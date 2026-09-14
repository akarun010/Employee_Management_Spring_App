package com.arun.Employee.Management.Controller;

import com.arun.Employee.Management.Model.Employee;
import com.arun.Employee.Management.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService service;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return service.getAllEmployee();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable int id){
        return service.getEmployeeById(id);
    }

    @GetMapping("/employees/department/{department}")
    public List<Employee> getEmployeeByDept(@PathVariable String department){
        return service.getEmployeesByDept(department);
    }

    @GetMapping("/employees/salary")
    public List<Employee> getEmployeesByMinSalary(@RequestParam int min){
        return service.getEmployeesByMinSalary(min);
    }

    @PostMapping("/employees")
    public void addEmployee(@RequestBody Employee employee){
        service.addEmployee(employee);
    }

    @PutMapping("/employees/{id}")
    public void updateEmployee(@RequestBody Employee employee,@PathVariable int id){
        service.updateEmployee(employee,id);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable int id){
        service.deleteEmployee(id);
    }
}
