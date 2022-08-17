package com.techelevator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.BAD_REQUEST, reason = "User already exists, Please use another name!!")
public class UserExistsException extends Exception{
    public UserExistsException() {
        String message = "User already exists, Please use another name!!";}
}
