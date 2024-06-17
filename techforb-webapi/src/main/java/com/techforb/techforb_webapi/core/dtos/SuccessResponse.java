package com.techforb.techforb_webapi.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuccessResponse<T> {
    private T data;

    private int status;

    public static <T> SuccessResponse<T> create(T data, int status) {
        return new SuccessResponse<T>(
                data, status);
    }

}
