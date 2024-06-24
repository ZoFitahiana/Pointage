package com.pointage.employes.model;

import com.pointage.employes.model.enums.CategoryName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Category {
    private CategoryName name;
    private int hourWorkWeek;
    private Double salaryWeek;
    private Double indemniser;
}
