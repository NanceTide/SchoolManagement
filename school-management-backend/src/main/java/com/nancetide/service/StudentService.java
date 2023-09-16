package com.nancetide.service;

import com.nancetide.entity.StudentView;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

public interface StudentService {

    StudentView getStudentById(@NonNull String studentId);

    List<StudentView> getStudentPage(@NonNull Integer page, @NonNull Integer limit);

    Integer getTotalStudentNumber();

    Integer getTotalStudentPage(@NonNull Integer limit);

    Integer updateStudentById(
            @NonNull String studentId,
            String studentName,
            Short gender,
            LocalDate birthday,
            Year enroll,
            String nation,
            String address,
            String classId
    );

    Integer insertStudent(
            @NonNull String studentId,
            @NonNull String studentName,
            @NonNull Short gender,
            @NonNull LocalDate birthday,
            @NonNull Year enroll,
            @NonNull String nation,
            @NonNull String address,
            String classId
    );

    Integer deleteStudentById(@NonNull String studentId);

    List<StudentView> getAllStudent();

    List<StudentView> getStudentSearch(@NonNull String like);

}
