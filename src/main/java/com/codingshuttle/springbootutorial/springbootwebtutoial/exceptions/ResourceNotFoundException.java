package com.codingshuttle.springbootutorial.springbootwebtutoial.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message){

        super(message);
    }
}
