package org.curious.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.curious.code.controller.EmployeeController;
import org.curious.code.model.Employee;
import org.curious.code.service.EmployeeService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
//@RunWith(MockitoJUnitRunner.class)
//@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
public class EmployeeControllerApiTest {

    @Autowired
    private MockMvc mvc;
    @InjectMocks
    private EmployeeController controller;

    @MockBean
    private EmployeeService service;

    List<Employee> employeeList;
    Employee emp =null;
    private ObjectMapper mapper = new ObjectMapper();

    @BeforeAll
    public void init(){
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(controller,"serverPort","8080");
        employeeList = Arrays.asList(
                Employee.builder().id(1).name("Raj").email("raj@123").build(),
                Employee.builder().id(2).name("Kaj").email("kaj@123").build()
        );

        emp=Employee.builder().id(1).name("Raj").email("raj@123").build();
    }
    @AfterAll
    public void destroy(){
        log.info("[EmployeeControllerUnitTests][destroy]");
    }

    @Test
    public void getAllEmployees() throws Exception {
        when(service.getAllEmp()).thenReturn(employeeList);
        String response = mvc.perform(get("/employee/getAllEmp")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        List<Employee> empList = mapper.readValue(response, new TypeReference<List<Employee>>(){});
        assertEquals(2,empList.size());
    }

    @Test
    public void getEmployeeById() throws Exception {
        when(service.getEmployeeById(anyInt())).thenReturn(emp);
        String response = mvc.perform(get("/employee/getEmp/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        Employee emp = mapper.readValue(response, new TypeReference<Employee>(){});
        assertEquals("Raj",emp.getName());
    }

    @Test
    public void addEmployee() throws Exception {
        when(service.addEmployee(any(Employee.class))).thenReturn(emp);
        Employee employee = Employee.builder().id(1).name("Raj").email("Raj@123").build();
        String response = mvc.perform(post("/employee/addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(mapper.writeValueAsString(employee))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        Employee emp = mapper.readValue(response, new TypeReference<Employee>(){});
        assertEquals("Raj",emp.getName());
    }

}