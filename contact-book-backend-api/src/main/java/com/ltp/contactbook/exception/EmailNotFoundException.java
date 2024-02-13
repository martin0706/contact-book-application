package com.ltp.contactbook.exception;

public class EmailNotFoundException extends RuntimeException {

    public EmailNotFoundException(Long id) {
        super("Email for the person id '" + id + "' does not exist in our records");
    }
    
}