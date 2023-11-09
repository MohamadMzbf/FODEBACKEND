package com.fodev2.backendv2Fode.dto;

public class CustomField {
    private String type;
    private String value;
    private String name;
    private String shortname;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
