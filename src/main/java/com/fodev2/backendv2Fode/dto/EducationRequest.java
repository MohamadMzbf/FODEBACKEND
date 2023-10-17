package com.fodev2.backendv2Fode.dto;

import com.fodev2.backendv2Fode.models.Applicant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EducationRequest {
    @Id
    private Integer Education_id;
    @Column(length = 45)
    private String title;
    @Column(length = 45)
    private String levelStudy;
    @Column(length = 45)
    private String yearGraduation;
    @Column(length = 45)
    private String institution;
    @ManyToOne
    private Applicant Applicant;
}
