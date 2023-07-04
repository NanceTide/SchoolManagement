package com.nancetide.mapper;

import com.nancetide.entity.Major;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface MajorMapper {

    List<Major> getMajorByCriteria(String majorId, String majorName);

}
