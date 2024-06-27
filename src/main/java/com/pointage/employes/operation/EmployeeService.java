package com.pointage.employes.operation;
import com.pointage.employes.model.Category;
import com.pointage.employes.model.Employe;
import com.pointage.employes.model.Pointage;
import com.pointage.employes.model.calendar.Calendars;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeService {
    private static final double TAX_RATE = 0.2;

    public static int calculateTotalHours(List<Pointage> pointages) {
        return pointages.stream().mapToInt(Pointage::getTimeWork).sum();
    }

    public static double calculateHourlyRate(Category category) {
        return category.getSalaryWeek() / category.getHourWorkWeek();
    }

    public static double calculateOvertimePay(int overtimeHours, double hourlyRate) {
        int hs30Hours = Math.min(overtimeHours, 8);
        int hs50Hours = Math.max(0, overtimeHours - 8);

        return hs30Hours * hourlyRate * 1.3 + hs50Hours * hourlyRate * 1.5;
    }

    public static double calculateHolidayPay(int holidayHours, double hourlyRate) {
        return holidayHours * hourlyRate * 1.5;
    }

    public static double calculateNightPay(int nightHours, double hourlyRate) {
        return nightHours * hourlyRate * 1.3;
    }

    public static double calculateSalary(Employe employe, List<Pointage> pointages, Calendars calendar) {
        Category category = employe.getCategory();
        double hourlyRate = calculateHourlyRate(category);

        int totalHours = calculateTotalHours(pointages);
        int normalHours = Math.min(totalHours, category.getHourWorkWeek());
        int overtimeHours = Math.min(totalHours - normalHours, 20);

        double normalPay = normalHours * hourlyRate;
        double overtimePay = calculateOvertimePay(overtimeHours, hourlyRate);

        int holidayHours = 0;
        int nightHours = 0;

        for (Pointage pointage : pointages) {
            LocalDate date = pointage.getDate().toLocalDate();
            if (calendar.isHoliday(date)) {
                holidayHours += pointage.getTimeWork();
            } else if (pointage.isNightWork()) {
                nightHours += pointage.getTimeWork();
            }
        }

        double holidayPay = calculateHolidayPay(holidayHours, hourlyRate);
        double nightPay = calculateNightPay(nightHours, hourlyRate);

        double grossSalary = normalPay + overtimePay + holidayPay + nightPay;
        double netSalary = grossSalary * (1 - TAX_RATE);

        return netSalary;
    }

    public static Map<String, Double> calculateSalaries(List<Employe> employees, List<Pointage> pointages, Calendars calendar) {
        Map<String, Double> salaries = new HashMap<>();
        for (Employe employe : employees) {
            double salary = calculateSalary(employe, pointages, calendar);
            salaries.put(employe.getMatricule(), salary);
        }
        return salaries;
    }
}
