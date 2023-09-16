package com.nancetide.mappe;

import com.nancetide.entity.Course;
import lombok.NonNull;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper {

    List<Course> getCoursePage(@NonNull Integer offset, @NonNull Integer limit);

    Integer getTotalCourse();

    Integer updateCourseById(@NonNull String courseId, String courseName, Integer credit);

    Integer insertCourse(@NonNull String courseId, @NonNull String courseName, Short credit);

    Integer deleteCourseById(@NonNull String courseId);

}
