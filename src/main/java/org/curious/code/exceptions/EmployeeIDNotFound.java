package org.curious.code.exceptions;

public class EmployeeIDNotFound extends RuntimeException{

    public EmployeeIDNotFound(){}

    public EmployeeIDNotFound(String msg){
        super(msg);
    }
}
