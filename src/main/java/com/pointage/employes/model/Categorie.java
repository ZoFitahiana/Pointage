package com.pointage.employes.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
public class Categorie {
    private CategorieNom nom ;
    private Time heureTravailSemaine;
    private Double salaireParSemaine;
    private Double indemnite ;

}
