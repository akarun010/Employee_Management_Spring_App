package com.arun.Employee.Management.Service;

import com.arun.Employee.Management.DAO.EmployeeDAO;
import com.arun.Employee.Management.Model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements UserDetailsService {
    @Autowired
    EmployeeDAO dao;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public void addEmployee(Employee employee){
        employee.setEmail(encoder.encode(employee.getEmail()));
        dao.save(employee);
    }
    public void updateEmployee(Employee employee, int id){
        Employee e = dao.findById(id).orElseThrow(RuntimeException::new);
        e.setName(employee.getName());
        e.setEmail(encoder.encode(employee.getEmail()));
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

    public List<Employee> getEmployeesByDept(String department) {
        return dao.findAll().stream().filter(e -> e.getDepartment().equals(department)).collect(Collectors.toList());
    }

    public List<Employee> getEmployeesByMinSalary(int min) {
        return dao.findAll().stream().filter(e -> e.getSalary() > min).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return dao.findByName(username);
    }
}
