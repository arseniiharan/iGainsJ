package com.iGainsTwo.iGainsJ.DTO.calendar;

import java.util.UUID;

public record UpdateCalendarDTO(Long calendarId,
                                Long exerciseId,
                                UUID userId,
                                int breakDuration,
                                int startTime,
                                int setQuantity) {
}
