package com.nancetide;

import com.nancetide.mapper.DepartmentMapper;
import com.nancetide.mapper.StudentMapper;
import com.nancetide.mapper.StudentViewMapper;
import com.nancetide.mapper.UserMapper;
import com.nancetide.service.DepartmentService;
import com.nancetide.service.MajorService;
import com.nancetide.service.UserService;
import com.nancetide.service.impl.MajorServiceImpl;
import com.nancetide.utils.Jwt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

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

    @Test
    void testGetMaleStudent() {
        studentMapper.getMaleStudent().forEach(System.out::println);
    }

    @Test
    void testJwt() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 20);
        System.out.println(Jwt.generateJwt(claims));
    }

    @Test
    void testDecodeJwt() {
        String str = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MjAsImV4cCI6MTY4ODMwNzgyOX0.74rAmtXUrQqQr0OnrkNZz615WZn8X0R_XGmJc2f55ms";
        System.out.println(Jwt.decodeJwt(str));
    }

    @Test
    void testUserServiceLogin() {
        System.out.println(userService.login("root", "0000"));
    }

    @Test
    void testUserServiceStudentUpdate() {
        System.out.println(userService.studentUpdateUser("root", "0000", "0001"));
    }

    @Test
    void testUserServiceAdminUpdate() {
        System.out.println(userService.adminUpdateUser("root", "0000"));
    }

    @Test
    void test1(){
        System.out.println(majorService.getMajorByStudentId("2021010101001"));
    }

    @Test
    void test2() {
        studentViewMapper.getStudentViewPage(0, 10).forEach(System.out::println);
    }

}
