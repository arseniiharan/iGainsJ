package com.iGainsTwo.iGainsJ.services.usercalendar.impl;

import com.iGainsTwo.iGainsJ.DTO.calendar.AddCalendarDTO;
import com.iGainsTwo.iGainsJ.DTO.calendar.CalendarDTO;
import com.iGainsTwo.iGainsJ.DTO.calendar.DeleteCalendarDTO;
import com.iGainsTwo.iGainsJ.exceptions.CalendarNeverExistedException;
import com.iGainsTwo.iGainsJ.exceptions.ExerciseNeverExistedException;
import com.iGainsTwo.iGainsJ.exceptions.UserNeverExistedException;
import com.iGainsTwo.iGainsJ.mappers.CalendarMapper;
import com.iGainsTwo.iGainsJ.models.Exercise;
import com.iGainsTwo.iGainsJ.models.User;
import com.iGainsTwo.iGainsJ.models.UserCalendar;
import com.iGainsTwo.iGainsJ.repositories.ExerciseRepository;
import com.iGainsTwo.iGainsJ.repositories.UserCalendarRepository;
import com.iGainsTwo.iGainsJ.repositories.UserRepository;
import com.iGainsTwo.iGainsJ.services.usercalendar.UserCalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserCalendarServiceImpl implements UserCalendarService {
    private final UserCalendarRepository userCalendarRepository;
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;

    private final CalendarMapper calendarMapper;

    @Override
    public CalendarDTO addUserCalendar(AddCalendarDTO addCalendarDTO) throws UserNeverExistedException, ExerciseNeverExistedException{
        Optional<User> userOptional = userRepository.findById(addCalendarDTO.userId());
        if (userOptional.isEmpty()) {
            throw new UserNeverExistedException("This user doesn't exist");
        }
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(addCalendarDTO.exerciseId());
        if (exerciseOptional.isEmpty()) {
            throw new ExerciseNeverExistedException("This exercise doesn't exist");
        }
        Exercise exercise = exerciseOptional.get();
        User user = userOptional.get();
        UserCalendar userCalendar = new UserCalendar();
        userCalendar.setUser(user);
        userCalendar.setExercise(exercise);
        userCalendar.setCompleted(false);
        userCalendar.setSetQuantity(addCalendarDTO.setQuantity());
        userCalendar.setBreakDuration(addCalendarDTO.breakDuration());
        userCalendar.setStartTime(addCalendarDTO.startTime());
        user.getUserCalendar().add(userCalendar);
        userRepository.save(user);
        userCalendarRepository.save(userCalendar);
        return calendarMapper.toDto(userCalendar);
    }

    @Override
    public void deleteUserCalendar(DeleteCalendarDTO deleteCalendarDTO) throws UserNeverExistedException, CalendarNeverExistedException{
        Optional<User> userOptional = userRepository.findById(deleteCalendarDTO.userId());
        if (userOptional.isEmpty()) {
            throw new UserNeverExistedException("This user doesn't exist");
        }
        Optional<UserCalendar> calendarOptional = userCalendarRepository.findById(deleteCalendarDTO.calendarId());
        if (calendarOptional.isEmpty()) {
            throw new CalendarNeverExistedException("Calendar like this doesn't exist for this user or have never been created");
        }
        UserCalendar userCalendar = calendarOptional.get();
        User user = userOptional.get();
        user.getUserCalendar().remove(userCalendar);
        userRepository.save(user);
        userCalendarRepository.delete(userCalendar);
    }
}
