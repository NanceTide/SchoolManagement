package com.nancetide.controller;

import com.nancetide.entity.ApplicationView;
import com.nancetide.entity.GradeView;
import com.nancetide.entity.MajorView;
import com.nancetide.entity.StudentView;
import com.nancetide.service.ApplicationService;
import com.nancetide.service.MajorService;
import com.nancetide.service.StudentService;
import com.nancetide.utils.Jwt;
import com.nancetide.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;
    private final MajorService majorService;
    private final ApplicationService applicationService;

    @Autowired
    public StudentController(StudentService studentService, MajorService majorService, ApplicationService applicationService) {
        this.studentService = studentService;
        this.majorService = majorService;
        this.applicationService = applicationService;
    }

    @RequestMapping("/student/info")
    public Result<?> studentSelfInfo(@RequestHeader String token) {
        String studentId;
        try {
            studentId = Jwt.jwtToUsername(token);
        } catch (Exception e) {
            return Result.error("未登录", null);
        }
        if(studentId.equals("root"))
            return Result.error("管理员账户不能访问学生接口", null);

        StudentView studentView = studentService.getStudentById(studentId);
        if(studentView != null)
            return Result.success("查询信息成功", studentView);
        return Result.error("查询信息失败", null);
    }

    @RequestMapping("/student/grade")
    public Result<?> studentSelfGrade(@RequestHeader String token) {
        String studentId;
        try {studentId = Jwt.jwtToUsername(token);}
        catch (Exception e) {return Result.error("未登录", null);}
        if(studentId.equals("root"))
            return Result.error("管理员账户不能访问学生接口", null);

        List<GradeView> gradeViewList = studentService.getGradeListByStudentId(studentId);
        if(!gradeViewList.isEmpty())
            return Result.success("查询成绩成功", gradeViewList);
        return Result.error("查询成绩失败", null);
    }

    @RequestMapping("/student/application")
    public Result<?> studentSelfApplication(@RequestHeader String token) {
        String studentId;
        try {studentId = Jwt.jwtToUsername(token);}
        catch (Exception e) {return Result.error("未登录", null);}
        if(studentId.equals("root"))
            return Result.error("管理员账户不能访问学生接口", null);

        ApplicationView application = applicationService.getApplicationByStudentId(studentId);
        if(application != null)
            return Result.success("查询异动申请成功", application);
        return Result.success("查询异动申请成功但无记录", null);
    }

    @RequestMapping("/student/major/same")
    public Result<?> majorSame(@RequestHeader String token) {
        String studentId;
        try {studentId = Jwt.jwtToUsername(token);}
        catch (Exception e) {return Result.error("未登录", null);}
        if(studentId.equals("root"))
            return Result.error("管理员账户不能访问学生接口", null);

        List<MajorView> majorViewList = majorService.getExceptMajorListShareDepartmentByStudentId(studentId);
        if(majorViewList != null)
            return Result.success("查询同院系专业成功", majorViewList);
        return Result.error("查询同院系专业失败", null);
    }

    @RequestMapping("/student/application/same")
    public Result<?> applicationSame(@RequestHeader String token, @RequestParam String majorTo) {
        String studentId;
        try {studentId = Jwt.jwtToUsername(token);}
        catch (Exception e) {return Result.error("未登录", null);}
        if(studentId.equals("root"))
            return Result.error("管理员账户不能访问学生接口", null);

        if(applicationService.insertApplication(studentId, majorTo))
            return Result.success("申请成功", majorTo);
        else
            return Result.error("申请失败", null);
    }


}
