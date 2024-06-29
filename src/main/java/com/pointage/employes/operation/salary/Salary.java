package com.pointage.employes.operation.salary;

import com.pointage.employes.model.Employe;
import com.pointage.employes.model.Pointage;
import com.pointage.employes.model.calendar.Calendars;

import java.time.LocalDate;
import java.util.List;

public class Salary {
    public static  double getSalaryDay(Employe employe){
        return employe.getSalary()/7;
    }
    public static  double getSalaryNight(Employe employe){
        return (employe.getSalary()*1.3)/7;
    }
    public static  double getSalaryDayMonth(Employe employe, List<Pointage> pointage,Calendars calendars){
     double salary = 0.0;
     int countHoliday =calendars.getHolidaysList().size() ;
     int isNotHoliday = (pointage.size())-calendars.getHolidaysList().size() ;
        salary = ((isNotHoliday*getSalaryDay(employe)))+((getSalaryDay(employe))*countHoliday*1.3);
        return salary;
    }
    public static  double getSalaryNightMonth(Employe employe, List<Pointage> pointage,Calendars calendars){
        double salary = 0.0;
        int countHoliday =calendars.getHolidaysList().size() ;
        int isNotHoliday = (pointage.size())-calendars.getHolidaysList().size() ;
        salary = ((isNotHoliday*getSalaryNight(employe)))+((getSalaryNight(employe))*countHoliday*1.3);
        return salary;
    }
    public  static  double getSalaireNet(Double salaryBrut){
        return salaryBrut*0.8;
    }

}
