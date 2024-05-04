package com.iGainsTwo.iGainsJ.DTO.calendar;

import java.util.UUID;

public record AddCalendarDTO(Long exerciseId,
                             String email,
                             int breakDuration,
                             String startTime,
                             int setQuantity,
                             String calendarDate) {
}
