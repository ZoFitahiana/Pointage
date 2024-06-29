package com.pointage.employes.Employe.salary;

import com.pointage.employes.model.Category;
import com.pointage.employes.model.Employe;
import com.pointage.employes.model.Pointage;
import com.pointage.employes.model.enums.CategoryName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.pointage.employes.operation.salary.Salary.getSalaryDay;
import static com.pointage.employes.operation.salary.Salary.getSalaryNight;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalaryDayTest {
    Category category = new Category(CategoryName.caretaker);
    Employe rabe = new Employe("Rabe", "Rabe", "RA002",new Date(),new Date(),new Date(), 100_000.00,category);
    Employe rakoto = new Employe("Rakoto", "Rakoto", "RA001",new Date(),new Date(),new Date(), 100_000.00,category);
    @Test
    public void get_salary_days(){
        List<Pointage> pointagesRakoto = new ArrayList<Pointage>();
        for (int i = 0; i < 42; i++) {
            pointagesRakoto.add(new Pointage(new Date(),10,rakoto,false));
        }
        Double salaryDayRakoto = getSalaryDay(rakoto);
        assertEquals(Math.round(14_285.71),Math.round(salaryDayRakoto));
    }
    @Test
    public void get_salary_night(){
        List<Pointage> pointagesRabe = new ArrayList<Pointage>();
        for (int i = 0; i < 42; i++) {
            pointagesRabe.add(new Pointage(new Date(),14,rabe,true));
        }
        Double salaryDayRakoto = getSalaryNight(rabe);
        assertEquals(Math.round(18_571.42),Math.round(salaryDayRakoto));
    }

}
