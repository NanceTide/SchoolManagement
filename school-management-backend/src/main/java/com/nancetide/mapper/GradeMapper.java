package com.nancetide.mapper;

import lombok.NonNull;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GradeMapper {

    Integer updateGradeById(@NonNull String studentId, @NonNull String courseId, Short grade);

    Integer deleteGradeById(@NonNull String studentId, @NonNull String courseId);

    Integer insertGrade(@NonNull String studentId, @NonNull String courseId, Short grade);
}
