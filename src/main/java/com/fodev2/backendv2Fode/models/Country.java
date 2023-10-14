package com.fodev2.backendv2Fode.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
//    private Integer Country_Id;
    @Column(length = 45)
    private String name;
    @Column(length = 5)
    private String PhoneCode;
    @Column(length = 255)
    private String flag;

//    @OneToOne(mappedBy = "Country_Id")
//    private Beneficiary beneficiary;

    @JsonIgnore
    @OneToMany(mappedBy = "country")
    private List<Beneficiary> beneficiaries;
}
