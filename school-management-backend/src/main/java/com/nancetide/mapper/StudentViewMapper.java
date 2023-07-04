package com.nancetide.mapper;

import com.nancetide.entity.StudentView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentViewMapper {

    List<StudentView> getStudentViewByStudentId(String studentId);

}
