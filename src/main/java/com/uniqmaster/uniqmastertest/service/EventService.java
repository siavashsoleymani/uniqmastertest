package com.uniqmaster.uniqmastertest.service;

import com.uniqmaster.uniqmastertest.model.dto.req.CreateEventReqDto;
import com.uniqmaster.uniqmastertest.model.dto.req.EventSubscriptionReqDto;
import com.uniqmaster.uniqmastertest.model.dto.res.CreateEventResDto;
import com.uniqmaster.uniqmastertest.model.dto.res.EventSubscriptionResDto;
import com.uniqmaster.uniqmastertest.model.dto.res.UpcomingEventsResDto;
import java.sql.Timestamp;

public interface EventService {
  CreateEventResDto createEvent(CreateEventReqDto createEventReqDto);

  EventSubscriptionResDto subscribeToEvent(EventSubscriptionReqDto eventSubscriptionReqDto, Long eventId);

  UpcomingEventsResDto getAllUpcomingEventsByAudienceId(Long audienceId);

  UpcomingEventsResDto getAllEventsByAudienceIdAndTimeBetween(Long audienceId, Timestamp start, Timestamp end);
}
