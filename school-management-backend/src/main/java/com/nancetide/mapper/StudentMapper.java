package com.nancetide.mappe;

import com.nancetide.entity.Student;
import lombok.NonNull;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

@Mapper
public interface StudentMapper {

    List<Student> getAllStudent();

    List<Student> getStudentByCriteria(String studentId, String studentName);

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

}
