package com.uniqmaster.uniqmastertest.model.dto.req;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventSubscriptionReqDto {
  @NotNull
  private Long audienceId;
}