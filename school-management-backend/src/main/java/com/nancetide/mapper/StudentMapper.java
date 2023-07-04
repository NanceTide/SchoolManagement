package com.nancetide.mapper;

import com.nancetide.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {

    List<Student> getAllStudent();

    List<Student> getMaleStudent();

    List<Student> getStudentByCriteria(String studentId, String studentName);

}
