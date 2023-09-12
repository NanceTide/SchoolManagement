package com.nancetide.service;

import com.nancetide.entity.GradeView;
import lombok.NonNull;

import java.util.List;

public interface GradeService {

    List<GradeView> getGradeListByCourseId(@NonNull String courseId);

    List<GradeView> getGradeListByStudentId(@NonNull String studentId);

    Integer updateGradeById(@NonNull String studentId, @NonNull String courseId, Short grade);

    Integer deleteGradeById(@NonNull String studentId, @NonNull String courseId);

    Integer insertGrade(@NonNull String studentId, @NonNull String courseId, Short grade);

}
