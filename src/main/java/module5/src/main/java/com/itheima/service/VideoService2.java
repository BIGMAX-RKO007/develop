package com.itheima.service;

import com.itheima.dto.Play;
import com.itheima.dto.Video;
import com.itheima.mapper.PlayMapper;
import com.itheima.mapper.VideoMapper;
import com.itheima.util.Bv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 从数据库中获取视频数据
 */
@Service
public class VideoService2 {

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private PlayMapper playMapper;

    // 根据 bv 号查询视频
    public Video find(String bv) {
        Video video = videoMapper.findByBv(bv);
        if (video == null) {
            return null;
        }

        List<Play> playList = playMapper.findByBv(bv);
        video.setPlayList(playList);
        return video;
    }

    // 发布视频
    public String publish(Video video) {
        video.setPublishTime(LocalDateTime.now()); // 设置发布事件
        // 1. 向 video 表插入视频
        videoMapper.insert(video);
        // 2. 生成 bv 号
        int id = videoMapper.lastInsertId();
        String bv = Bv.get(id);
        // 3. 更新 bv 号
        videoMapper.updateBv(bv, id);
        // 4. 向 play 表插入所有视频选集
        for (Play play : video.getPlayList()) {
            playMapper.insert(play, bv);
        }
        return bv;
    }
}
