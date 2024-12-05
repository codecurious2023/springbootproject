package org.curious.code.service;

import lombok.extern.slf4j.Slf4j;
import org.curious.code.exceptions.EmployeeAlreadyExist;
import org.curious.code.exceptions.EmployeeIDNotFound;
import org.curious.code.exceptions.NoEmployeeAvailable;
import org.curious.code.model.Employee;
import org.curious.code.repo.EmployeeRepo;
import org.curious.code.repo.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    //Logger log = LoggerFactory.getLogger(EmployeeService.class);
    public List<Employee> getAllEmp() {
        log.info("[Start][EmployeeService][getAllEmp]");
        try {
            List<Employee> employees = new ArrayList<>();
            employeeRepository.findAll().forEach(emp->employees.add(emp));
            log.info("getAllEmp list size:-"+employees.size());
            return employees;
        }catch (Exception e){
            log.error("getAllEmp:-"+e);
            throw new NoEmployeeAvailable("There is no employee in DB");
        }
    }

    public Employee getEmployeeById(int id) {
       try {
           return employeeRepository.findById(id).get();

       }catch (Exception e){
           System.out.println("getEmployeeById:-"+e);
           throw new EmployeeIDNotFound("There is no employee with this id-"+id);
       }
    }

    public Employee addEmployee(Employee emp) {
        try {
            return employeeRepository.save(emp);
        }catch (Exception e){
            System.out.println("addEmployee:-"+e);
            throw new EmployeeAlreadyExist("Employee exist with given employee id in passed Employee objectID -"+emp.getId());
        }
    }

    public Employee updateEmployee(Employee emp) {
        try {
            Employee employee = employeeRepository.findById(emp.getId()).get();
            employee.setName(emp.getName());
            employee.setEmail(emp.getEmail());
            employeeRepository.save(employee);
            return emp;
        }catch (Exception e){
            System.out.println("updateEmployee-"+e);
            throw new EmployeeIDNotFound("There is no employee with this id-"+emp.getId());
        }
    }

    public Employee deleteEmployee(int id) {
        try {
            employeeRepository.deleteById(id);
        }catch (Exception e){
            System.out.println("deleteEmployee:-"+e);
            throw new EmployeeIDNotFound("There is no employee with this id-"+id);
        }
        return null;
    }
}


