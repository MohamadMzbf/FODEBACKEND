package com.fodev2.backendv2Fode.models;

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
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
//    private Integer city_id;
    @OneToMany(mappedBy = "city")
    private List<Beneficiary> beneficiaries;
//    private String city_name;
}