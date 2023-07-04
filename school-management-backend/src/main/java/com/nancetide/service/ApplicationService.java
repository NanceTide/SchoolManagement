package com.nancetide.service;

import com.nancetide.entity.ApplicationView;
import lombok.NonNull;

public interface ApplicationService {

    ApplicationView getApplicationByStudentId(@NonNull String studentId);

    Boolean insertApplication(@NonNull String studentId, @NonNull String majorId);

}
