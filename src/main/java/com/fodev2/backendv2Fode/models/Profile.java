package com.fodev2.backendv2Fode.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
//@AllArgsConstructor
@Builder
@Data
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonIgnore
    @OneToMany(mappedBy = "profile")
    private List<Beneficiary> beneficiaries;
    public Profile(){
    }

    public Profile(Long id, List<Beneficiary> beneficiaries) {
        this.id = id;
        this.beneficiaries = beneficiaries;
    }

    //on cree methode ToString pour recupere ID et l'envoyer au BeneficiaryService pour l'integrer au iuf
    public String toString() {
        Long x = this.id;
        return x.toString();
    }
}