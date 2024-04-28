package com.iGainsTwo.iGainsJ.DTO.calendar;

import java.util.UUID;

public record AddCalendarDTO(Long exerciseId,
                             UUID userId,
                             int breakDuration,
                             int startTime,
                             int setQuantity) {
}
