package com.pointage.employes.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class Pointage {
    private Date date;
    private int timeWork;
    private Employe employe;
    private boolean isNightWork;

    public Pointage(Date date, int timeWork, Employe employe, boolean isNightWork) {
        this.date = date;
        this.timeWork = Math.min(timeWork, 20);
        this.employe = employe;
        this.isNightWork = isNightWork;
    }

}
