package com.fodev2.backendv2Fode.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hobbie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Hobbie_id;

    @ManyToOne
    private Applicant applicant;
}
