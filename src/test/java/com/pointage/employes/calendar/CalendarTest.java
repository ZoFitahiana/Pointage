package com.pointage.employes.calendar;

import com.pointage.employes.model.calendar.Calendars;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CalendarTest {
    @Test
    void test_are_holidays_for_june_2024() {
        Calendar calendar = Calendar.getInstance();
        List<LocalDate> holidaysList = new ArrayList<>();
        holidaysList.add(LocalDate.of(2024, 6, 17));
        holidaysList.add(LocalDate.of(2024, 6, 25));
        holidaysList.add(LocalDate.of(2024, 6, 26));

        Calendars calendars = new Calendars(calendar, holidaysList);

        // Generate all dates for June 2024
        List<LocalDate> datesToCheck = new ArrayList<>();
        LocalDate startDate = LocalDate.of(2024, 6, 1);
        LocalDate endDate = LocalDate.of(2024, 6, 30);

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            datesToCheck.add(date);
        }

        // Expected results
        List<Boolean> expectedResults = new ArrayList<>();
        for (LocalDate date : datesToCheck) {
            if (holidaysList.contains(date)) {
                expectedResults.add(true);
            } else {
                expectedResults.add(false);
            }
        }

        // Get the actual results from the method
        List<Boolean> actualResults = calendars.areHolidays(datesToCheck);

        // Assert that the actual results match the expected results
        assertEquals(expectedResults, actualResults);
    }
}
