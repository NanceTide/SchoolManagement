package com.nancetide.service;

import com.nancetide.entity.ApplicationView;
import lombok.NonNull;

import java.util.List;

public interface ApplicationService {

    ApplicationView getApplicationByStudentId(@NonNull String studentId);

    Boolean insertApplication(@NonNull String studentId, @NonNull String majorId);

    List<ApplicationView> getAllApplication();

    Boolean confirmApplication(@NonNull String studentId, @NonNull String majorId);

    Boolean cancelApplication(@NonNull String studentId);

}
