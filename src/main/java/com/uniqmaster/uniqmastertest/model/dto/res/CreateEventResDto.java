package com.uniqmaster.uniqmastertest.model.dto.res;

import com.uniqmaster.uniqmastertest.model.dto.req.CreateEventReqDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventResDto extends CreateEventReqDto {
  private Long id;
}
