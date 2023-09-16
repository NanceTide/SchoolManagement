package com.nancetide.controller;

import com.nancetide.entity.*;
import com.nancetide.service.*;
import com.nancetide.utils.Jwt;
import com.nancetide.utils.Result;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;

@RestController
public class AdminController {

    private final StudentService studentService;
    private final CourseService courseService;
    private final DepartmentService departmentService;
    private final GradeService gradeService;
    private final MajorService majorService;
    private final ClassService classService;
    private final ApplicationService applicationService;

    @Autowired
    public AdminController(
            StudentService studentService,
            CourseService courseService,
            DepartmentService departmentService,
            GradeService gradeService,
            MajorService majorService,
            ClassService classService,
            ApplicationService applicationService
    ) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.departmentService = departmentService;
        this.gradeService = gradeService;
        this.majorService = majorService;
        this.classService = classService;
        this.applicationService = applicationService;
    }

    private Result<?> validateAdminToken(String token) {
        try {Jwt.jwtToUsername(token);}
        catch (Exception e) {return Result.error("未登录", null);}
        if(Jwt.jwtToAccess(token) != 0)
            return Result.error("不是管理员账户", null);
        return null;
    }

    @RequestMapping("/admin/student/list")
    public Result<?> studentList(@RequestHeader String token, @RequestParam Integer page) {
        Result<?> validationResult = validateAdminToken(token);
        if(validationResult != null)
            return validationResult;

        if(page == null || page < 1 || page > studentService.getTotalStudentPage(10))
            return Result.error("查询失败，页数不合法", null);
        List<StudentView> studentViewList = studentService.getStudentPage(page, 10);
        return Result.success("查询学生成功", studentViewList);
    }

    @RequestMapping("/admin/student/totalCount")
    public Result<?> studentTotalNumber(@RequestHeader String token) {
        Result<?> validationResult = validateAdminToken(token);
        if(validationResult != null)
            return validationResult;

        return Result.success("查询成功", studentService.getTotalStudentNumber());
    }

    @RequestMapping("/admin/student/update")
    public Result<?> studentUpdate(
            @RequestHeader String token,
            @RequestParam String studentId,
            @RequestParam(required = false) String studentName,
            @RequestParam(required = false) Short gender,
            @RequestParam(required = false) LocalDate birthday,
            @RequestParam(required = false) Year enroll,
            @RequestParam(required = false) String nation,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String classId
            ) {
        Result<?> validationResult = validateAdminToken(token);
        if(validationResult != null)
            return validationResult;

        if(studentService.updateStudentById(
                studentId, studentName, gender, birthday, enroll, nation, address, classId
        ) == 0)
            return Result.error("修改失败", null);
        return Result.success("修改成功", null);
    }

    @RequestMapping("/admin/student/grade")
    public Result<?> studentGrade(@RequestHeader String token, @RequestParam String studentId) {
        Result<?> validationResult = validateAdminToken(token);
        if(validationResult != null)
            return validationResult;

        List<GradeView> gradeViewList = gradeService.getGradeListByStudentId(studentId);
        if(gradeViewList != null)
            return Result.success("查询成绩成功", gradeViewList);
        return Result.error("查询成绩失败", null);
    }

    @RequestMapping("/admin/student/search")
    public Result<?> studentSearch(@RequestHeader String token, @RequestParam String like) {
        Result<?> validationResult = validateAdminToken(token);
        if(validationResult != null)
            return validationResult;

        List<StudentView> studentViewList = studentService.getStudentSearch(like);
        if(studentViewList != null)
            return Result.success("查询成功", studentViewList);
        return Result.error("查询失败", null);
    }

    @RequestMapping("/admin/course/list")
    public Result<?> courseList(@RequestHeader String token, @RequestParam Integer page) {
        Result<?> validationResult = validateAdminToken(token);
        if(validationResult != null)
            return validationResult;

        if(page == null || page < 1 || page > courseService.getTotalCoursePage(10))
            return Result.error("查询失败，页数不合法", null);
        List<Course> courseList = courseService.getCoursePage(page, 10);
        return Result.success("查询课程成功", courseList);
    }

    @RequestMapping("/admin/course/totalCount")
    public Result<?> courseTotalNumber(@RequestHeader String token) {
        Result<?> validationResult = validateAdminToken(token);
        if(validationResult != null)
            return validationResult;

        return Result.success("查询成功", courseService.getTotalCourseNumber());
    }

    @RequestMapping("/admin/course/update")
    public Result<?> courseUpdate(
            @RequestHeader String token,
            @RequestParam String courseId,
            @RequestParam(required = false) String courseName,
            @RequestParam(required = false) Integer credit
            ) {
        Result<?> validationResult = validateAdminToken(token);
        if(validationResult != null)
            return validationResult;

        if(courseService.updateCourseById(
                courseId, courseName, credit
        ) == 0)
            return Result.error("修改失败", null);
        return Result.success("修改成功", null);
    }

    @RequestMapping("/admin/course/grade")
    public Result<?> courseGrade(@RequestHeader String token, @RequestParam String courseId) {
        Result<?> validationResult = validateAdminToken(token);
        if(validationResult != null)
            return validationResult;

        List<GradeView> gradeViewList = gradeService.getGradeListByCourseId(courseId);
        if(gradeViewList != null)
            return Result.success("查询成功", gradeViewList);
        return Result.error("查询失败", null);
    }

    @RequestMapping("/admin/grade/update")
    public Result<?> gradeUpdate(
            @RequestHeader String token,
            @RequestParam String studentId,
            @RequestParam String courseId,
            @RequestParam(required = false) Short grade
            ) {
        Result<?> validationResult = validateAdminToken(token);
        if(validationResult != null)
            return validationResult;

        if(gradeService.updateGradeById(studentId, courseId, grade) == 0)
            return Result.error("修改失败", 0);
        return Result.success("修改成功", 0);
    }

    @RequestMapping("/admin/department/list")
    public Result<?> departmentList(@RequestHeader String token, @RequestParam Integer page) {
        Result<?> validationResult = validateAdminToken(token);
        if(validationResult != null)
            return validationResult;

        if(page == null || page < 1 || page > departmentService.getTotalDepartmentPage(10))
            return Result.error("查询失败，页数不合法", null);
        List<Department> departmentList = departmentService.getDepartmentPage(page, 10);
        return Result.success("查询院系成功", departmentList);
    }

    @RequestMapping("/admin/department/totalCount")
    public Result<?> departmentTotalNumber(@RequestHeader String token) {
        Result<?> validationResult = validateAdminToken(token);
        if(validationResult != null)
            return validationResult;

        return Result.success("查询成功", departmentService.getTotalDepartmentNumber());
    }

    @RequestMapping("/admin/department/update")
    public Result<?> departmentUpdate(
            @RequestHeader String token,
            @RequestParam String departmentId,
            @RequestParam(required = false) String departmentName
    ) {
        Result<?> validationResult = validateAdminToken(token);
        if(validationResult != null)
            return validationResult;

        if(departmentService.updateDepartmentById(
                departmentId, departmentName
        ) == 0)
            return Result.error("修改失败", null);
        return Result.success("修改成功", null);
    }

    @RequestMapping("/admin/department/major/list")
    public Result<?> majorList(@RequestHeader String token, @RequestParam String departmentId) {
        Result<?> validationResult = validateAdminToken(token);
        if(validationResult != null)
            return validationResult;

        List<MajorView> majorViewList = majorService.getMajorListByDepartmentId(departmentId);
        if(majorViewList != null)
            return Result.success("查询成功", majorViewList);
        return Result.error("查询失败", null);
    }

    @RequestMapping("/admin/department/major/update")
    public Result<?> majorUpdate(
            @RequestHeader String token,
            @RequestParam String majorId,
            @RequestParam(required = false) String majorName,
            @RequestParam(required = false) String departmentId
    ) {
        Result<?> validationResult = validateAdminToken(token);
        if(validationResult != null)
            return validationResult;

        if(majorService.updateMajorById(majorId, majorName, departmentId) == 0)
            return Result.error("修改失败", null);
        return Result.success("修改成功", null);
    }

    @RequestMapping("/admin/department/major/class/list")
    public Result<?> classList(@RequestHeader String token, @RequestParam String majorId) {
        Result<?> validationResult = validateAdminToken(token);
        if(validationResult != null)
            return validationResult;

        List<ClassView> classViewList = classService.getClassListByMajorId(majorId);
        if(classViewList != null)
            return Result.success("查询成功", classViewList);
        return Result.error("查询失败", null);
    }

    @RequestMapping("/admin/department/major/class/update")
    public Result<?> classUpdate(
            @RequestHeader String token,
            @RequestParam String classId,
            @RequestParam(required = false) String className,
            @RequestParam(required = false) String majorId
    ) {
        Result<?> validationResult = validateAdminToken(token);
        if(validationResult != null)
            return validationResult;

        if(classService.updateClassById(classId, className, majorId) == 0)
            return Result.error("修改失败", null);
        return Result.success("修改成功", null);
    }

    @RequestMapping("/admin/student/insert")
    public Result<?> studentInsert(
            @RequestHeader String token,
            @RequestParam String studentId,
            @RequestParam String studentName,
            @RequestParam Short gender,
            @RequestParam LocalDate birthday,
            @RequestParam Year enroll,
            @RequestParam String nation,
            @RequestParam String address,
            @RequestParam(required = false) String classId
    ) {
        Result<?> validationResult = validateAdminToken(token);
        if(validationResult != null)
            return validationResult;

        if(studentService.insertStudent(
                studentId, studentName, gender, birthday, enroll, nation, address, classId
        ) == 0)
            return Result.error("新增失败", null);
        return Result.success("新增成功", null);
    }

    @RequestMapping("/admin/student/delete")
    public Result<?> studentDelete(@RequestHeader String token, @RequestParam String studentId) {
        Result<?> validationResult = validateAdminToken(token);
        if(validationResult != null)
            return validationResult;

        if(studentService.deleteStudentById(studentId) == 0)
            return Result.error("删除失败", null);
        return Result.success("删除成功", null);
    }

    @RequestMapping("/admin/grade/insert")
    public Result<?> gradeInsert(
            @RequestHeader String token,
            @RequestParam String studentId,
            @RequestParam String courseId,
            @RequestParam(required = false) Short grade
    ) {
        Result<?> validationResult = validateAdminToken(token);
        if(validationResult != null)
            return validationResult;

        if(gradeService.insertGrade(studentId, courseId, grade) == 0)
            return Result.error("新增失败", null);
        return Result.success("新增成功", null);
    }

    @RequestMapping("/admin/grade/delete")
    public Result<?> gradeDelete(
            @RequestHeader String token,
            @RequestParam String studentId,
            @RequestParam String courseId
    ) {
        Result<?> validationResult = validateAdminToken(token);
        if(validationResult != null)
            return validationResult;

        if(gradeService.deleteGradeById(studentId, courseId) == 0)
            return Result.error("删除失败", null);
        return Result.success("删除成功", null);
    }

    @RequestMapping("/admin/course/insert")
    public Result<?> courseInsert(
            @RequestHeader String token,
            @RequestParam String courseId,
            @RequestParam String courseName,
            @RequestParam Short credit
    ) {
        Result<?> validationResult = validateAdminToken(token);
        if(validationResult != null)
            return validationResult;

        if(courseService.insertCourse(courseId, courseName, credit) == 0)
            return Result.error("新增失败", null);
        return Result.success("新增成功", null);
    }

    @RequestMapping("/admin/course/delete")
    public Result<?> courseDelete(@RequestHeader String token, @RequestParam String courseId) {
        Result<?> validationResult = validateAdminToken(token);
        if(validationResult != null)
            return validationResult;

        if(courseService.deleteCourseById(courseId) == 0)
            return Result.error("删除失败", null);
        return Result.success("删除成功", null);
    }

    @RequestMapping("/admin/department/insert")
    public Result<?> departmentInsert(
            @RequestHeader String token,
            @RequestParam String departmentId,
            @RequestParam String departmentName
    ) {
        Result<?> validationResult = validateAdminToken(token);
        if(validationResult != null)
            return validationResult;

        if(departmentService.insertDepartment(departmentId, departmentName) == 0)
            return Result.error("新增失败", null);
        return Result.success("新增成功", null);
    }

    @RequestMapping("/admin/department/delete")
    public Result<?> departmentDelete(
            @RequestHeader String token,
            @RequestParam String departmentId
    ) {
        Result<?> validationResult = validateAdminToken(token);
        if(validationResult != null)
            return validationResult;

        if(departmentService.deleteDepartmentById(departmentId) == 0)
            return Result.error("删除失败", null);
        return Result.success("删除成功", null);
    }

    @RequestMapping("/admin/major/insert")
    public Result<?> majorInsert(
            @RequestHeader String token,
            @RequestParam String majorId,
            @RequestParam String majorName,
            @RequestParam(required = false) String departmentId
    ) {
        Result<?> validationResult = validateAdminToken(token);
        if(validationResult != null)
            return validationResult;

        if(majorService.insertMajor(majorId, majorName, departmentId) == 0)
            return Result.error("新增失败", null);
        return Result.success("新增成功", null);
    }

    @RequestMapping("/admin/major/delete")
    public Result<?> majorDelete(
            @RequestHeader String token,
            @RequestParam String majorId
    ) {
        Result<?> validationResult = validateAdminToken(token);
        if(validationResult != null)
            return validationResult;

        if(majorService.deleteMajorById(majorId) == 0)
            return Result.error("删除失败", null);
        return Result.success("删除成功", null);
    }

    @RequestMapping("/admin/class/insert")
    public Result<?> classInsert(
            @RequestHeader String token,
            @RequestParam String classId,
            @RequestParam String className,
            @RequestParam(required = false) String majorId
    ) {
        Result<?> validationResult = validateAdminToken(token);
        if(validationResult != null)
            return validationResult;

        if(classService.insertClass(classId, className, majorId) == 0)
            return Result.error("新增失败", null);
        return Result.success("新增成功", null);
    }

    @RequestMapping("/admin/class/delete")
    public Result<?> classDelete(
            @RequestHeader String token,
            @RequestParam String classId
    ) {
        Result<?> validationResult = validateAdminToken(token);
        if(validationResult != null)
            return validationResult;

        if(classService.deleteClassById(classId) == 0)
            return Result.error("删除失败", null);
        return Result.success("删除成功", null);
    }

    @RequestMapping("/admin/application/confirm")
    public Result<?> applicationConfirm(
            @RequestHeader String token,
            @RequestParam String studentId,
            @RequestParam String classId
    ) {
        Result<?> validationResult = validateAdminToken(token);
        if(validationResult != null)
            return validationResult;

        if(applicationService.confirmApplication(studentId, classId))
            return Result.success("异动已审批", null);
        return Result.error("异动审批失败", null);
    }

    @RequestMapping("/admin/application/cancel")
    public Result<?> applicationCancel(
            @RequestHeader String token,
            @RequestParam String studentId
    ) {
        Result<?> validationResult = validateAdminToken(token);
        if(validationResult != null)
            return validationResult;

        if(applicationService.cancelApplication(studentId))
            return Result.success("异动已取消", null);
        return Result.error("异动取消失败", null);
    }

    @RequestMapping("/admin/application/list")
    public Result<?> applicationList(@RequestHeader String token) {
        Result<?> validationResult = validateAdminToken(token);
        if(validationResult != null)
            return validationResult;

        return Result.success("成功", applicationService.getAllApplication());
    }

    @RequestMapping("/admin/student/file")
    public void studentFile(@RequestHeader String token, HttpServletResponse response) throws IOException {
        if(validateAdminToken(token) != null)
            return;

        HSSFWorkbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("学生信息");

        List<StudentView> studentList = studentService.getAllStudent();
        int rowNum = 0;

        for (StudentView student : studentList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(student.getStudentId());
            row.createCell(1).setCellValue(student.getStudentName());
            row.createCell(2).setCellValue(student.getGender() == 0 ? "女" : "男");
            row.createCell(3).setCellValue(student.getEnroll().getValue());
            row.createCell(4).setCellValue(student.getBirthday().toString());
            row.createCell(5).setCellValue(student.getAddress());
            row.createCell(6).setCellValue(student.getNation());
            row.createCell(7).setCellValue(student.getDepartmentName());
            row.createCell(8).setCellValue(student.getMajorName());
            row.createCell(9).setCellValue(student.getClassName());
            row.createCell(10).setCellValue(student.getDepartmentId());
            row.createCell(11).setCellValue(student.getMajorId());
            row.createCell(12).setCellValue(student.getClassId());
        }

        String fileName = "学生信息.xls";
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.close();
    }

    @RequestMapping("/admin/course/search")
    public Result<?> courseSearch(@RequestHeader String token, @RequestParam String like) {
        Result<?> validationResult = validateAdminToken(token);
        if(validationResult != null)
            return validationResult;

        List<Course> courseList = courseService.getCourseSearch(like);
        if(courseList != null)
            return Result.success("查询成功", courseList);
        return Result.error("查询失败", null);
    }

    @RequestMapping("/admin/course/file")
    public void courseFile(@RequestHeader String token, HttpServletResponse response) throws IOException {
        if(validateAdminToken(token) != null)
            return;

        HSSFWorkbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("课程信息");

        List<Course> courseList = courseService.getAllCourse();
        int rowNum = 0;

        for (Course course : courseList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(course.getCourseId());
            row.createCell(1).setCellValue(course.getCourseName());
            row.createCell(2).setCellValue(course.getCredit());
        }

        String fileName = "课程信息.xls";
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.close();
    }

}
