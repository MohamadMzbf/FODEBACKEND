package com.fodev2.backendv2Fode.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data

public class Beneficiary {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
    @Id
    private String IUF;
    private String firstname;
    private String lastname;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateBirth;
    //    private String city;
//    private String country;
    private String email;
    private String phoneNumberOne;
    private String phoneNumberTwo;

    //Les relations
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
    @ManyToOne
    @JoinColumn(name = "gender_id")
    private Gender gender;
//    @OneToOne
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

//    @OneToOne(mappedBy = "IUF")
//    private Applicant applicant;


//
//    public Beneficiary(Long id, Profile profile, String IUF, String firstname, String lastname, Date dateBirth, Gender gender, City city, Country country, String email, String phoneNumberOne, String phoneNumberTwo) {
//        this.id = id;
//        this.profile = profile;
//        this.IUF = IUF;
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.dateBirth = dateBirth;
//        this.gender = gender;
//        this.city = city;
//        this.country = country;
//        this.email = email;
//        this.phoneNumberOne = phoneNumberOne;
//        this.phoneNumberTwo = phoneNumberTwo;
//    }
//
//    public Beneficiary() {
//    }
//
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getIUF() {
//        return IUF;
//    }
//
//    public void setIUF(String IUF) {
//        this.IUF = IUF;
//    }
//
//    public String getFirstname() {
//        return firstname;
//    }
//
//    public void setFirstname(String firstname) {
//        this.firstname = firstname;
//    }
//
//    public String getLastname() {
//        return lastname;
//    }
//
//    public void setLastname(String lastname) {
//        this.lastname = lastname;
//    }
//
//    public Date getDateBirth() {
//        return dateBirth;
//    }
//
//    public void setDateBirth(Date dateBirth) {
//        this.dateBirth = dateBirth;
//    }
//
//    public Gender getGender() {
//        return gender;
//    }
//
//    public void setGender(Gender gender) {
//        this.gender = gender;
//    }
//
//    public City getCity() {
//        return city;
//    }
//
//    public void setCity(City city) {
//        this.city = city;
//    }
//
//    public Country getCountry() {
//        return country;
//    }
//
//    public void setCountry(Country country) {
//        this.country = country;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPhoneNumberOne() {
//        return phoneNumberOne;
//    }
//
//    public void setPhoneNumberOne(String phoneNumberOne) {
//        this.phoneNumberOne = phoneNumberOne;
//    }
//
//    public String getPhoneNumberTwo() {
//        return phoneNumberTwo;
//    }
//
//    public void setPhoneNumberTwo(String phoneNumberTwo) {
//        this.phoneNumberTwo = phoneNumberTwo;
//    }
//
//    public Profile getProfile() {
//        return profile;
//    }
//
//    public void setProfile(Profile profile) {
//        this.profile = profile;
//    }

}