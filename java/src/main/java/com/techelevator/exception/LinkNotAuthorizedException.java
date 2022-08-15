package com.techelevator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.BAD_REQUEST, reason = "The key does not match")
public class LinkNotAuthorizedException extends Exception{
    public LinkNotAuthorizedException(String message) {super(message);};
}
