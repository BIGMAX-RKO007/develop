package com.itheima.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

@Controller
public class UploadController {

    @Value("${video-path}")
    private String videoPath;

    @Value("${img-path}")
    private String imgPath;

    // 上传分块
    @RequestMapping("/upload")
    @ResponseBody
    // MultipartFile 专用于上传二进制数据的类型
    public Map<String, String> upload(int i, int chunks, MultipartFile data, String url)
            throws IOException {
//        System.out.println(i + "/" + chunks + " " + url);
        data.transferTo(Path.of(videoPath, url + ".part" + i));
        return Map.of(url, (i * 100.0 / chunks) + "%");
    }

    // 合并分块
    @RequestMapping("/finish")
    @ResponseBody
    public void finish(int chunks, String url) throws IOException { // chunks=3
        // 5c39b2dbe5c4a6030deb89b48ceb66c7.mp4.part1
        // 5c39b2dbe5c4a6030deb89b48ceb66c7.mp4.part2
        // 5c39b2dbe5c4a6030deb89b48ceb66c7.mp4.part3
        // 5c39b2dbe5c4a6030deb89b48ceb66c7.mp4    FileOutputStream 文件输出流 (作用：创建新文件，并写入内容)
        try (FileOutputStream os = new FileOutputStream(videoPath + url)) {
            // 写入内容
            for (int i = 1; i <= chunks; i++) { // 1,2,3
                Path part = Path.of(videoPath, url + ".part" + i);
                // 参数1 原始文件  参数2 目标文件
                Files.copy(part, os);
                part.toFile().delete(); // 删除 part 文件
            }
        }
        // finally { os.close() }
    }

    // 上传封面
    @RequestMapping("/uploadCover")
    @ResponseBody
    public Map<String, String> uploadCover(MultipartFile data, String cover) throws IOException {
        data.transferTo(Path.of(imgPath, cover));
        return Map.of("cover", cover);
    }


}
