package com.pointage.employes.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Pointage {
    private LocalDateTime date;
    private int timeWork;
    private Employe employe;
    private boolean isNightWork;
}
