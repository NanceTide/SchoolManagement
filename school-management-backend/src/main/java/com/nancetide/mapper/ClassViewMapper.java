package com.nancetide.mappe;

import com.nancetide.entity.ClassView;
import lombok.NonNull;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassViewMapper {

    List<ClassView> getClassViewByDepartmentId(@NonNull String departmentId);

    List<ClassView> getClassViewByMajorId(@NonNull String majorId);

}
