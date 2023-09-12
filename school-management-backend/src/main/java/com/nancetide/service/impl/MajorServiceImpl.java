package com.nancetide.service.impl;

import com.nancetide.entity.MajorView;
import com.nancetide.entity.StudentView;
import com.nancetide.mapper.MajorMapper;
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

    private final MajorMapper majorMapper;
    private final MajorViewMapper majorViewMapper;
    private final StudentViewMapper studentViewMapper;
    private final StudentService studentService;

    @Autowired
    public MajorServiceImpl(
            MajorMapper majorMapper,
            MajorViewMapper majorViewMapper,
            StudentViewMapper studentViewMapper,
            StudentService studentService
    ) {
        this.majorMapper = majorMapper;
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
    public Integer updateMajorById(@NonNull String majorId, String majorName, String departmentId) {
        if(majorName.isEmpty() || departmentId.isEmpty())
            return 0;
        return majorMapper.updateMajorById(majorId, majorName, departmentId);
    }

    @Override
    public Integer deleteMajorById(@NonNull String majorId) {
        return majorMapper.deleteMajorById(majorId);
    }

    @Override
    public Integer insertMajor(@NonNull String majorId, @NonNull String majorName, String departmentId) {
        return majorMapper.insertMajor(majorId, majorName, departmentId);
    }


}
