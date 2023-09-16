package com.nancetide.service.impl;

import com.nancetide.entity.Course;
import com.nancetide.mapper.CourseMapper;
import com.nancetide.service.CourseService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseMapper courseMapper;

    @Autowired
    public CourseServiceImpl(
            CourseMapper courseMapper
    ) {
        this.courseMapper = courseMapper;
    }

    @Override
    public List<Course> getCoursePage(@NonNull Integer page, @NonNull Integer limit) {
        return courseMapper.getCoursePage((page - 1) * limit, limit);
    }

    @Override
    public Integer getTotalCourseNumber() { return courseMapper.getTotalCourse(); }

    @Override
    public Integer getTotalCoursePage(@NonNull Integer limit) {
        return (int) Math.ceil((double) getTotalCourseNumber() / limit);
    }

    @Override
    public Integer updateCourseById(@NonNull String courseId, String courseName, Integer credit) {
        if(courseId.isEmpty())
            return 0;
        return courseMapper.updateCourseById(courseId, courseName, credit);
    }

    @Override
    public Integer insertCourse(@NonNull String courseId, @NonNull String courseName, @NonNull Short credit) {
        return courseMapper.insertCourse(courseId, courseName, credit);
    }

    public Integer deleteCourseById(@NonNull String courseId) {
        return courseMapper.deleteCourseById(courseId);
    }

    @Override
    public List<Course> getCourseSearch(@NonNull String like) {
        return courseMapper.getCourseSearch(like);
    }

    @Override
    public List<Course> getAllCourse() {
        return courseMapper.getAllCourse();
    }

}
