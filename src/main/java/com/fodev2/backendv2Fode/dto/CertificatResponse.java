package com.fodev2.backendv2Fode.dto;

import java.util.TimeZone;

public class CertificatResponse {

    private Integer id;


    private String name;
    private String idnumber;

    private String description;

    private String descriptionformat;
    private Long parent;

    private Long sortorder ;
    private Long coursecount ;

    private Boolean visible ;

    private Boolean visibleold;

    private TimeZone timemodified;

    private String path ;

    private String theme;


    public Integer getId(){return id ;}


    public String getName() {
        return name;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public String getDescription() {
        return description;
    }

    public String getDescriptionformat() {
        return descriptionformat;
    }

    public Long getParent() {
        return parent;
    }

    public Long getSortorder() {
        return sortorder;
    }

    public Long getCoursecount() {
        return coursecount;
    }

    public Boolean getVisible() {
        return visible;
    }

    public TimeZone getTimemodified() {
        return timemodified;
    }

    public String getPath() {
        return path;
    }

    public String getTheme() {
        return theme;
    }


    @Override
    public String toString() {
        return "CertificatResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", idnumber='" + idnumber + '\'' +
                ", description='" + description + '\'' +
                ", descriptionformat='" + descriptionformat + '\'' +
                ", parent=" + parent +
                ", sortorder=" + sortorder +
                ", coursecount=" + coursecount +
                ", visible=" + visible +
                ", timemodified=" + timemodified +
                ", path='" + path + '\'' +
                ", theme='" + theme + '\'' +
                '}';
    }

}
