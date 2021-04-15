package com.uniqmaster.uniqmastertest.controller;

import com.uniqmaster.uniqmastertest.model.dto.Response;
import com.uniqmaster.uniqmastertest.model.dto.req.CreateEventReqDto;
import com.uniqmaster.uniqmastertest.model.dto.req.EventSubscriptionReqDto;
import com.uniqmaster.uniqmastertest.model.dto.res.CreateEventResDto;
import com.uniqmaster.uniqmastertest.model.dto.res.EventSubscriptionResDto;
import com.uniqmaster.uniqmastertest.model.dto.res.UpcomingEventsResDto;
import com.uniqmaster.uniqmastertest.service.EventService;
import java.sql.Timestamp;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {

  private final EventService eventService;

  @Autowired
  public EventController(EventService eventService) {
    this.eventService = eventService;
  }

  @PostMapping
  public ResponseEntity<Response<CreateEventResDto>> createNewEvent(
      @RequestBody @Valid CreateEventReqDto createEventReqDto) {
    CreateEventResDto event = eventService.createEvent(createEventReqDto);
    return ResponseEntity
        .ok(new Response<>("Event created successfully.", event));
  }

  @GetMapping(value = "/audience/{audienceId}", params = {"start", "end"})
  public ResponseEntity<Response<UpcomingEventsResDto>> getAllEvents(
      @PathVariable Long audienceId,
      @RequestParam("start") Timestamp start,
      @RequestParam("end") Timestamp end) {
    UpcomingEventsResDto upcomingEvents =
        eventService.getAllEventsByAudienceIdAndTimeBetween(audienceId, start, end);
    return ResponseEntity.ok(new Response<>("Upcoming events", upcomingEvents));
  }

  @GetMapping("/audience/{audienceId}/upcoming")
  public ResponseEntity<Response<UpcomingEventsResDto>> getUpcomingEvents(
      @PathVariable Long audienceId) {
    UpcomingEventsResDto upcomingEvents =
        eventService.getAllUpcomingEventsByAudienceId(audienceId);
    return ResponseEntity.ok(new Response<>("Upcoming events", upcomingEvents));
  }

  @PatchMapping("/{id}/audience")
  public ResponseEntity<Response<EventSubscriptionResDto>> subscribeToEvent(
      @RequestBody @Valid EventSubscriptionReqDto eventSubscriptionReqDto,
      @PathVariable Long id) {
    EventSubscriptionResDto eventSubscriptionResDto =
        eventService.subscribeToEvent(eventSubscriptionReqDto, id);
    return ResponseEntity.ok(new Response<>("You have subscribed to this event successfully.",
        eventSubscriptionResDto));
  }
}
