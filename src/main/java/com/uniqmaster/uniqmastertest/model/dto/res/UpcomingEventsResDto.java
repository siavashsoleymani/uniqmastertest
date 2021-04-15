package com.uniqmaster.uniqmastertest.model.dto.res;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpcomingEventsResDto {
  private List<EventSubscriptionResDto> upcomingEvents;
}
