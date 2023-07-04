package com.nancetide.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {

    private Integer status;
    private String message;
    private T data;

    static public <T> Result<T> success(String message, T data) {
        return new Result<>(1, message, data);
    }

    static public <T> Result<T> error(String message, T data) {
        return new Result<>(0, message, data);
    }

}
