package com.nancetide.mapper;

import com.nancetide.entity.StudentView;
import lombok.NonNull;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentViewMapper {

    List<StudentView> getStudentViewByStudentId(@NonNull String studentId);

    List<StudentView> getStudentViewPage(@NonNull Integer offset, @NonNull Integer limit);

    Integer getTotalStudent();

}
