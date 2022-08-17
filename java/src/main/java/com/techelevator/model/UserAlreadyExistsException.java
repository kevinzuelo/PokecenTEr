package com.techelevator.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.BAD_REQUEST, reason = "User Already Exists. Please Enter a new name.")
public class UserAlreadyExistsException extends RuntimeException {

}
