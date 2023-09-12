package com.nancetide.service;

import com.nancetide.entity.Department;
import lombok.NonNull;

import java.util.List;

public interface DepartmentService {

    List<Department> getDepartmentList();

    Department getDepartmentById(@NonNull String departmentId);

    Department getDepartmentByStudentId(@NonNull String studentId);

    List<Department> getDepartmentPage(@NonNull Integer page, @NonNull Integer limit);

    Integer getTotalDepartmentNumber();

    Integer getTotalDepartmentPage(Integer limit);

    Integer updateDepartmentById(@NonNull String departmentId, String departmentName);

    Integer insertDepartment(@NonNull String departmentId, @NonNull String departmentName);

    Integer deleteDepartmentById(@NonNull String departmentId);

}
