package com.fodev2.backendv2Fode.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String IUF;

//    @OneToMany(mappedBy = "applicant")
//    private Collection<Profile> profile;
    @OneToMany(mappedBy = "IUF")
    private Collection<Education> education;
    @OneToMany(mappedBy = "applicant")
    private Collection<Experience> experience;
    @OneToMany(mappedBy = "applicant")
    private Collection<Skill> skill;
    @OneToMany(mappedBy = "applicant")
    private Collection<Reference> reference;

    @OneToMany(mappedBy = "applicant")
    private Collection<Interest> interest;

    @OneToMany(mappedBy = "applicant")
    private Collection<Hobbie> hobbie;

    @OneToMany(mappedBy = "applicant")
    private Collection<Language> language;

    @OneToMany(mappedBy = "applicant")
    private Collection<Competence> competence;

}
