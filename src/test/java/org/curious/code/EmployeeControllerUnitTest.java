package org.curious.code;

import lombok.extern.slf4j.Slf4j;
import org.curious.code.controller.EmployeeController;
import org.curious.code.model.Employee;
import org.curious.code.service.EmployeeService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
//@RunWith(MockitoJUnitRunner.class)
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
public class EmployeeControllerUnitTest {

    @Value("${code.curious.ownerName}")
    private String ownerName;
    @InjectMocks
    private EmployeeController controller;

    @Mock
    private EmployeeService service;

    List<Employee> employeeList;

    Employee emp =null;

    @BeforeAll
    public void init(){
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(controller,"serverPort",ownerName);
        employeeList = Arrays.asList(
                Employee.builder().id(1).name("Raj").email("raj@123").build(),
                Employee.builder().id(2).name("Kaj").email("kaj@123").build()
        );
        emp = Employee.builder().id(1).name("Raj").email("raj@123").build();
    }
    @AfterAll
    public void destroy(){
        log.info("[EmployeeControllerUnitTests][destroy]");
    }

    @Test
    public void getAllEmployees(){
        when(service.getAllEmp()).thenReturn(employeeList);
        ResponseEntity<List<Employee>> empList = controller.getAllEmp();
        assertEquals(2,empList.getBody().size());
    }

    @Test
    public void getEmployeeById(){
        when(service.getEmployeeById(anyInt())).thenReturn(emp);
        ResponseEntity<Employee> emp = controller.getEmployeeById(1);
        assertEquals("Raj",emp.getBody().getName());
    }

    @Test
    public void addEmployee(){
        when(service.addEmployee(any(Employee.class))).thenReturn(emp);
        ResponseEntity<Employee> emp = controller.addEmployee(new Employee());
        assertEquals("Raj",emp.getBody().getName());
    }

}
