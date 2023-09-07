package com.nancetide.service;

import com.nancetide.entity.GradeView;
import com.nancetide.entity.StudentView;
import lombok.NonNull;

import java.util.List;

public interface StudentService {

    StudentView getStudentById(@NonNull String studentId);

    List<GradeView> getGradeListByStudentId(@NonNull String studentId);

    List<StudentView> getStudentPage(@NonNull Integer page, @NonNull Integer limit);

    Integer getTotalStudentNumber();

    Integer getTotalStudentPage(Integer limit);

}
