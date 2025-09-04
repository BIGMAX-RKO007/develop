package com.itheima;

import com.itheima.dto.Student;
import com.itheima.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

// 单元测试
@SpringBootTest
public class TestStudentMapper {

    @Autowired
    StudentMapper studentMapper;

    @Test // 测试查询所有
    public void test1() {
        System.out.println(1);
        List<Student> all = studentMapper.findAll();
        for (Student stu : all) {
            System.out.println(stu.getId() + " " + stu.getName());
        }
    }

    @Test // 测试根据id查询
    public void test2() {
        System.out.println(2);
        Student stu = studentMapper.findById(4);
        System.out.println(stu);
//        System.out.println(stu.getId() + " " + stu.getName());
    }

    @Test
    public void test3() {
//        studentMapper.insert(5, "钱七");
        Student stu = new Student(6, "周八");
        studentMapper.insert(stu);
    }

    @Test
    public void test4() {
        Student stu = new Student(1, "张小三");
        studentMapper.update(stu);
    }

    @Test
    public void test5() {
        studentMapper.delete(5);
    }

}
