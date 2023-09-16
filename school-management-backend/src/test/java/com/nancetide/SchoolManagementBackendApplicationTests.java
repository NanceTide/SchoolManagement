package com.nancetide;

import com.nancetide.mapper.StudentMapper;
import com.nancetide.mapper.StudentViewMapper;
import com.nancetide.mapper.UserMapper;
import com.nancetide.service.DepartmentService;
import com.nancetide.service.UserService;
import com.nancetide.service.impl.MajorServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class SchoolManagementBackendApplicationTests {

    @Autowired StudentMapper studentMapper;
    @Autowired UserMapper userMapper;
    @Autowired UserService userService;
    @Autowired MajorServiceImpl majorService;
    @Autowired DepartmentService departmentService;
    @Autowired StudentViewMapper studentViewMapper;

    @Test
    void testGetAllStudents() {
        studentMapper.getAllStudent().forEach(System.out::println);
    }

}
