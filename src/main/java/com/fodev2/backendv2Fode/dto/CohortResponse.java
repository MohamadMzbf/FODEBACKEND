package com.fodev2.backendv2Fode.dto;

import java.util.List;

public class CohortResponse {
    private Integer id;
    private String name;
    private String idnumber;
    private String description;
    private Integer descriptionformat;
    private Boolean visible;
    private String theme;
    private List<CustomField> customfields;

    public static class CustomField {
        // Si des champs spécifiques sont présents dans les customfields, ajoutez-les ici
    }

    // Getters et Setters pour chaque champ

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDescriptionformat() {
        return descriptionformat;
    }

    public void setDescriptionformat(Integer descriptionformat) {
        this.descriptionformat = descriptionformat;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public List<CustomField> getCustomfields() {
        return customfields;
    }

    public void setCustomfields(List<CustomField> customfields) {
        this.customfields = customfields;
    }
}
