package com.arun.Employee.Management.DAO;

import com.arun.Employee.Management.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee, Integer> {
    Employee  findByName(String name);
}
