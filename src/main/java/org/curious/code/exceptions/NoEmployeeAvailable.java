package org.curious.code.exceptions;

public class NoEmployeeAvailable extends RuntimeException{
    public NoEmployeeAvailable(){}

    public NoEmployeeAvailable(String msg){
        super(msg);
    }
}
