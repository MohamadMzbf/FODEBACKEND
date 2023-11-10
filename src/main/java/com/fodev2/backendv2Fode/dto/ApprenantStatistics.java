package com.fodev2.backendv2Fode.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ApprenantStatistics {

    private  Integer totalApprenant = 0;
    private Integer apprenantFeminin  = 0;
    private Integer apprenantMasculin  = 0;
    private  Integer genreNonDefini = 0;


    private  List<ApprenantZone> apprenantZones;

    public void setGenreNonDefini(Integer genreNonDefini) {
        this.genreNonDefini = genreNonDefini;
    }

    public void setApprenantZones(List<ApprenantZone> apprenantZones) {
        this.apprenantZones = apprenantZones;
    }

    public void setTotalApprenant(Integer totalApprenant) {
        this.totalApprenant = totalApprenant;
    }

    public void setApprenantFeminin(Integer apprenantFeminin) {
        this.apprenantFeminin = apprenantFeminin;
    }

    public void setApprenantMasculin(Integer apprenantMasculin) {
        this.apprenantMasculin = apprenantMasculin;
    }
}
