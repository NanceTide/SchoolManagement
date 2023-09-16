package com.nancetide.mapper;

import com.nancetide.entity.Department;
import lombok.NonNull;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentMapper {

    List<Department> getDepartment();

    List<Department> getDepartmentById(@NonNull String departmentId);

    List<Department> getDepartmentPage(@NonNull Integer offset, @NonNull Integer limit);

    Integer getTotalDepartment();

    Integer updateDepartmentById(@NonNull String departmentId, String departmentName);

    Integer deleteDepartmentById(@NonNull String departmentId);

    Integer insertDepartment(@NonNull String departmentId, @NonNull String departmentName);

}
