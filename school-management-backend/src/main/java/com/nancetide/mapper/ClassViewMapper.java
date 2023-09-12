package com.nancetide.mapper;

import com.nancetide.entity.Class;
import com.nancetide.entity.ClassView;
import lombok.NonNull;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassViewMapper {

    List<ClassView> getClassViewListByDepartmentId(@NonNull String departmentId);

    List<ClassView> getClassViewListByMajorId(@NonNull String majorId);

}
