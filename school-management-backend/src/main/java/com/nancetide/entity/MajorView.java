package com.nancetide.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MajorView {
    private String majorId;
    private String majorName;
    private String departmentId;
    private String departmentName;
}
