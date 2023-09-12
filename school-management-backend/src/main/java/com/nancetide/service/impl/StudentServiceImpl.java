package com.nancetide.service.impl;

import com.nancetide.entity.GradeView;
import com.nancetide.entity.StudentView;
import com.nancetide.mapper.GradeViewMapper;
import com.nancetide.mapper.StudentMapper;
import com.nancetide.mapper.StudentViewMapper;
import com.nancetide.service.StudentService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentMapper studentMapper;
    private final StudentViewMapper studentViewMapper;

    @Autowired
    public StudentServiceImpl(
            StudentMapper studentMapper,
            StudentViewMapper studentViewMapper
    ) {
        this.studentMapper = studentMapper;
        this.studentViewMapper = studentViewMapper;
    }

    @Override
    public StudentView getStudentById(@NonNull String studentId) {
        List<StudentView> studentViewList = studentViewMapper.getStudentViewByStudentId(studentId);
        if(!studentViewList.isEmpty())
            return studentViewList.get(0);
        return null;
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

    @Override
    public Integer updateStudentById(
            @NonNull String studentId,
            String studentName,
            Short gender,
            LocalDate birthday,
            Year enroll,
            String nation,
            String address,
            String classId
    ) {
        if(studentId.isEmpty())
            return 0;
        return studentMapper.updateStudentById(studentId, studentName, gender, birthday, enroll, nation, address, classId);
    }

    @Override
    public Integer insertStudent(
            @NonNull String studentId,
            @NonNull String studentName,
            @NonNull Short gender,
            @NonNull LocalDate birthday,
            @NonNull Year enroll,
            @NonNull String nation,
            @NonNull String address,
            String classId
    ) {
        return studentMapper.insertStudent(studentId, studentName, gender, birthday, enroll, nation, address, classId);
    }

    @Override
    public Integer deleteStudentById(@NonNull String studentId) {
        return studentMapper.deleteStudentById(studentId);
    }

}
