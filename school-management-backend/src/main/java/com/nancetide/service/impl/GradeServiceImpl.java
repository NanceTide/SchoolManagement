package com.nancetide.service.impl;

import com.nancetide.entity.GradeView;
import com.nancetide.mapper.GradeMapper;
import com.nancetide.mapper.GradeViewMapper;
import com.nancetide.service.GradeService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {

    private final GradeMapper gradeMapper;
    private final GradeViewMapper gradeViewMapper;

    public GradeServiceImpl(
            GradeMapper gradeMapper,
            GradeViewMapper gradeViewMapper
    ) {
        this.gradeMapper = gradeMapper;
        this.gradeViewMapper = gradeViewMapper;
    }

    @Override
    public Integer updateGradeById(@NonNull String studentId, @NonNull String courseId, Short grade) {
        if(studentId.isEmpty() || courseId.isEmpty())
            return 0;
        return gradeMapper.updateGradeById(studentId, courseId, grade);
    }

    @Override
    public List<GradeView> getGradeListByStudentId(@NonNull String studentId) {
        return gradeViewMapper.getGradeViewByStudentId(studentId);
    }

    @Override
    public List<GradeView> getGradeListByCourseId(@NonNull String courseId) {
        return gradeViewMapper.getGradeViewByCourseId(courseId);
    }

    @Override
    public Integer deleteGradeById(@NonNull String studentId, @NonNull String courseId) {
        return gradeMapper.deleteGradeById(studentId, courseId);
    }

    @Override
    public Integer insertGrade(@NonNull String studentId, @NonNull String courseId, Short grade) {
        return gradeMapper.insertGrade(studentId, courseId, grade);
    }

}
