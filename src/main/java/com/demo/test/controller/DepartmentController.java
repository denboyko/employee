package com.demo.test.controller;

import com.demo.test.dao.DepartmentDAO;
import com.demo.test.entity.Department;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/department")
@AllArgsConstructor
public class DepartmentController {
    private final DepartmentDAO departmentDAO;

    @GetMapping
    public List<Department> getAllDepartments(){
        return departmentDAO.findAll();
    }
}
