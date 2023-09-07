package com.nancetide.service;

import com.nancetide.entity.Department;
import com.nancetide.entity.StudentView;
import lombok.NonNull;

import java.util.List;

public interface DepartmentService {

    List<Department> getDepartmentList();

    Department getDepartmentById(@NonNull String departmentId);

    Department getDepartmentByStudentId(@NonNull String studentId);

    List<Department> getDepartmentPage(@NonNull Integer page, @NonNull Integer limit);

    Integer getTotalDepartmentNumber();

    Integer getTotalDepartmentPage(Integer limit);

}
