package org.curious.code.exceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import org.curious.code.exceptions.DefaultResponse;
import org.curious.code.exceptions.EmployeeAlreadyExist;
import org.curious.code.exceptions.EmployeeIDNotFound;
import org.curious.code.exceptions.NoEmployeeAvailable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    //@ResponseStatus
    @ExceptionHandler(value = EmployeeAlreadyExist.class)
    public ResponseEntity<DefaultResponse> employeeAlreadyExist(EmployeeAlreadyExist ex, WebRequest request, HttpServletRequest req){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(DefaultResponse.builder().statusCode(HttpStatus.CONFLICT.value()).msg(ex.getMessage()).build());
    }

    @ExceptionHandler(value = EmployeeIDNotFound.class)
    public ResponseEntity<DefaultResponse> employeeIdNotFound(EmployeeAlreadyExist ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(DefaultResponse.builder().statusCode(HttpStatus.NOT_FOUND.value()).msg(ex.getMessage()).build());
    }

    @ExceptionHandler(value = NoEmployeeAvailable.class)
    public ResponseEntity<DefaultResponse> noEmployeeAvailable(EmployeeAlreadyExist ex){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(DefaultResponse.builder().statusCode(HttpStatus.NO_CONTENT.value()).msg(ex.getMessage()).build());
    }

}
