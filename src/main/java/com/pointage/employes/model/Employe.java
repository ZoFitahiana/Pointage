package com.pointage.employes.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Employe {
    private String nom ;
    private  String prenom ;
    private String matricule ;
    private Date DateNaissance;
    private Date DateEmbauche;
    private Date DateFinContrat ;
    private  Double salaire ;
    private  Categorie categorie ;
}
