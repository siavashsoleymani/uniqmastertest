package com.uniqmaster.uniqmastertest.repository;

import com.uniqmaster.uniqmastertest.model.entity.Audience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AudienceRepository extends JpaRepository<Audience, Long> {
}
