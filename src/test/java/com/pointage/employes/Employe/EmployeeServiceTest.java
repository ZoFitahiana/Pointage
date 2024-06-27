package com.pointage.employes.Employe;
import com.pointage.employes.model.Category;
import com.pointage.employes.model.Employe;
import com.pointage.employes.model.Pointage;
import com.pointage.employes.model.calendar.Calendars;
import com.pointage.employes.model.enums.CategoryName;
import com.pointage.employes.operation.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeServiceTest {
    private List<Pointage> pointages;
    private Calendars calendar;
    private Employe rakoto;
    private Employe rabe;
    private Category gardienCategory;

    @BeforeEach
    public void setUp() {
        // Initialize the calendar with holidays
        calendar = new Calendars(null, Arrays.asList(LocalDate.of(2024, 6, 17), LocalDate.of(2024, 6, 25), LocalDate.of(2024, 6, 26)));
        // Create categories
        gardienCategory = new Category(CategoryName.caretaker, 56, 100000.0, 0.0);

        // Create employees
        Date birthDateRakoto = Date.from(LocalDate.of(1980, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date hireDateRakoto = Date.from(LocalDate.of(2000, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDateRakoto = Date.from(LocalDate.of(2025, 12, 31).atStartOfDay(ZoneId.systemDefault()).toInstant());

        Date birthDateRabe = Date.from(LocalDate.of(1985, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date hireDateRabe = Date.from(LocalDate.of(2005, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDateRabe = Date.from(LocalDate.of(2025, 12, 31).atStartOfDay(ZoneId.systemDefault()).toInstant());

        rakoto = new Employe("Rakoto", "Rakoto", "RA001",birthDateRakoto,hireDateRakoto,endDateRakoto, 100000.0, gardienCategory);
        rabe = new Employe("Rabe", "Rabe", "RA002", birthDateRabe,hireDateRabe,endDateRabe, 100000.0, gardienCategory);

        // Create pointages
        pointages = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            pointages.add(new Pointage(LocalDateTime.of(2024, 6, i + 1, 8, 0), 8, rakoto, false));
            pointages.add(new Pointage(LocalDateTime.of(2024, 6, i + 1, 20, 0), 8, rabe, true));
        }
    }

    @Test
    public void test_calculate_total_hours() {
        int totalHours = EmployeeService.calculateTotalHours(pointages);
        assertEquals(480, totalHours);
    }

    @Test
    public void test_calculate_hour_rate() {
        double hourlyRate = EmployeeService.calculateHourlyRate(gardienCategory);
        assertEquals(100000.0 / 56, hourlyRate);
    }

    @Test
    public void test_calculate_salaries() {
        List<Employe> employees = Arrays.asList(rakoto, rabe);
        Map<String, Double> salaries = EmployeeService.calculateSalaries(employees, pointages, calendar);

        // Assuming correct values calculated manually or using a trusted source
        double expectedSalary =624571.0;

        assertEquals(expectedSalary,Math.round( salaries.get("RA001")));
        assertEquals(expectedSalary,Math.round( salaries.get("RA002")));
    }
}

