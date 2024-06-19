package com.techforb.techforb_webapi.core.dtos.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalReading {

    private long readingOK;
    private long mediumAlerts;
    private long redAlerts;
    private long disableSensors;
}
