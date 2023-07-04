package com.nancetide.service;

import com.nancetide.entity.MajorView;
import lombok.NonNull;

import java.util.List;

public interface MajorService {

    List<MajorView> getMajorListByDepartmentId(@NonNull String departmentId);

    MajorView getMajorByName(@NonNull String majorName);

    MajorView getMajorById(@NonNull String majorId);

    MajorView getMajorByStudentId(@NonNull String studentId);

    List<MajorView> getMajorListShareDepartmentById(@NonNull String majorId);

    List<MajorView> getMajorListShareDepartmentByStudentId(@NonNull String studentId);

    List<MajorView> getExceptMajorListShareDepartmentByStudentId(@NonNull String studentId);
}
