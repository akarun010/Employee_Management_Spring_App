package com.arun.Employee.Management.Service;

import com.arun.Employee.Management.DAO.EmployeeDAO;
import com.arun.Employee.Management.Model.Employee;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    EmployeeDAO dao;
    @Mock
    BCryptPasswordEncoder encoder;

    @InjectMocks
    EmployeeService employeeService;

    Employee employee;

    @BeforeEach
    void employeeCreation(){
        employee = new Employee();
        employee.setId(5);
        employee.setName("Akshay");
        employee.setEmail("akshay@gmail.com");
        employee.setSalary(59000);
        employee.setDepartment("Full Stack");
    }

    @BeforeAll
    static void testStarted(){
        System.out.println("Testing Started...");
    }

    @AfterAll
    static void testEnded(){
        System.out.println("Testing Ended...");
    }

    @AfterEach
    void cleanUp(){
        employee = null;
        System.out.println("Code Cleanup");
    }

    @Nested
    @DisplayName("Add Employee Test Case")
    class AddEmployeeTest{
        @Test
        void addEmployee() {
            //Given
            when(encoder.encode(anyString())).thenReturn("encoded-email");

            //When
            employeeService.addEmployee(employee);

            //Then
            verify(encoder, times(1)).encode("akshay@gmail.com");
            ArgumentCaptor<Employee> captor = ArgumentCaptor.forClass(Employee.class);
            verify(dao).save(captor.capture());
            Employee capturedEmployee = captor.getValue();
            assertEquals(5, capturedEmployee.getId());
            assertEquals("Akshay", capturedEmployee.getName());
            assertEquals("Full Stack", capturedEmployee.getDepartment());
        }
    }

    @Nested
    @DisplayName("Update Employee Test Case")
    class UpdateEmployeeTest {
        @Test
        void updateEmployee(){
            Employee existingEmployee = employee;
            Optional<Employee> e = Optional.of(existingEmployee);
            when(dao.findById(existingEmployee.getId())).thenReturn(e);
            when(encoder.encode("rahman@gmail.com")).thenReturn("encoded-email");

            //Given
            Employee employee = new Employee();
            employee.setId(5);
            employee.setName("Rahman");
            employee.setEmail("rahman@gmail.com");
            employee.setSalary(76000);
            employee.setDepartment("IT");

            //When
            employeeService.updateEmployee(employee, 5);

            //Then
            verify(encoder).encode("rahman@gmail.com");
            verify(dao).save(existingEmployee);
        }

        @Test
        @DisplayName("User Not Found Test Case")
        void userNotFound() {

            //Given
            Employee e = employee;

            when(dao.findById(anyInt())).thenReturn(Optional.empty());

            //When
            assertThrows(RuntimeException.class,  () -> {
                employeeService.updateEmployee(e, 5);
            });

            //Then
            verify(dao).findById(5);
            verify(dao, never()).save(any(Employee.class));
        }
    }

    @Nested
    @DisplayName("Get Employee Tests")
    class GetEmployeeTest {
        @Test
        @DisplayName("Get Employee By Id Test Case")
        void getEmployeeById() {

            //Given
            Employee e = employee;

            doReturn(Optional.of(e)).when(dao).findById(5);

            Employee result = employeeService.getEmployeeById(5);

            assertAll(
                    () -> assertNotNull(result),
                    () -> assertEquals(5, result.getId()),
                    () -> assertEquals("Akshay", result.getName()),
                    () -> assertEquals("akshay@gmail.com", result.getEmail()),
                    () -> assertEquals(59000, result.getSalary()),
                    () -> assertEquals("Full Stack", result.getDepartment())
            );
        }
    }

    @Test
    @DisplayName("Delete Employee Exception Test")
    void deleteEmployeeException() {

        doThrow(new RuntimeException()).when(dao).deleteById(5);

        assertThrows(RuntimeException.class, () -> {
            employeeService.deleteEmployee(5);
        });
        verify(dao).deleteById(5);
    }

    @Test
    @DisplayName("Delete Employee Success Test")
    void deleteEmployeeSuccess() {
        doNothing().when(dao).deleteById(5);

        employeeService.deleteEmployee(5);

        verify(dao).deleteById(5);
    }

    @ParameterizedTest
    @CsvSource({
            "2, Arun",
            "5, Akash"
    })
    void testEmployeeId(int id, String name){
        assertTrue(id > 0);
        assertNotNull(name);
    }
}