package com.nancetide.config;

import com.nancetide.utils.Result;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerConfig {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public Result<?> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        String errorMessage = "违反数据完整性约束: " + e.getMessage();
        return Result.error(errorMessage, null);
    }

}
