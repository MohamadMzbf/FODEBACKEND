package com.fodev2.backendv2Fode.dto;

import java.util.List;

public class StudentResponse {

    private Integer  id ;
    private String    username   ;
    private String  firstname   ;
    private String  lastname  ;
    private String fullname   ;
    private String email ;
    private String  department   ;
    private Long  firstaccess ;
    private Long lastaccess  ;
    private Long  lastcourseaccess  ;
    private String  description   ;
    private Integer  descriptionformat ;
    private String  country  ;
    private String  profileimageurlsmall   ;
    private String profileimageurl   ;

    private List<CustomsField> customfields;

    private List<EnrolledCourse>  enrolledcourses;

    private List<Role>  roles;

    private String city;


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getFirstaccess() {
        return firstaccess;
    }

    public void setFirstaccess(Long firstaccess) {
        this.firstaccess = firstaccess;
    }

    public Long getLastaccess() {
        return lastaccess;
    }

    public void setLastaccess(Long lastaccess) {
        this.lastaccess = lastaccess;
    }

    public Long getLastcourseaccess() {
        return lastcourseaccess;
    }

    public void setLastcourseaccess(Long lastcourseaccess) {
        this.lastcourseaccess = lastcourseaccess;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProfileimageurlsmall() {
        return profileimageurlsmall;
    }

    public void setProfileimageurlsmall(String profileimageurlsmall) {
        this.profileimageurlsmall = profileimageurlsmall;
    }

    public String getProfileimageurl() {
        return profileimageurl;
    }

    public void setProfileimageurl(String profileimageurl) {
        this.profileimageurl = profileimageurl;
    }

    public List<CustomsField> getCustomfields() {
        return customfields;
    }

    public void setCustomfields(List<CustomsField> customfields) {
        this.customfields = customfields;
    }

    public List<EnrolledCourse> getEnrolledcourses() {
        return enrolledcourses;
    }

    public void setEnrolledcourses(List<EnrolledCourse> enrolledcourses) {
        this.enrolledcourses = enrolledcourses;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}


class Group {
    // Vous pouvez préciser le type approprié si nécessaire
}

