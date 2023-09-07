package com.nancetide.service.impl;

import com.nancetide.entity.GradeView;
import com.nancetide.entity.StudentView;
import com.nancetide.mapper.GradeViewMapper;
import com.nancetide.mapper.StudentViewMapper;
import com.nancetide.service.StudentService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentViewMapper studentViewMapper;
    private final GradeViewMapper gradeViewMapper;

    @Autowired
    public StudentServiceImpl(StudentViewMapper studentViewMapper, GradeViewMapper gradeViewMapper) {
        this.studentViewMapper = studentViewMapper;
        this.gradeViewMapper = gradeViewMapper;
    }

    @Override
    public StudentView getStudentById(@NonNull String studentId) {
        List<StudentView> studentViewList = studentViewMapper.getStudentViewByStudentId(studentId);
        if(!studentViewList.isEmpty())
            return studentViewList.get(0);
        return null;
    }

    @Override
    public List<GradeView> getGradeListByStudentId(@NonNull String studentId) {
        return gradeViewMapper.getGradeViewByStudentId(studentId);
    }

    @Override
    public List<StudentView> getStudentPage(@NonNull Integer page, @NonNull Integer limit) {
        return studentViewMapper.getStudentViewPage((page - 1) * limit, limit);
    }

    @Override
    public Integer getTotalStudentNumber() {
        return studentViewMapper.getTotalStudent();
    }

    @Override
    public Integer getTotalStudentPage(@NonNull Integer limit) {
        return (int) Math.ceil((double) getTotalStudentNumber() / limit);
    }

}
