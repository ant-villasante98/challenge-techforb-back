package com.techforb.techforb_webapi.core.dtos;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnsuccessResponse {
    private String title;
    private int status;
    private String type;
    @Nullable
    private Object errors;
}
