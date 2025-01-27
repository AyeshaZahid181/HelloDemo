package com.example.HelloDemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.HelloDemo.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
