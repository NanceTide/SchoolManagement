package com.nancetide.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeView {
    private String studentId;
    private String studentName;
    private String courseId;
    private String courseName;
    private Short credit;
    private Short grade;
}
