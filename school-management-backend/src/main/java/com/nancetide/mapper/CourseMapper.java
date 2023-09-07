package com.nancetide.mapper;

import com.nancetide.entity.Course;
import lombok.NonNull;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper {

    List<Course> getCoursePage(@NonNull Integer offset, @NonNull Integer limit);

    Integer getTotalCourse();

}
