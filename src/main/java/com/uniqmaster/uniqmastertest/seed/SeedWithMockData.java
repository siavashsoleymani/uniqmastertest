package com.uniqmaster.uniqmastertest.seed;

import com.uniqmaster.uniqmastertest.model.entity.Audience;
import com.uniqmaster.uniqmastertest.model.entity.Organizer;
import com.uniqmaster.uniqmastertest.repository.AudienceRepository;
import com.uniqmaster.uniqmastertest.repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class SeedWithMockData {
  private final AudienceRepository audienceRepository;
  private final OrganizerRepository organizerRepository;

  @Autowired
  public SeedWithMockData(
      AudienceRepository audienceRepository,
      OrganizerRepository organizerRepository) {
    this.audienceRepository = audienceRepository;
    this.organizerRepository = organizerRepository;
  }

  @EventListener
  public void seedWithMockData(ContextRefreshedEvent event) {
    if (audienceRepository.count() == 0) {
      Audience audience = new Audience();
      audience.setName("siavosh");
      audience.setEmail("siavosh@testmail.com");
      audienceRepository.save(audience);
    }
    if (organizerRepository.count() == 0) {
      Organizer organizer = new Organizer();
      organizer.setName("Udemy");
      organizer.setEmail("test@udemy.com");
      organizerRepository.save(organizer);
    }
  }
}
