package com.fodev2.backendv2Fode.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@Builder
@Data
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(mappedBy = "gender")
    private List<Beneficiary> beneficiaries;

    public Gender(){
    }

    public String toString() {
        Long x = this.id;
        return x.toString();
    }
}
