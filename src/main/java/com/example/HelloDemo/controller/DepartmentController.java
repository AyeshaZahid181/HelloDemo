package com.example.HelloDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.HelloDemo.model.Department;
import com.example.HelloDemo.service.DepartmentService;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;
// get all departments
    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }
//Department by id
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Department department = departmentService.getDepartmentById(id).orElseThrow();
        return ResponseEntity.ok(department);
    }
//create department
    @PostMapping("/addDepartment")
    public Department createDepartment(@RequestBody Department department) {
        return departmentService.createDepartment(department);
    }
//updateDepartment
    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department departmentDetails) {
        Department updatedDepartment = departmentService.updateDepartment(id, departmentDetails);
        return ResponseEntity.ok(updatedDepartment);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        String message = getDeletionMessage(id);
        return ResponseEntity.ok(message);
    }

    private String getDeletionMessage(Long id) {
        return "Record with ID " + id + " deleted successfully.";
    }
}

