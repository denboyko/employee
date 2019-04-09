package com.demo.test.service;

import com.demo.test.entity.Employee;
import com.demo.test.model.EmployeeRequest;
import com.demo.test.model.Page;

import java.util.List;

public interface EmployeeService {
    Page<Employee> getAllEmployee(String search, Integer page, Integer perPage);

    Employee getEmployee(Integer id);

    Employee createEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    Boolean deleteEmployee(Integer employeeId);
}
