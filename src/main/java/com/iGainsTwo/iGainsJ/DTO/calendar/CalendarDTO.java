package com.iGainsTwo.iGainsJ.DTO.calendar;

import lombok.Data;

@Data
public class CalendarDTO {
    private boolean isCompleted;
    private int breakDuration;
    private String startTime;
    private int setQuantity;
    private String calendarDate;
}
