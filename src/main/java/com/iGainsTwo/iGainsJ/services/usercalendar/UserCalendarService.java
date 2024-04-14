package com.iGainsTwo.iGainsJ.services.usercalendar;

import com.iGainsTwo.iGainsJ.DTO.calendar.AddCalendarDTO;
import com.iGainsTwo.iGainsJ.DTO.calendar.CalendarDTO;
import com.iGainsTwo.iGainsJ.DTO.calendar.DeleteCalendarDTO;
import com.iGainsTwo.iGainsJ.exceptions.CalendarNeverExistedException;
import com.iGainsTwo.iGainsJ.exceptions.ExerciseNeverExistedException;
import com.iGainsTwo.iGainsJ.exceptions.UserNeverExistedException;

public interface UserCalendarService {
    CalendarDTO addUserCalendar(AddCalendarDTO addCalendarDTO) throws UserNeverExistedException, ExerciseNeverExistedException;
    void deleteUserCalendar(DeleteCalendarDTO deleteCalendarDTO) throws UserNeverExistedException, CalendarNeverExistedException;
}
