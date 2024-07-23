package com.example.HelloDemo.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.example.HelloDemo.model.Employee;
import com.example.HelloDemo.repositories.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
 
    //Get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
//getEmployee by id
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }
    //create Employee
    public EmployeeResponse createEmployee(Employee employee) {
        try {
            Employee savedEmployee = employeeRepository.save(employee);
            return new EmployeeResponse("Employee created successfully", savedEmployee);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create employee: " + e.getMessage(), e);
        }
    }
//Update Employee
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee existingEmployee = employeeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found"));

        existingEmployee.setEmpName(employeeDetails.getEmpName());
        existingEmployee.setEmpEmail(employeeDetails.getEmpEmail());
        existingEmployee.setEmpContact(employeeDetails.getEmpContact());
        existingEmployee.setDep(employeeDetails.getDep());

        return employeeRepository.save(existingEmployee);
    }

    //Delete Employee
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employeeRepository.delete(employee);
    }
}

