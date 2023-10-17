package com.fodev2.backendv2Fode.services;

import com.fodev2.backendv2Fode.dto.EducationRequest;
import com.fodev2.backendv2Fode.models.Applicant;
import com.fodev2.backendv2Fode.models.Education;
import com.fodev2.backendv2Fode.repositories.ApplicantRepository;
import com.fodev2.backendv2Fode.repositories.EducationRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
@Builder
public class EducationService {
    @Autowired
    private final EducationRepository educationRepository;

    public void createEducation(EducationRequest educationRequest){
        Education education = Education.builder()
                .title(educationRequest.getTitle())
                .institution(educationRequest.getInstitution())
                .yearGraduation(educationRequest.getYearGraduation())
                .levelStudy(educationRequest.getLevelStudy())
                .IUF(educationRequest.getApplicant())
                .build();

        educationRepository.save(education);
        log.info("Education with IUF {} is saved", education.getIUF());
    }


    public void updateEducation(Integer Edu_id ,EducationRequest educationRequest){
        Optional<Education> optionalEducation = educationRepository.findById(Edu_id);

        if(optionalEducation.isPresent()){
           Education existingEducation = optionalEducation.get();

           existingEducation.setTitle(educationRequest.getTitle());
           existingEducation.setInstitution(educationRequest.getInstitution());
           existingEducation.setLevelStudy(educationRequest.getLevelStudy());
           existingEducation.setYearGraduation(educationRequest.getYearGraduation());
           existingEducation.setIUF(educationRequest.getApplicant());
            educationRepository.save(existingEducation);
        }else {
            log.info("Education with iuf {} is saved", educationRequest.getApplicant());
        }
    }

    private EducationRequest mapToEducationRequest(Education education){
        return EducationRequest.builder()
                .Education_id(education.getEducation_id())
                .title(education.getTitle())
                .institution(education.getInstitution())
                .levelStudy(education.getLevelStudy())
                .yearGraduation(education.getInstitution())
                .Applicant(education.getIUF())
                .build();
    }

}
