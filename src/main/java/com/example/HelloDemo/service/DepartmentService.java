package com.example.HelloDemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HelloDemo.model.Department;
import com.example.HelloDemo.repositories.DepartmentRepository;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
// get all departments
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
//get department by id
    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }
//Create Department
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }
//Update Department
    public Department updateDepartment(Long id, Department departmentDetails) {
        Department department = departmentRepository.findById(id).orElseThrow();
        department.setDepName(departmentDetails.getDepName());
        department.setDepHead(departmentDetails.getDepHead());
        return departmentRepository.save(department);
    }
//delete Department
    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow();
        departmentRepository.delete(department);
    }
}
