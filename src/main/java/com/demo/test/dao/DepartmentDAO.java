package com.demo.test.dao;

import com.demo.test.entity.Department;

import java.util.List;

public interface DepartmentDAO {
    List<Department> findAll();
}
