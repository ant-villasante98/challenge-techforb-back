package com.techforb.techforb_webapi.core.dtos;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnsuccessResponse {
    private String message;
    private int status;
    private String exception;
    private Object details;
}
