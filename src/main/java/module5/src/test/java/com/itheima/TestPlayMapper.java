package com.itheima;

import com.itheima.dto.Play;
import com.itheima.mapper.PlayMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.List;

@SpringBootTest
public class TestPlayMapper {

    @Autowired
    private PlayMapper playMapper;

    @Test
    public void test1() {
        List<Play> list = playMapper.findByBv("1");
        System.out.println(list.size());
    }

    @Test
    public void test2() {
        Play p1 = new Play("P1", "选集1", LocalTime.parse("00:21:10"), "1.mp4");
        Play p2 = new Play("P2", "选集2", LocalTime.parse("00:18:22"), "2.mp4");

        playMapper.insert(p1, "bv4GmR4x");
        playMapper.insert(p2, "bv4GmR4x");
    }
}
