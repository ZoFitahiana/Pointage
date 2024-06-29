package com.pointage.employes.Employe.salary;

import com.pointage.employes.model.Category;
import com.pointage.employes.model.Employe;
import com.pointage.employes.model.Pointage;
import com.pointage.employes.model.calendar.Calendars;
import com.pointage.employes.model.enums.CategoryName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.pointage.employes.operation.salary.Salary.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalaryMonth {
    Category category = new Category(CategoryName.caretaker);

    Employe rabe = new Employe("Rabe", "Rabe", "RA002",new Date(),new Date(),new Date(), 100_000.00,category);
    Employe rakoto = new Employe("Rakoto", "Rakoto", "RA001",new Date(),new Date(),new Date(), 100_000.00,category);

    @Test
    public void get_salary_month_day(){
        List<Pointage> pointagesRakoto = new ArrayList<Pointage>();
        for (int i = 0; i < 42; i++) {
            pointagesRakoto.add(new Pointage(new Date(),10,rakoto,false));
        }

        List<Date> holidays = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            holidays.add(new Date());
        }

        Calendars calendar = new Calendars(new Date(),new Date(),holidays);
        double salary = getSalaryDayMonth(rakoto,pointagesRakoto,calendar);
        assertEquals(Math.round(612_856.959),Math.round(salary));
    }

    @Test
    public void get_salary_month_night(){
        List<Pointage> pointagesRabe = new ArrayList<Pointage>();
        for (int i = 0; i < 42; i++) {
            pointagesRabe.add(new Pointage(new Date(),14,rabe,true));
        }

        List<Date> holidays = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            holidays.add(new Date());
        }

        Calendars calendar = new Calendars(new Date(),new Date(),holidays);
        double salary = getSalaryNightMonth(rabe,pointagesRabe,calendar);
        assertEquals(Math.round(796_713.918),Math.round(salary));
    }
}
