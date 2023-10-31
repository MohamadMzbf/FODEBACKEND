package com.fodev2.backendv2Fode.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.awt.*;
import java.util.List;


public class CoursResponse {
    private Integer id;
    private String shortname;
    private Integer categoryid;
    private Integer categorysortorder;
    private String fullname;
    private String displayname;
    private String idnumber;
    private String summary;
    private Integer summaryformat;
    private String format;
    private Integer showgrades;
    private Integer newsitems;
    private Long startdate;
    private Long enddate;
    private Integer numsections;
    private Long maxbytes;
    private Integer showreports;
    private Integer visible;
    private Integer groupmode;
    private Integer groupmodeforce;
    private Integer defaultgroupingid;
    private Long timecreated;
    private Long timemodified;
    private Integer enablecompletion;
    private Integer completionnotify;
    private String lang;
    private String forcetheme;
//    private List<CourseFormatOption> courseformatoptions;
    private Boolean showactivitydates;
    private Object showcompletionconditions;

    // Getters and setters...

    public void setId(Integer id) {
        this.id = id;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public void setCategorysortorder(Integer categorysortorder) {
        this.categorysortorder = categorysortorder;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setSummaryformat(Integer summaryformat) {
        this.summaryformat = summaryformat;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setShowgrades(Integer showgrades) {
        this.showgrades = showgrades;
    }

    public void setNewsitems(Integer newsitems) {
        this.newsitems = newsitems;
    }

    public void setStartdate(Long startdate) {
        this.startdate = startdate;
    }

    public void setEnddate(Long enddate) {
        this.enddate = enddate;
    }

    public void setNumsections(Integer numsections) {
        this.numsections = numsections;
    }

    public void setMaxbytes(Long maxbytes) {
        this.maxbytes = maxbytes;
    }

    public void setShowreports(Integer showreports) {
        this.showreports = showreports;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public void setGroupmode(Integer groupmode) {
        this.groupmode = groupmode;
    }

    public void setGroupmodeforce(Integer groupmodeforce) {
        this.groupmodeforce = groupmodeforce;
    }

    public void setDefaultgroupingid(Integer defaultgroupingid) {
        this.defaultgroupingid = defaultgroupingid;
    }

    public void setTimecreated(Long timecreated) {
        this.timecreated = timecreated;
    }

    public void setTimemodified(Long timemodified) {
        this.timemodified = timemodified;
    }

    public void setEnablecompletion(Integer enablecompletion) {
        this.enablecompletion = enablecompletion;
    }

    public void setCompletionnotify(Integer completionnotify) {
        this.completionnotify = completionnotify;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void setForcetheme(String forcetheme) {
        this.forcetheme = forcetheme;
    }

//    public void setCourseformatoptions(List<CourseFormatOption> courseformatoptions) {
//        this.courseformatoptions = courseformatoptions;
//    }

    public void setShowactivitydates(Boolean showactivitydates) {
        this.showactivitydates = showactivitydates;
    }

    public void setShowcompletionconditions(Object showcompletionconditions) {
        this.showcompletionconditions = showcompletionconditions;
    }

    public Integer getId() {
        return id;
    }

    public String getShortname() {
        return shortname;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public Integer getCategorysortorder() {
        return categorysortorder;
    }

    public String getFullname() {
        return fullname;
    }

    public String getDisplayname() {
        return displayname;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public String getSummary() {
        return summary;
    }

    public Integer getSummaryformat() {
        return summaryformat;
    }

    public String getFormat() {
        return format;
    }

    public Integer getShowgrades() {
        return showgrades;
    }

    public Integer getNewsitems() {
        return newsitems;
    }

    public Long getStartdate() {
        return startdate;
    }

    public Long getEnddate() {
        return enddate;
    }

    public Integer getNumsections() {
        return numsections;
    }

    public Long getMaxbytes() {
        return maxbytes;
    }

    public Integer getShowreports() {
        return showreports;
    }

    public Integer getVisible() {
        return visible;
    }

    public Integer getGroupmode() {
        return groupmode;
    }

    public Integer getGroupmodeforce() {
        return groupmodeforce;
    }

    public Integer getDefaultgroupingid() {
        return defaultgroupingid;
    }

    public Long getTimecreated() {
        return timecreated;
    }

    public Long getTimemodified() {
        return timemodified;
    }

    public Integer getEnablecompletion() {
        return enablecompletion;
    }

    public Integer getCompletionnotify() {
        return completionnotify;
    }

    public String getLang() {
        return lang;
    }

    public String getForcetheme() {
        return forcetheme;
    }

//    public List<CourseFormatOption> getCourseformatoptions() {
//        return courseformatoptions;
//    }

    public Boolean getShowactivitydates() {
        return showactivitydates;
    }

    public Object getShowcompletionconditions() {
        return showcompletionconditions;
    }
}
class CourseFormatOption {
    private String name;
    private Integer value;

    // Getters and setters...

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}