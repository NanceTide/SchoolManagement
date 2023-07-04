package com.nancetide.service.impl;

import com.nancetide.entity.MajorView;
import com.nancetide.entity.StudentView;
import com.nancetide.mapper.MajorViewMapper;
import com.nancetide.mapper.StudentViewMapper;
import com.nancetide.service.MajorService;
import com.nancetide.service.StudentService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorServiceImpl implements MajorService {

    private final MajorViewMapper majorViewMapper;
    private final StudentViewMapper studentViewMapper;
    private final StudentService studentService;

    @Autowired
    public MajorServiceImpl(MajorViewMapper majorViewMapper, StudentViewMapper studentViewMapper, StudentService studentService) {
        this.majorViewMapper = majorViewMapper;
        this.studentViewMapper = studentViewMapper;
        this.studentService = studentService;
    }

    @Override
    public List<MajorView> getMajorListByDepartmentId(@NonNull String departmentId) {
        return majorViewMapper.getMajorViewByCriteria(null, departmentId);
    }

    @Override
    public MajorView getMajorByName(@NonNull String majorName) {
        List<MajorView> majorViewList = majorViewMapper.getMajorViewByCriteria(null, majorName);
        if(majorViewList.isEmpty())
            return null;
        return majorViewList.get(0);
    }

    @Override
    public MajorView getMajorById(@NonNull String majorId) {
        List<MajorView> majorViewList = majorViewMapper.getMajorViewByCriteria(majorId, null);
        if(majorViewList.isEmpty())
            return null;
        return majorViewList.get(0);
    }

    @Override
    // 根据学生号获取所在专业
    public MajorView getMajorByStudentId(@NonNull String studentId) {
        StudentView studentView = studentService.getStudentById(studentId);
        if(studentView == null)
            return null;
        List<MajorView> majorViewList = majorViewMapper.getMajorViewByCriteria(studentView.getMajorId(), null);
        if(majorViewList.isEmpty())
            return null;
        return majorViewList.get(0);
    }

    @Override
    // 根据专业号获取某专业所属院系的所有专业的列表
    public List<MajorView> getMajorListShareDepartmentById(@NonNull String majorId) {
        return getMajorListByDepartmentId(getMajorById(majorId).getDepartmentId());
    }


    @Override
    // 根据学生号获取某学生所在院系的所有专业的列表
    public List<MajorView> getMajorListShareDepartmentByStudentId(@NonNull String studentId) {
        return getMajorListShareDepartmentById(
                studentViewMapper.getStudentViewByStudentId(studentId).get(0).getMajorId());
    }

    @Override
    // 根据学生号获取某学生所在院系的所有专业的列表，但该学生所在的专业不在列表中
    public List<MajorView> getExceptMajorListShareDepartmentByStudentId(@NonNull String studentId) {
        MajorView majorView = getMajorByStudentId(studentId);
        return getMajorListShareDepartmentByStudentId(studentId).stream().filter(item -> !item.equals(majorView)).toList();
    }

}
