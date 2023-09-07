package com.nancetide.service.impl;

import com.nancetide.entity.Department;
import com.nancetide.entity.StudentView;
import com.nancetide.mapper.DepartmentMapper;
import com.nancetide.service.DepartmentService;
import com.nancetide.service.StudentService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentMapper departmentMapper;
    private final StudentService studentService;

    @Autowired
    public DepartmentServiceImpl(DepartmentMapper departmentMapper, StudentService studentService) {
        this.departmentMapper = departmentMapper;
        this.studentService = studentService;
    }

    @Override
    public List<Department> getDepartmentList() {
        return departmentMapper.getDepartment();
    }

    @Override
    public Department getDepartmentById(@NonNull String departmentId) {
        List<Department> departmentList = departmentMapper.getDepartmentById(departmentId);
        if(departmentList.isEmpty())
            return null;
        return departmentList.get(0);
    }

    @Override
    public Department getDepartmentByStudentId(@NonNull String studentId) {
        StudentView studentView = studentService.getStudentById(studentId);
        if(studentView == null)
            return null;
        return getDepartmentById(studentView.getDepartmentId());
    }

    @Override
    public List<Department> getDepartmentPage(@NonNull Integer page, @NonNull Integer limit) {
        return departmentMapper.getDepartmentPage((page - 1) * limit, limit);
    }

    @Override
    public Integer getTotalDepartmentNumber() { return departmentMapper.getTotalDepartment(); }

    @Override
    public Integer getTotalDepartmentPage(Integer limit) {
        return (int) Math.ceil((double) getTotalDepartmentNumber() / limit);
    }


}
