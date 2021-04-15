package com.uniqmaster.uniqmastertest.model.dto.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uniqmaster.uniqmastertest.model.entity.EventType;
import java.sql.Timestamp;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventReqDto {
  @NotBlank
  private String topic;
  @NotNull
  private EventType eventType;
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private Timestamp start;
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private Timestamp end;
  @NotNull
  private Long organizerId;
}
