package com.nancetide.service.impl;

import com.nancetide.entity.ApplicationView;
import com.nancetide.mapper.ApplicationViewMapper;
import com.nancetide.service.ApplicationService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationViewMapper applicationViewMapper;

    @Autowired
    public ApplicationServiceImpl(ApplicationViewMapper applicationViewMapper) {
        this.applicationViewMapper = applicationViewMapper;
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
        return applicationViewMapper.insertApplication(studentId, majorId) == 1;
    }

}
