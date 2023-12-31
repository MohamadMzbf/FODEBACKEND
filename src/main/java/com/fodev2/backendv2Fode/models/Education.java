package com.fodev2.backendv2Fode.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column( nullable = false)
    private Integer Education_id;
    @Column(length = 45)
    private String title;
    @Column(length = 45)
    private String levelStudy;
    @Column(length = 45)
    private String yearGraduation;
    @Column(length = 45)
    private String institution;
//    @Column(name = "IUF")
    @ManyToOne
    private Applicant IUF;
}