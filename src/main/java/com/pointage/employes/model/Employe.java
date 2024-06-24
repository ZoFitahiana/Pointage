package com.pointage.employes.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Employe {
    private String name;
    private  String firstName ;
    private String matricule ;
    private Date Birthdate;
    private Date StartDate;
    private Date ContractEndDate;
    private  Double salary ;
    private Category category ;

    public  double getSalaireNet(){
        return  (salary  * 0.8);
    }
}
