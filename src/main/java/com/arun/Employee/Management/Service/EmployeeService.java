package com.arun.Employee.Management.Service;

import com.arun.Employee.Management.DAO.EmployeeDAO;
import com.arun.Employee.Management.Model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeDAO dao;
    public void addEmployee(Employee employee){
        dao.save(employee);
    }
    public void updateEmployee(Employee employee, int id){
        Employee e = dao.findById(id).orElseThrow(RuntimeException::new);
        e.setName(employee.getName());
        e.setEmail(employee.getEmail());
        e.setDepartment(employee.getDepartment());
        e.setSalary(employee.getSalary());
        dao.save(e);
    }
    public void deleteEmployee(int id){
        dao.deleteById(id);
    }
    public Employee getEmployeeById(int id){
        return dao.findById(id).orElse(null);
    }
    public List<Employee> getAllEmployee(){
        return dao.findAll();
    }
}
