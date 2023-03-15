package com.example.user.error;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id){
        super("User " + id + "Not found!");
    }
}
