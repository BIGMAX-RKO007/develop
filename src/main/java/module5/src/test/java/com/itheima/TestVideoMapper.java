package com.itheima;

import com.itheima.dto.Video;
import com.itheima.mapper.VideoMapper;
import com.itheima.util.Bv;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class TestVideoMapper {

    @Autowired
    private VideoMapper videoMapper;

    @Test
    public void test1() {
        Video v = videoMapper.findByBv("1");
        System.out.println(v.getBv());
        System.out.println(v.getType());
        System.out.println(v.getCategory());
        System.out.println(v.getTitle());
        System.out.println(v.getCover());
        System.out.println(v.getIntroduction());
        System.out.println(v.getPublishTime());
        System.out.println(v.getTagList());
    }

    @Test
    public void test2() {
        Video v = new Video();
        v.setType("转发");
        v.setCategory("动漫");
        v.setTitle("海贼王3");
        v.setCover("3.png");
        v.setIntroduction("简介...");
        v.setPublishTime(LocalDateTime.now());
        v.setTags("日本_热血_动漫");

        videoMapper.insert(v);

        /*
            bv 号举例
               BV1bB4y187mj
               BV16S4y1E7Da
               BV1zr4y1j7nU
               BV1cB4y1Y7VW
               ...
         */
        int id = videoMapper.lastInsertId();
        System.out.println(id);

        String bv = Bv.get(id);
        videoMapper.updateBv(bv, id);
    }
}
