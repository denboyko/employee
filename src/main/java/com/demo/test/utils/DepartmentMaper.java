package com.demo.test.utils;

import com.demo.test.entity.Department;
import com.demo.test.entity.Employee;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DepartmentMaper implements RowMapper<Department> {

    @Override
    public Department mapRow(ResultSet resultSet, int i) throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getInt("depID"));
        department.setName(resultSet.getString("depName"));
        return department;
    }
}
