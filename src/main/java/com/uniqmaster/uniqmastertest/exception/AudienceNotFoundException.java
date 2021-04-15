package com.uniqmaster.uniqmastertest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Audience with provided id was not found")
public class AudienceNotFoundException extends RuntimeException{
}
