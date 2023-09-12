package com.nancetide.service.impl;

import com.nancetide.entity.ClassView;
import com.nancetide.mapper.ClassMapper;
import com.nancetide.mapper.ClassViewMapper;
import com.nancetide.service.ClassService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    private final ClassMapper classMapper;
    private final ClassViewMapper classViewMapper;

    public ClassServiceImpl(
            ClassMapper classMapper,
            ClassViewMapper classViewMapper
    ) {
        this.classMapper = classMapper;
        this.classViewMapper = classViewMapper;
    }


    @Override
    public Integer updateClassById(@NonNull String classId, String className, String majorId) {
        if(classId.isEmpty() || className.isEmpty())
            return 0;
        return classMapper.updateClassById(classId, className, majorId);
    }

    @Override
    public List<ClassView> getClassListByDepartmentId(@NonNull String departmentId) {
        return classViewMapper.getClassViewListByDepartmentId(departmentId);
    }

    @Override
    public List<ClassView> getClassListByMajorId(@NonNull String majorId) {
        return classViewMapper.getClassViewListByMajorId(majorId);
    }

    @Override
    public Integer insertClass(@NonNull String classId, @NonNull String className, String majorId) {
        return classMapper.insertClass(classId, className, majorId);
    }

    @Override
    public Integer deleteClassById(@NonNull String classId) {
        return classMapper.deleteClassById(classId);
    }

}
