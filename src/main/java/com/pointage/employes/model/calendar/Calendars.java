package com.pointage.employes.model.calendar;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class Calendars {

    private Date startDate ;
    private Date endDate ;
    private List<Date> holidaysList;

    public boolean isHoliday(Date date) {
        return holidaysList.contains(date);
    }
}
