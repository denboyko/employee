package com.demo.test.utils;

import com.demo.test.entity.Employee;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class EmployeeMaper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("empID"));
        employee.setName(resultSet.getString("empName"));
        employee.setActive(resultSet.getBoolean("active"));
        employee.setDepId(resultSet.getInt("depID"));
        return employee;
    }
}
