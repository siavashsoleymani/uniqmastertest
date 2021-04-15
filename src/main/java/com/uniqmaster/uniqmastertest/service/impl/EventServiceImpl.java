package com.uniqmaster.uniqmastertest.service.impl;

import com.uniqmaster.uniqmastertest.exception.AudienceNotFoundException;
import com.uniqmaster.uniqmastertest.exception.EventNotFoundException;
import com.uniqmaster.uniqmastertest.exception.OrganizerNotFoundException;
import com.uniqmaster.uniqmastertest.model.dto.req.CreateEventReqDto;
import com.uniqmaster.uniqmastertest.model.dto.req.EventSubscriptionReqDto;
import com.uniqmaster.uniqmastertest.model.dto.res.CreateEventResDto;
import com.uniqmaster.uniqmastertest.model.dto.res.EventSubscriptionResDto;
import com.uniqmaster.uniqmastertest.model.dto.res.UpcomingEventsResDto;
import com.uniqmaster.uniqmastertest.model.entity.Audience;
import com.uniqmaster.uniqmastertest.model.entity.Event;
import com.uniqmaster.uniqmastertest.model.entity.Organizer;
import com.uniqmaster.uniqmastertest.model.mapper.EventMapper;
import com.uniqmaster.uniqmastertest.repository.AudienceRepository;
import com.uniqmaster.uniqmastertest.repository.EventRepository;
import com.uniqmaster.uniqmastertest.repository.OrganizerRepository;
import com.uniqmaster.uniqmastertest.service.EmailService;
import com.uniqmaster.uniqmastertest.service.EventService;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

  private final EventRepository eventRepository;
  private final AudienceRepository audienceRepository;
  private final EventMapper eventMapper;
  private final EmailService emailService;
  private final OrganizerRepository organizerRepository;

  @Autowired
  public EventServiceImpl(EventRepository eventRepository,
                          AudienceRepository audienceRepository,
                          EventMapper eventMapper,
                          EmailService emailService,
                          OrganizerRepository organizerRepository) {
    this.eventRepository = eventRepository;
    this.audienceRepository = audienceRepository;
    this.eventMapper = eventMapper;
    this.emailService = emailService;
    this.organizerRepository = organizerRepository;
  }

  @Override
  public CreateEventResDto createEvent(CreateEventReqDto createEventReqDto) {
    Organizer organizer = organizerRepository.findById(createEventReqDto.getOrganizerId())
        .orElseThrow(OrganizerNotFoundException::new);
    Event event = eventMapper.createEventReqDtoToEvent(createEventReqDto);
    event.getOrganizers().add(organizer);
    Event savedEvent = eventRepository.save(event);
    return eventMapper.eventToCreateEventReqDto(savedEvent);
  }

  @Override
  public EventSubscriptionResDto subscribeToEvent(EventSubscriptionReqDto eventSubscriptionReqDto,
                                                  Long eventId) {
    Event event = eventRepository.findById(eventId).orElseThrow(EventNotFoundException::new);
    Audience audience = audienceRepository.findById(eventSubscriptionReqDto.getAudienceId())
        .orElseThrow(AudienceNotFoundException::new);
    event.getAudiences().add(audience);
    Event updatedEvent = eventRepository.save(event);
    emailService.sendEmailWithSubjectWithMessageTo("Yeay new event new things to lean :)",
        "You have added to the: " + event.getTopic(), audience.getEmail());
    return eventMapper.eventToEventSubscriptionResDto(updatedEvent);
  }

  @Override
  public UpcomingEventsResDto getAllUpcomingEventsByAudienceId(Long audienceId) {
    List<Event> upcomingEvents =
        eventRepository.findByEndBeforeAndAudiencesIdOrderByStartDescLimit(
            new Timestamp(System.currentTimeMillis()), audienceId, PageRequest.of(0, 10));
    return new UpcomingEventsResDto(
        upcomingEvents.stream()
            .map(eventMapper::eventToEventSubscriptionResDto).collect(Collectors.toList()));
  }

  @Override
  public UpcomingEventsResDto getAllEventsByAudienceIdAndTimeBetween(Long audienceId,
                                                                     Timestamp start,
                                                                     Timestamp end) {
    List<Event> upcomingEvents =
        eventRepository.findByAudienceIdStartBetween(audienceId, start, end);
    return new UpcomingEventsResDto(
        upcomingEvents.stream()
            .map(eventMapper::eventToEventSubscriptionResDto).collect(Collectors.toList()));
  }
}
