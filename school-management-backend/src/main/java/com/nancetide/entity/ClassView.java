package com.nancetide.entit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassView {
    private String classId;
    private String className;
    private String majorId;
    private String majorName;
    private String departmentId;
    private String departmentName;
    private Short size;
}
