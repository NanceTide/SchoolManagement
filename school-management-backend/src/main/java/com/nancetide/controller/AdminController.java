package com.nancetide.controller;

import com.nancetide.entity.Course;
import com.nancetide.entity.Department;
import com.nancetide.entity.StudentView;
import com.nancetide.service.CourseService;
import com.nancetide.service.DepartmentService;
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
public class AdminController {

    private final StudentService studentService;
    private final CourseService courseService;
    private final DepartmentService departmentService;

    @Autowired
    public AdminController(StudentService studentService, CourseService courseService, DepartmentService departmentService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.departmentService = departmentService;
    }

    @RequestMapping("/admin/student/list")
    public Result<?> studentList(@RequestHeader String token, @RequestParam Integer page) {
        try {Jwt.jwtToUsername(token);}
        catch (Exception e) {return Result.error("未登录", null);}
        if(Jwt.jwtToAccess(token) != 0)
            return Result.error("不是管理员账户", null);

        if(page == null || page < 1 || page > studentService.getTotalStudentPage(10))
            return Result.error("查询失败，页数不合法", null);
        List<StudentView> studentViewList = studentService.getStudentPage(page, 10);
        return Result.success("查询学生成功", studentViewList);
    }

    @RequestMapping("/admin/student/totalCount")
    public Result<?> studentTotalNumber(@RequestHeader String token) {
        try {Jwt.jwtToUsername(token);}
        catch (Exception e) {return Result.error("未登录", null);}
        if(Jwt.jwtToAccess(token) != 0)
            return Result.error("不是管理员账户", null);

        return Result.success("查询成功", studentService.getTotalStudentNumber());
    }

    @RequestMapping("/admin/student/search")
    public Result<?> studentSearch(@RequestHeader String token, @RequestParam String studentName) {
        try {Jwt.jwtToUsername(token);}
        catch (Exception e) {return Result.error("未登录", null);}
        if(Jwt.jwtToAccess(token) != 0)
            return Result.error("不是管理员账户", null);

        return Result.success("", null);
    }

//    @RequestMapping("/admin/student/alter")
//    public Result<?> studentAlter(@RequestHeader String token, @RequestParam String studentId) {
//        return null;
//    }
//
//    @RequestMapping("/admin/grade/list")
//    public Result<?> gradeList(@RequestHeader String token, @RequestParam Integer page) {
//        return null;
//    }

    @RequestMapping("/admin/course/list")
    public Result<?> courseList(@RequestHeader String token, @RequestParam Integer page) {
        try {Jwt.jwtToUsername(token);}
        catch (Exception e) {return Result.error("未登录", null);}
        if(Jwt.jwtToAccess(token) != 0)
            return Result.error("不是管理员账户", null);

        if(page == null || page < 1 || page > courseService.getTotalCoursePage(10))
            return Result.error("查询失败，页数不合法", null);
        List<Course> courseList = courseService.getCoursePage(page, 10);
        return Result.success("查询课程成功", courseList);
    }

    @RequestMapping("/admin/course/totalCount")
    public Result<?> courseTotalNumber(@RequestHeader String token) {
        try {Jwt.jwtToUsername(token);}
        catch (Exception e) {return Result.error("未登录", null);}
        if (Jwt.jwtToAccess(token) != 0)
            return Result.error("不是管理员账户", null);

        return Result.success("查询成功", courseService.getTotalCourseNumber());
    }

    @RequestMapping("/admin/department/list")
    public Result<?> departmentList(@RequestHeader String token, @RequestParam Integer page) {
        try {Jwt.jwtToUsername(token);}
        catch (Exception e) {return Result.error("未登录", null);}
        if(Jwt.jwtToAccess(token) != 0)
            return Result.error("不是管理员账户", null);

        if(page == null || page < 1 || page > departmentService.getTotalDepartmentPage(10))
            return Result.error("查询失败，页数不合法", null);
        List<Department> departmentList = departmentService.getDepartmentPage(page, 10);
        return Result.success("查询院系成功", departmentList);
    }

    @RequestMapping("/admin/department/totalCount")
    public Result<?> departmentTotalNumber(@RequestHeader String token) {
        try {Jwt.jwtToUsername(token);}
        catch (Exception e) {return Result.error("未登录", null);}
        if (Jwt.jwtToAccess(token) != 0)
            return Result.error("不是管理员账户", null);

        return Result.success("查询成功", departmentService.getTotalDepartmentNumber());
    }

}
