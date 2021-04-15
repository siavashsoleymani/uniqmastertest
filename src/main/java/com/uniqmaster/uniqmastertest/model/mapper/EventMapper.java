package com.uniqmaster.uniqmastertest.model.mapper;

import com.uniqmaster.uniqmastertest.model.dto.req.CreateEventReqDto;
import com.uniqmaster.uniqmastertest.model.dto.res.CreateEventResDto;
import com.uniqmaster.uniqmastertest.model.dto.res.EventSubscriptionResDto;
import com.uniqmaster.uniqmastertest.model.entity.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {
  Event createEventReqDtoToEvent(CreateEventReqDto eventReqDto);

  CreateEventResDto eventToCreateEventReqDto(Event event);

  EventSubscriptionResDto eventToEventSubscriptionResDto(Event event);
}
