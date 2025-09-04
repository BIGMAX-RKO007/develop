package com.itheima.mapper;

import com.itheima.dto.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper // 这是一个专用于增删改查的接口
// 实现类(mybatis 和 spring), 可以通过 @Autowired 依赖注入获取实现类对象
public interface StudentMapper {

    @Select("""
            select id, name
            from student
            """)
    List<Student> findAll();

    // 根据编号查询学生
    @Select("""
            select id, name
            from student
            where id=#{id}
            """)
    Student findById(int id); // id=1,2,3...

    // 新增学生
    /*@Insert("""
            insert into student(id, name)
            values (#{id}, #{name})
            """)
    void insert(@Param("id") int i, @Param("name") String n);*/

    @Insert("""
            insert into student(id, name)
            values (#{id}, #{name})
            """)
    void insert(Student stu);

    // 修改学生
    @Update("""
            update student set name=#{name}
            where id=#{id}
            """)
    void update(Student stu);

    @Delete("delete from student where id=#{id}")
    void delete(int id);
}
