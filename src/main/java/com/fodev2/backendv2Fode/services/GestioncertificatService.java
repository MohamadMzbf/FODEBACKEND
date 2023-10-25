package com.fodev2.backendv2Fode.services;

import com.fodev2.backendv2Fode.MoodleConfig;
import com.fodev2.backendv2Fode.dto.CertificatResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
@Builder
public class GestioncertificatService {


    private final MoodleConfig moodleConfig = new MoodleConfig();


    public List<CertificatResponse>  getListCertificat(){
        RestTemplate restTemplate = new RestTemplate();
        String wsfunction = "core_course_get_categories";

        ResponseEntity<CertificatResponse[]> certificatResponses = restTemplate.getForEntity(moodleConfig.serveurUrl+"?wstoken={token}&wsfunction={function}&moodlewsrestformat=json",CertificatResponse[].class,moodleConfig.wstoken,wsfunction);

        CertificatResponse[] certificatResponses1 = certificatResponses.getBody();

        return List.of(certificatResponses1);
    }


}
