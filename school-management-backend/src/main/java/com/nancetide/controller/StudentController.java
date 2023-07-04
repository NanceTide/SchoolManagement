package com.nancetide.controller;

import com.nancetide.entity.StudentView;
import com.nancetide.service.StudentViewService;
import com.nancetide.utils.Jwt;
import com.nancetide.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentViewController {

    private final StudentViewService studentViewService;

    @Autowired
    public StudentViewController(StudentViewService studentViewService) {
        this.studentViewService = studentViewService;
    }

    @RequestMapping("/student/selfInfo")
    public Result<?> studentSelfInfo(@RequestHeader String Token) {
        String studentId = (String) Jwt.decodeJwt(Token).get("username");
        if(studentId.equals("root"))
            return Result.error("管理员账户不能通过学生接口查询", null);
        StudentView studentView = studentViewService.studentGetSelfInfo(studentId);
        if(studentView != null)
            return Result.success("查询成功", studentView);
        return Result.error("查询失败", null);
    }

    @RequestMapping("/student/selfGrade")
    public Result<?> studentSelfGrade(@RequestHeader String Token) {
        String student
    }

}
