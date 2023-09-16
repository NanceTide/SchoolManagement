package com.nancetide.mappe;


import com.nancetide.entity.GradeView;
import lombok.NonNull;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GradeViewMapper {

    List<GradeView> getGradeViewByStudentId(@NonNull String studentId);

    List<GradeView> getGradeViewByCourseId(@NonNull String courseId);

}

