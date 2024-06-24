package com.pointage.employes.calendar;

import org.junit.jupiter.api.Test;

import static com.pointage.employes.operation.Salary.calculateTotalHoursInMonth;
import static org.springframework.test.util.AssertionErrors.assertEquals;

public class TotalHoursInMonth {
    @Test
    void get_total_hours_month() {
        int totalHoursInMonth = calculateTotalHoursInMonth(8, 30, 0);
        assertEquals("Total hours in month", 240, totalHoursInMonth);
    }
}
