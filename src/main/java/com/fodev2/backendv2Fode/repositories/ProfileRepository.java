package com.fodev2.backendv2Fode.repositories;

import com.fodev2.backendv2Fode.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
}
