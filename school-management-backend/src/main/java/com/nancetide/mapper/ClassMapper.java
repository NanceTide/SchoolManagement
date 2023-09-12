package com.nancetide.mapper;

import lombok.NonNull;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClassMapper {

    Integer updateClassById(@NonNull String classId, String className, String majorId);

    Integer deleteClassById(@NonNull String classId);

    Integer insertClass(@NonNull String classId, @NonNull String className, String majorId);

}
