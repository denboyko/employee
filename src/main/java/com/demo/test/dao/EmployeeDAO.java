package com.demo.test.dao;

import com.demo.test.entity.Employee;
import com.demo.test.model.Page;

import java.util.Optional;

public interface EmployeeDAO {
    Page<Employee> findAll(String startWithName, Integer page, Integer perPage);

    Optional<Employee> findById(Integer id);

    Employee getById(Integer id);

    int delete(Integer id);

    Employee save(Employee employee);
}
