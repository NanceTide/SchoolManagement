package com.nancetide.service.impl;

import com.nancetide.entity.StudentView;
import com.nancetide.mapper.StudentViewMapper;
import com.nancetide.service.StudentViewService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentViewServiceImpl implements StudentViewService {

    private final StudentViewMapper studentViewMapper;

    @Autowired
    public StudentViewServiceImpl(StudentViewMapper studentViewMapper) {
        this.studentViewMapper = studentViewMapper;
    }

    @Override
    public StudentView studentGetSelfInfo(@NonNull String studentId) {
        List<StudentView> studentViewList = studentViewMapper.getStudentViewByStudentId(studentId);
        if(!studentViewList.isEmpty())
            return studentViewList.get(0);
        return null;
    }

}
