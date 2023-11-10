package com.fodev2.backendv2Fode.dto;

import lombok.Getter;

@Getter
public class ApprenantZone {

    private String zone;

    private Integer nombreEtudiant;

    public void setZone(String zone) {
        this.zone = zone;
    }

    public void setNombreEtudiant(Integer nombreEtudiant) {
        this.nombreEtudiant = nombreEtudiant;
    }
}
