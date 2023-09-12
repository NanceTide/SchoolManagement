package com.nancetide.service;

import com.nancetide.entity.ClassView;
import lombok.NonNull;

import java.util.List;

public interface ClassService {

    Integer updateClassById(@NonNull String classId, String className, String majorId);

    List<ClassView> getClassListByDepartmentId(@NonNull String departmentId);

    List<ClassView> getClassListByMajorId(@NonNull String majorId);

    Integer insertClass(@NonNull String classId, @NonNull String className, String majorId);

    Integer deleteClassById(@NonNull String classId);

}
