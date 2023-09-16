package com.nancetide.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Year;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentView {
    private String studentId;
    private String studentName;
    private Short gender;
    private String address;
    private LocalDate birthday;
    private Year enroll;
    private String nation;
    private String classId;
    private String className;
    private String majorId;
    private String majorName;
    private String departmentId;
    private String departmentName;
}
