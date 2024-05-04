package com.iGainsTwo.iGainsJ.DTO.calendar;

import java.util.UUID;

public record UpdateCalendarDTO(Long calendarId,
                                Long exerciseId,
                                String email,
                                int breakDuration,
                                String startTime,
                                int setQuantity,
                                String calendarDate) {
}
