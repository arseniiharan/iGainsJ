package com.iGainsTwo.iGainsJ.mappers;

import com.iGainsTwo.iGainsJ.DTO.calendar.CalendarDTO;
import com.iGainsTwo.iGainsJ.DTO.exercise.ExerciseDTO;
import com.iGainsTwo.iGainsJ.models.Exercise;
import com.iGainsTwo.iGainsJ.models.UserCalendar;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CalendarMapper {
    @Mapping(target = "breakDuration", source = "dto.breakDuration")
    @Mapping(target = "setQuantity", source = "dto.setQuantity")
    @Mapping(target = "startTime", source = "dto.startTime")
    UserCalendar toModel(CalendarDTO dto);

    @Mapping(target = "breakDuration", source = "userCalendar.breakDuration")
    @Mapping(target = "setQuantity", source = "userCalendar.setQuantity")
    @Mapping(target = "startTime", source = "userCalendar.startTime")
    CalendarDTO toDto(UserCalendar userCalendar);
}
