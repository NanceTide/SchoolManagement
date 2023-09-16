package com.nancetide.entit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grade {
    private String courseId;
    private String studentId;
    private Short grade;
}
