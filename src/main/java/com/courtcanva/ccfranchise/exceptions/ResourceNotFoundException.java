package com.courtcanva.ccfranchise.exceptions;

public class ResourceNotFoundException extends  RuntimeException{

    public ResourceNotFoundException(){
        super("Resource is not found");
    }

    public ResourceNotFoundException(String msg){
        super(msg);
    }
}
