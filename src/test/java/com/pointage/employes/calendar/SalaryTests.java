package com.pointage.employes.calendar;

import com.pointage.employes.model.Category;
import com.pointage.employes.model.Employe;
import com.pointage.employes.model.Pointage;
import com.pointage.employes.model.calendar.Calendars;
import com.pointage.employes.model.enums.CategoryName;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static com.pointage.employes.operation.Salary.*;
import static org.junit.Assert.assertEquals;

public class SalaryTests {
    private static final Logger LOGGER = Logger.getLogger(SalaryTests.class.getName());
    private List<Pointage> pointages;
    private Calendars calendars;
    private Employe rakoto;
    private Employe rabe;

    @Before
    public void setUp() {
        // Initialize pointage data
        pointages = new ArrayList<>();
        LocalDateTime dateTime1 = LocalDateTime.of(2024, 6, 1, 12, 0);
        LocalDateTime dateTime2 = LocalDateTime.of(2024, 6, 2, 12, 0);
        LocalDateTime dateTime3 = LocalDateTime.of(2024, 6, 1, 12, 0);
        LocalDateTime dateTime4 = LocalDateTime.of(2024, 6, 2, 12, 0);

        pointages.add(new Pointage(dateTime1, 8, null, false));
        pointages.add(new Pointage(dateTime2, 8, null, false));
        pointages.add(new Pointage(dateTime3, 8, null, true));
        pointages.add(new Pointage(dateTime4, 8, null, true));

        // Initialize holidays
        List<LocalDate> holidaysList = new ArrayList<>();
        holidaysList.add(LocalDate.of(2024, 6, 10));

        // Create Calendars object with holidays
        calendars = new Calendars(null, holidaysList);

        // Create employees Rakoto and Rabe with their categories
        Date birthDateRakoto = Date.from(LocalDate.of(1980, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date hireDateRakoto = Date.from(LocalDate.of(2000, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDateRakoto = Date.from(LocalDate.of(2025, 12, 31).atStartOfDay(ZoneId.systemDefault()).toInstant());

        Date birthDateRabe = Date.from(LocalDate.of(1985, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date hireDateRabe = Date.from(LocalDate.of(2005, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDateRabe = Date.from(LocalDate.of(2025, 12, 31).atStartOfDay(ZoneId.systemDefault()).toInstant());

        rakoto = new Employe("Rakoto", "R", "001", birthDateRakoto, hireDateRakoto, endDateRakoto, 100000.0,
                new Category(CategoryName.caretaker, 8 * 30, 100000.0, 0.0));
        rabe = new Employe("Rabe", "R", "002", birthDateRabe, hireDateRabe, endDateRabe, 120000.0,
                new Category(CategoryName.caretaker, 6, 120000.0, 0.0));
    }

    @Test
    public void test_calculate_day_worker_salary_for_Rakoto() {
        double rakotoDaySalary = getDayWorkerSalary(calendars, rakoto, pointages);
        LOGGER.info("Day salary for Rakoto: " + rakotoDaySalary);
        assertEquals("Day salary for Rakoto", 240000.0, rakotoDaySalary, 0.01);
    }

    @Test
    public void test_calculate_night_worker_salary_for_Rabe() {
        double rabeNightSalary = getNightWorkerSalary(calendars, rabe, pointages);
        LOGGER.info("Night salary for Rabe: " + rabeNightSalary);
        assertEquals("Night salary for Rabe", 249600.0, rabeNightSalary, 0.01);
    }

    @Test
    public void test_Calculate_adjusted_salary_for_Rakoto() {
        pointages.add(new Pointage(LocalDateTime.of(2024, 6, 25, 10, 0), 16, null, false));
        pointages.add(new Pointage(LocalDateTime.of(2024, 6, 26, 8, 0), 16, null, false));

        double rakotoAdjustedSalary = getDayWorkerSalary(calendars, rakoto, pointages);
        LOGGER.info("Adjusted salary for Rakoto: " + rakotoAdjustedSalary);
        assertEquals("Adjusted salary for Rakoto", 320000.0, rakotoAdjustedSalary, 0.01);
    }

    @Test
    public void test_calculate_adjusted_salary_for_Rabe() {
        pointages.add(new Pointage(LocalDateTime.of(2024, 6, 25, 8, 0), 16, null, false));
        pointages.add(new Pointage(LocalDateTime.of(2024, 6, 26, 8, 0), 16, null, false));

        double rabeAdjustedSalary = getNightWorkerSalary(calendars, rabe, pointages);
        LOGGER.info("Adjusted salary for Rabe: " + rabeAdjustedSalary);
        assertEquals("Adjusted salary for Rabe", 249600.0, rabeAdjustedSalary, 0.01);
    }

    @Test
    public void test_total_hours_in_month_for_Rakoto() {
        int totalHoursInMonth = calculateTotalHoursInMonth(8, 30, 16);
        assertEquals("Total hours for Rakoto", 256, totalHoursInMonth);
    }

    @Test
    public void test_total_hours_in_month_for_Rabe() {
        int totalHoursInMonth = calculateTotalHoursInMonth(8, 28, 0);
        assertEquals("Total hours for Rabe", 224, totalHoursInMonth);
    }
}
