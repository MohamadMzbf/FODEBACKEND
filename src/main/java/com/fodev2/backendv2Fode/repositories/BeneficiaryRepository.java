package com.fodev2.backendv2Fode.repositories;

import com.fodev2.backendv2Fode.models.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary,Long> {
    Beneficiary findByIUF(@Param("IUF") Beneficiary iuf);
}