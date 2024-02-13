package com.ltp.contactbook.exception;

public class AddressNotFoundException extends RuntimeException {

    public AddressNotFoundException(Long id) {
        super("Address for the person id '" + id + "' does not exist in our records");
    }
    
}