package com.fodev2.backendv2Fode.repositories;

import com.fodev2.backendv2Fode.models.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
}