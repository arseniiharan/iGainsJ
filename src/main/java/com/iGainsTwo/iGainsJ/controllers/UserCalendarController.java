package com.iGainsTwo.iGainsJ.controllers;

import com.iGainsTwo.iGainsJ.DTO.calendar.AddCalendarDTO;
import com.iGainsTwo.iGainsJ.DTO.calendar.DeleteCalendarDTO;
import com.iGainsTwo.iGainsJ.DTO.calendar.UpdateCalendarDTO;
import com.iGainsTwo.iGainsJ.exceptions.CalendarNeverExistedException;
import com.iGainsTwo.iGainsJ.exceptions.ExerciseNeverExistedException;
import com.iGainsTwo.iGainsJ.exceptions.UserNeverExistedException;
import com.iGainsTwo.iGainsJ.services.usercalendar.UserCalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calendar")
@RequiredArgsConstructor
public class UserCalendarController {
    private final UserCalendarService userCalendarService;

    @PostMapping("/add")
    public ResponseEntity<?> addUserCalendar(@RequestBody AddCalendarDTO addCalendarDTO) {
        try {
            userCalendarService.addUserCalendar(addCalendarDTO);
            return ResponseEntity.ok("Calendar saved successfully");
        } catch (UserNeverExistedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (ExerciseNeverExistedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUserCalendar(@RequestBody DeleteCalendarDTO deleteCalendarDTO) {
        try {
            userCalendarService.deleteUserCalendar(deleteCalendarDTO);
            return ResponseEntity.ok("Calendar has been deleted successfully");
        } catch (UserNeverExistedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (CalendarNeverExistedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUserCalendar(@RequestBody UpdateCalendarDTO updateCalendarDTO) {
        try {
            userCalendarService.updateUserCalendar(updateCalendarDTO);
            return ResponseEntity.ok("Calendar has been updated successfully");
        } catch (UserNeverExistedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (ExerciseNeverExistedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (CalendarNeverExistedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return  ResponseEntity.badRequest().body("Bad request");
        }
    }
}
