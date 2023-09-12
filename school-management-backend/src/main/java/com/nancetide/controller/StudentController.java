package com.nancetide.controller;

import com.nancetide.entity.*;
import com.nancetide.service.*;
import com.nancetide.utils.Jwt;
import com.nancetide.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;
    private final MajorService majorService;
    private final ApplicationService applicationService;
    private final DepartmentService departmentService;
    private final UserService userService;
    private final GradeService gradeService;

    @Autowired
    public StudentController(
            StudentService studentService,
            MajorService majorService,
            ApplicationService applicationService,
            DepartmentService departmentService,
            UserService userService,
            GradeService gradeService
    ) {
        this.studentService = studentService;
        this.majorService = majorService;
        this.applicationService = applicationService;
        this.departmentService = departmentService;
        this.userService = userService;
        this.gradeService = gradeService;
    }

    private String validateStudentTokenAndGetId(String token) {
        String studentId;
        try {studentId = Jwt.jwtToUsername(token);}
        catch (Exception e) {return null;}
        if(Jwt.jwtToAccess(token) == 0)
            return null;
        return studentId;
    }

    @RequestMapping("/student/info")
    public Result<?> studentSelfInfo(@RequestHeader String token) {
        String studentId = validateStudentTokenAndGetId(token);
        if(studentId == null)
            return Result.error("未登录或不能访问学生接口", null);

        StudentView studentView = studentService.getStudentById(studentId);
        if(studentView != null)
            return Result.success("查询信息成功", studentView);
        return Result.error("查询信息失败", null);
    }

    @RequestMapping("/student/grade")
    public Result<?> studentSelfGrade(@RequestHeader String token) {
        String studentId = validateStudentTokenAndGetId(token);
        if(studentId == null)
            return Result.error("未登录或不能访问学生接口", null);

        List<GradeView> gradeViewList = gradeService.getGradeListByStudentId(studentId);
        if(!gradeViewList.isEmpty())
            return Result.success("查询成绩成功", gradeViewList);
        return Result.error("查询成绩失败", null);
    }

    @RequestMapping("/student/application")
    public Result<?> studentSelfApplication(@RequestHeader String token) {
        String studentId = validateStudentTokenAndGetId(token);
        if(studentId == null)
            return Result.error("未登录或不能访问学生接口", null);

        ApplicationView application = applicationService.getApplicationByStudentId(studentId);
        if(application != null)
            return Result.success("查询异动申请成功", application);
        return Result.success("查询异动申请成功但无记录", null);
    }

    @RequestMapping("/student/major")
    public Result<?> major(@RequestHeader String token) {
        String studentId = validateStudentTokenAndGetId(token);
        if(studentId == null)
            return Result.error("未登录或不能访问学生接口", null);

        List<MajorView> majorViewList = majorService.getMajorListShareDepartmentByStudentId(studentId);
        MajorView majorView = majorService.getMajorByStudentId(studentId);
        if(majorViewList != null && majorView != null)
            return Result.success("查询同院系专业成功", Arrays.asList(majorViewList, majorView));
        return Result.error("查询同院系专业失败", null);
    }

    @RequestMapping("/student/application/major")
    public Result<?> applicationMajor(@RequestHeader String token, @RequestParam String majorTo) {
        if(majorTo == null || majorTo.isEmpty())
            return Result.error("专业为空", null);

        String studentId = validateStudentTokenAndGetId(token);
        if(studentId == null)
            return Result.error("未登录或不能访问学生接口", null);

        if(applicationService.insertApplication(studentId, majorTo))
            return Result.success("申请成功", majorTo);
        return Result.error("申请失败", null);
    }

    @RequestMapping("/student/department")
    public Result<?> department(@RequestHeader String token) {
        String studentId = validateStudentTokenAndGetId(token);
        if(studentId == null)
            return Result.error("未登录或不能访问学生接口", null);

        List<Department> departmentList = departmentService.getDepartmentList();
        Department department = departmentService.getDepartmentByStudentId(studentId);
        if(departmentList != null && department != null)
            return Result.success("查询院系成功", Arrays.asList(departmentList, department));
        return Result.error("查询院系失败", null);
    }

    @RequestMapping("/public/department/major")
    public Result<?> majorOfDepartment(@RequestParam String departmentId) {
        List<MajorView> majorViewList = majorService.getMajorListByDepartmentId(departmentId);
        if(majorViewList != null)
            return Result.success("查询专业成功", majorViewList);
        return Result.error("查询专业失败", null);
    }

    @RequestMapping("/student/account")
    public Result<?> account(
            @RequestHeader String token,
            @RequestParam String oldPassword,
            @RequestParam String newPassword
    ) {
        String studentId = validateStudentTokenAndGetId(token);
        if(studentId == null)
            return Result.error("未登录或不能访问学生接口", null);

        if(userService.studentUpdateUser(studentId, oldPassword, newPassword.substring(0, Math.min(newPassword.length(), 33))))
            return Result.success("密码修改成功", null);
        else
            return Result.error("密码不正确", null);
    }

}
