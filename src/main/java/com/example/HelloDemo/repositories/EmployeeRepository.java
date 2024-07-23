package com.example.HelloDemo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.HelloDemo.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

