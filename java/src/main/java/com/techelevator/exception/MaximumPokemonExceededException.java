package com.techelevator.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.BAD_REQUEST, reason = "Could not add pokemon. Must upgrade to premium to have more than 50 pokemon.")
public class MaximumPokemonExceededException extends Exception{
   public MaximumPokemonExceededException(String message) {super(message);};
}
