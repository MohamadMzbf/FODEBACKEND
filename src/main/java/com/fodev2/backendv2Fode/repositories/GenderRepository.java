package com.fodev2.backendv2Fode.repositories;

import com.fodev2.backendv2Fode.models.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Gender,Long> {
}
