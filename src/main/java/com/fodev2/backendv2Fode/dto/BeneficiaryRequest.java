package com.fodev2.backendv2Fode.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fodev2.backendv2Fode.models.City;
import com.fodev2.backendv2Fode.models.Country;
import com.fodev2.backendv2Fode.models.Gender;
import com.fodev2.backendv2Fode.models.Profile;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BeneficiaryRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Profile profile;
    private String IUF;
    private String firstname;
    private String lastname;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateBirth;
    private Gender gender;
    private City city;
    private Country country;
    private String email;
    private String phoneNumberOne;
    private String phoneNumberTwo;
}
