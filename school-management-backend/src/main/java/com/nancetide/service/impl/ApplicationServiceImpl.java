package com.nancetide.service.impl;

import com.nancetide.entity.ApplicationView;
import com.nancetide.mapper.ApplicationMapper;
import com.nancetide.mapper.ApplicationViewMapper;
import com.nancetide.mapper.StudentMapper;
import com.nancetide.service.ApplicationService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationViewMapper applicationViewMapper;
    private final ApplicationMapper applicationMapper;
    private final StudentMapper studentMapper;

    @Autowired
    public ApplicationServiceImpl(
            ApplicationViewMapper applicationViewMapper,
            ApplicationMapper applicationMapper,
            StudentMapper studentMapper
    ) {
        this.applicationViewMapper = applicationViewMapper;
        this.applicationMapper = applicationMapper;
        this.studentMapper = studentMapper;
    }

    @Override
    public ApplicationView getApplicationByStudentId(@NonNull String studentId) {
        List<ApplicationView> applicationList = applicationViewMapper.getApplicationViewByStudentId(studentId);
        if(!applicationList.isEmpty())
            return applicationList.get(0);
        return null;
    }

    @Override
    public Boolean insertApplication(@NonNull String studentId, @NonNull String majorId) {
        if(getApplicationByStudentId(studentId) != null)
            return false;
        return applicationMapper.insertApplication(studentId, majorId) == 1;
    }

    @Override
    public List<ApplicationView> getAllApplication() {
        return applicationViewMapper.getAllApplicationView();
    }

    @Override
    public Boolean confirmApplication(@NonNull String studentId, @NonNull String classId) {
        applicationMapper.deleteApplication(studentId);
        return studentMapper.updateStudentById(studentId, null, null, null, null, null, null, classId) == 1;
    }

    @Override
    public Boolean cancelApplication(@NonNull String studentId) {
        return applicationMapper.deleteApplication(studentId) == 1;
    }

}
