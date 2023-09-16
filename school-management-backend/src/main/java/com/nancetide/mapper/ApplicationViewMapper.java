package com.nancetide.mappe;

import com.nancetide.entity.ApplicationView;
import lombok.NonNull;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApplicationViewMapper {

    List<ApplicationView> getApplicationViewByStudentId(@NonNull String StudentId);

    List<ApplicationView> getAllApplicationView();

}
