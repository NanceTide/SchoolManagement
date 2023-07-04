package com.nancetide.service;

import com.nancetide.entity.StudentView;
import lombok.NonNull;

public interface StudentViewService {

    StudentView studentGetSelfInfo(@NonNull String studentId);

}
