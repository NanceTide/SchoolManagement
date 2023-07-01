package com.nancetide.mapper;

import com.nancetide.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {

    public List<Student> getAllStudent();

    public List<Student> getMaleStudent();


}
