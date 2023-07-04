package com.nancetide.mapper;

import com.nancetide.entity.Application;
import lombok.NonNull;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApplicationMapper {

    List<Application> getApplicationByStudentId(@NonNull String StudentId);
}
