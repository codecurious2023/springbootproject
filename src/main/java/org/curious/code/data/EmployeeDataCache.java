package org.curious.code.data;

import org.curious.code.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDataCache {
    private static EmployeeDataCache cache;
    volatile List<Employee> empList = new ArrayList<>();
    private EmployeeDataCache(){
//        Employee emp = new Employee("Paji",122,"paji@122");
//        empList.add(emp);
//        emp = new Employee("Raj",123,"raj@123");
//        empList.add(emp);
//        emp = new Employee("Kaj",124,"kaj@124");
//        empList.add(emp);
//        emp = new Employee("Taj",125,"taj@125");
//        empList.add(emp);
//        emp = new Employee("Saj",126,"saj@126");
//        empList.add(emp);
//        emp = new Employee("Maj",127,"Maj@127");
//        empList.add(emp);
    }

    public static EmployeeDataCache getCache(){
        if(cache==null){
            cache=new EmployeeDataCache();
        }
        return  cache;
    }

    public List<Employee> getEmployeeList(){
        return empList;
    }
}
