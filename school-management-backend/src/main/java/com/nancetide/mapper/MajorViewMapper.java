package com.nancetide.mappe;

import com.nancetide.entity.MajorView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MajorViewMapper {

    List<MajorView> getMajorViewByCriteria(String majorId, String departmentId);

}
