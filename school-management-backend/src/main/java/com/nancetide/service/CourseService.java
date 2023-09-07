package com.nancetide.service;

import com.nancetide.entity.Course;
import lombok.NonNull;

import java.util.List;

public interface CourseService {

    List<Course> getCoursePage(@NonNull Integer page, @NonNull Integer limit);

    Integer getTotalCourseNumber();

    Integer getTotalCoursePage(@NonNull Integer limit);

}
