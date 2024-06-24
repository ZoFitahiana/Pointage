package com.pointage.employes.operation;

import com.pointage.employes.model.Category;
import com.pointage.employes.model.Employe;
import com.pointage.employes.model.Pointage;
import com.pointage.employes.model.calendar.Calendars;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class Salary {
    public static int calculateTotalHoursInMonth(int dailyHours, int daysInMonth, int overtimeHoursInMonth) {
        return (dailyHours * daysInMonth) + overtimeHoursInMonth;
    }

    public static double calculateNormalHourlyRate(double weeklySalary, int weeklyHours) {
        return weeklySalary / weeklyHours;
    }

    public static double calculateSalary(double hourlyRate, double totalHours) {
        return hourlyRate * totalHours;
    }

    public static double calculateOvertimeSalary(double hourlyRate, double overtimeHours, double multiplier) {
        return hourlyRate * overtimeHours * multiplier;
    }

    public static double getDayWorkerSalary(Calendars calendars, Employe employe, List<Pointage> pointages) {
        double salary = employe.getSalary();
        Category category = employe.getCategory();
        int weeklyHours = category.getHourWorkWeek();
        double weeklySalary = category.getSalaryWeek();
        double normalHourlyRate = calculateNormalHourlyRate(weeklySalary, weeklyHours);

        double normalHoursWorked = 0;
        double sundayHoursWorked = 0;
        double holidayHoursWorked = 0;

        LocalDate startDate = LocalDate.of(2024, 6, 1);
        LocalDate endDate = LocalDate.of(2024, 6, 30);

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            int hoursWorked = 0;

            for (Pointage pointage : pointages) {
                if (pointage.getDate().toLocalDate().equals(date)) {
                    hoursWorked = pointage.getTimeWork();
                    break;
                }
            }

            if (calendars.isHoliday(date)) {
                holidayHoursWorked += hoursWorked;
            } else if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                sundayHoursWorked += hoursWorked;
            } else {
                normalHoursWorked += hoursWorked;
            }
        }

        double normalSalary = calculateSalary(normalHourlyRate, normalHoursWorked);
        double sundaySalary = calculateOvertimeSalary(normalHourlyRate, sundayHoursWorked, 1.4); // 140%
        double holidaySalary = calculateOvertimeSalary(normalHourlyRate, holidayHoursWorked, 1.5); // 150%

        return normalSalary + sundaySalary + holidaySalary;
    }

    public static double getNightWorkerSalary(Calendars calendars, Employe employe, List<Pointage> pointages) {
        double salary = employe.getSalary();
        Category category = employe.getCategory();
        int weeklyHours = category.getHourWorkWeek();
        double weeklySalary = category.getSalaryWeek();
        double normalHourlyRate = calculateNormalHourlyRate(weeklySalary, weeklyHours);

        double normalHoursWorked = 0;
        double nightHoursWorked = 0;
        double holidayHoursWorked = 0;

        LocalDate startDate = LocalDate.of(2024, 6, 1);
        LocalDate endDate = LocalDate.of(2024, 6, 30);
        List<LocalDate> holidaysList = calendars.getHolidaysList();

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            int hoursWorked = 0;

            for (Pointage pointage : pointages) {
                if (pointage.getDate().toLocalDate().equals(date) && pointage.isNightWork()) {
                    hoursWorked = pointage.getTimeWork();
                    break;
                }
            }

            if (calendars.isHoliday(date)) {
                holidayHoursWorked += hoursWorked;
            } else {
                nightHoursWorked += hoursWorked;
            }
        }

        double nightSalary = calculateOvertimeSalary(normalHourlyRate, nightHoursWorked, 1.3); // 130%
        double holidaySalary = calculateOvertimeSalary(normalHourlyRate, holidayHoursWorked, 1.5); // 150%

        return nightSalary + holidaySalary;
    }
}
