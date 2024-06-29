package com.pointage.employes.operation.time;

import com.pointage.employes.model.Category;
import com.pointage.employes.model.Employe;
import com.pointage.employes.model.Pointage;
import com.pointage.employes.model.calendar.Calendars;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Hours {
    public static int calculateTotalHours(List<Pointage> pointages) {
        return pointages.stream().mapToInt(Pointage::getTimeWork).sum();
    }
}
