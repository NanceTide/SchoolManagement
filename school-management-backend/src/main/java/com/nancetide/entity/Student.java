package com.nancetide.entit;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Year;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String studentId;
    private String studentName;
    private Short gender;
    private LocalDate birthday;
    private Year enroll;
    private String nation;
    private String address;
    private String classId;
}
