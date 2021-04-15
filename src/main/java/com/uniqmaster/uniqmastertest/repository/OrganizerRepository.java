package com.uniqmaster.uniqmastertest.repository;

import com.uniqmaster.uniqmastertest.model.entity.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, Long> {
}
