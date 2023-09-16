package com.nancetide.mappe;


import lombok.NonNull;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApplicationMapper {

    Integer deleteApplication(@NonNull String studentId);

    Integer insertApplication(@NonNull String studentId, @NonNull String majorId);

    Integer confirmApplication(@NonNull String studentId);

}
