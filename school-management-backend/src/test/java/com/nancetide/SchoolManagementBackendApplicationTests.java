package com.nancetide;

import com.nancetide.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SchoolManagementBackendApplicationTests {

    @Autowired
    StudentMapper studentMapper;

    @Test
    void testGetAllStudents() {
        studentMapper.getAllStudent().forEach(System.out::println);
    }

    @Test
    void testGetMaleStudent() {
        studentMapper.getMaleStudent().forEach(System.out::println);
    }

}
