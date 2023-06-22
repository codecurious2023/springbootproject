package org.curious.code.repo;

import org.curious.code.data.EmployeeDataCache;
import org.curious.code.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepo {

    public List<Employee> getAllEmp() {
        return EmployeeDataCache.getCache().getEmployeeList();
    }

    public Employee getEmployeeById(int id) {
        return EmployeeDataCache.getCache()
                .getEmployeeList()
                .stream().filter(emp->emp.getId()==id).toList().get(0);
    }

    public Employee addEmployee(Employee emp) {
        boolean flag =EmployeeDataCache.getCache()
                .getEmployeeList().stream().anyMatch(emp1->emp1.getId()==emp.getId());
        if(flag) throw new RuntimeException();
        EmployeeDataCache.getCache().getEmployeeList().add(emp);
        return emp;
    }

    public Employee updateEmployee(Employee emp) {
        boolean flag =EmployeeDataCache.getCache()
                .getEmployeeList().stream().anyMatch(emp1->emp1.getId()==emp.getId());
        if(!flag) throw new RuntimeException();
        EmployeeDataCache.getCache()
                .getEmployeeList().stream().map(emp1-> {
                    if(emp1.getId()==emp.getId()){
                        emp1.setEmail(emp.getEmail());
                        emp1.setEmail(emp.getEmail());
                    }
                    return emp1;
                }).toList();

        return emp;
    }

    public Employee deleteEmployee(int id) {
       Employee employee= EmployeeDataCache.getCache()
                .getEmployeeList().stream().filter(emp->emp.getId()==id).toList().get(0);
        EmployeeDataCache.getCache()
                .getEmployeeList().remove(employee);
        return employee;
    }
}
