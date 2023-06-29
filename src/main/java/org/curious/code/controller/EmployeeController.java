package org.curious.code.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.curious.code.service.EmployeeService;
import org.curious.code.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {

    @Value("${server.port}")
    private String serverPort;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/getAllEmp")
    public ResponseEntity<List<Employee>> getAllEmp(){
        log.info("--getAllEmp()--"+serverPort);
        List<Employee> emplist = employeeService.getAllEmp();
        return ResponseEntity.ok(emplist);
    }
    @Operation(summary = "Get a Employee by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Employee",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Employee.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid EmployeeId",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Employee not found",
                    content = @Content) })
    @GetMapping("/getEmp/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id){
        log.info("--getEmployeeById()--"+serverPort);
        Employee emplist = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(emplist);
    }

    @PostMapping("/addEmployee")
    public @ResponseBody ResponseEntity<Employee> addEmployee(@RequestBody Employee emp){
        log.info("--addEmployee()--"+serverPort);
        Employee employee = employeeService.addEmployee(emp);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    @PutMapping("/updateEmployee")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee emp){
        Employee employee = employeeService.updateEmployee(emp);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") int id){
        Employee employee = employeeService.deleteEmployee(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }
}
