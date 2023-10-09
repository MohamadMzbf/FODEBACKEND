package com.fodev2.backendv2Fode.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Entity
@Data
public class Beneficiary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long profile;
    private String IUF;

    private String firstname;
    private String lastname;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateBirth;
    private Long gender;
    private String city;
    private String country;
    private String email;
    private String phoneNumberOne;
    private String phoneNumberTwo;

    public Beneficiary(Long id, Long profile, String IUF, String firstname, String lastname, Date dateBirth, Long gender, String city, String country, String email, String phoneNumberOne, String phoneNumberTwo) {
        this.id = id;
        this.profile = profile;
        this.IUF = IUF;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateBirth = dateBirth;
        this.gender = gender;
        this.city = city;
        this.country = country;
        this.email = email;
        this.phoneNumberOne = phoneNumberOne;
        this.phoneNumberTwo = phoneNumberTwo;
    }

    public Beneficiary() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIUF() {
        return IUF;
    }

    public void setIUF(String IUF) {
        this.IUF = IUF;
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

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public Long getGender() {
        return gender;
    }

    public void setGender(Long gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumberOne() {
        return phoneNumberOne;
    }

    public void setPhoneNumberOne(String phoneNumberOne) {
        this.phoneNumberOne = phoneNumberOne;
    }

    public String getPhoneNumberTwo() {
        return phoneNumberTwo;
    }

    public void setPhoneNumberTwo(String phoneNumberTwo) {
        this.phoneNumberTwo = phoneNumberTwo;
    }

    public Long getProfile() {
        return profile;
    }

    public void setProfile(Long profile) {
        this.profile = profile;
    }
}
