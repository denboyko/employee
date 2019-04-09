package com.demo.test.service.impl;

import com.demo.test.dao.EmployeeDAO;
import com.demo.test.entity.Employee;
import com.demo.test.exception.PlatformException;
import com.demo.test.model.EmployeeRequest;
import com.demo.test.model.Page;
import com.demo.test.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;

    @Override
    public Page<Employee> getAllEmployee(String search, Integer page, Integer perPage) {
        return employeeDAO.findAll(search, page, perPage);
    }

    @Override
    public Employee getEmployee(Integer id) {
        return employeeDAO.findById(id).orElseThrow(()-> new PlatformException("Cannot find employee with id: " + id));
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeDAO.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeDAO.save(employee);
    }

    @Override
    public Boolean deleteEmployee(Integer employeeId) {
        return employeeDAO.delete(employeeId) > 0;
    }
}
