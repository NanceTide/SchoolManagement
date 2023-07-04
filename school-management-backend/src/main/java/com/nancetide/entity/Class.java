package com.nancetide.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Class {
    private String classId;
    private String className;
    private Short size;
    private String majorId;
}
