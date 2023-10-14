package com.fodev2.backendv2Fode.dto;

import com.fodev2.backendv2Fode.models.Beneficiary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ApplicantRequest {
    private Beneficiary IUF;
}
