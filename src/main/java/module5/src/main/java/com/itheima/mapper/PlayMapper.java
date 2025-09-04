package com.itheima.mapper;

import com.itheima.dto.Play;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PlayMapper {

    // 查询某个视频的选集
    @Select("""
            select id, title, duration, url
            from play
            where bv=#{bv}
            """)
    List<Play> findByBv(String bv);

    @Insert("""
            insert into play(id,title,duration,url,bv) 
            values (#{p.id},#{p.title},#{p.duration},#{p.url},#{bv})
            """)
    void insert(@Param("p") Play play, @Param("bv") String bv);
}
