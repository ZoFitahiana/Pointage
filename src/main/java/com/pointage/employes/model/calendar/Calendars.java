package com.pointage.employes.model.calendar;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class Calendars {
    private Calendar calendar ;
    private List<LocalDate> holidaysList;

    public boolean isHoliday(LocalDate date) {
        return holidaysList.contains(date);
    }
    public List<Boolean> areHolidays(List<LocalDate> dates) {
        List<Boolean> results = new ArrayList<>();
        for (LocalDate date : dates) {
            results.add(isHoliday(date));
        }
        return results;
    }
}
