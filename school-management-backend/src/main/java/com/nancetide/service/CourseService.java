package com.nancetide.service;

import com.nancetide.entity.Course;
import lombok.NonNull;

import java.util.List;

public interface CourseService {

    List<Course> getCoursePage(@NonNull Integer page, @NonNull Integer limit);

    Integer getTotalCourseNumber();

    Integer getTotalCoursePage(@NonNull Integer limit);

    Integer updateCourseById(@NonNull String courseId, String courseName, Integer credit);

    Integer insertCourse(@NonNull String courseId, @NonNull String courseName, @NonNull Short credit);

    Integer deleteCourseById(@NonNull String courseId);

}
