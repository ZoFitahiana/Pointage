package com.pointage.employes.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Pointage {
    private LocalDateTime date;
    private int timeWork;
    private Employe employe;
    private boolean isNightWork;

    public Pointage(LocalDateTime date, int timeWork, Employe employe, boolean isNightWork) {
        this.date = date;
        this.timeWork = Math.min(timeWork, 20);
        this.employe = employe;
        this.isNightWork = isNightWork;
    }

}
