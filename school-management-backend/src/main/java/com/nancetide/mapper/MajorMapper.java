package com.nancetide.mapper;

import com.nancetide.entity.Major;
import lombok.NonNull;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MajorMapper {

    List<Major> getMajorByCriteria(String majorId, String majorName);

    Integer updateMajorById(@NonNull String majorId, String majorName, String departmentId);

    Integer deleteMajorById(@NonNull String majorId);

    Integer insertMajor(@NonNull String majorId, @NonNull String majorName, String departmentId);

}
