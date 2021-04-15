package com.uniqmaster.uniqmastertest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
  private String message = "SUCCESS";
  private T data;
}
