package com.demo.test.dao.impl;

import com.demo.test.dao.EmployeeDAO;
import com.demo.test.entity.Employee;
import com.demo.test.model.Page;
import com.demo.test.utils.EmployeeMaper;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeDAOImpl implements EmployeeDAO {
    private final JdbcTemplate jdbcTemplate;
    private final EmployeeMaper employeeMaper;
    private final SimpleJdbcInsert simpleJdbcInsert;

    @Override
    public Page<Employee> findAll(String startWithName, Integer page, Integer perPage) {
        Page<Employee> employeePage = new Page<>();
        employeePage.setPage(page);
        employeePage.setPerPage(perPage);

        String searchCriteria = " where empName like '" + startWithName + "%'";

        String countQuery = "select count(1) as row_count from Employee" + searchCriteria;
        employeePage.setTotal(jdbcTemplate.queryForObject(countQuery, new Object[]{}, (rs, rowNum) -> rs.getInt(1)));


        String searchQuery = "select * from Employee" + searchCriteria + " limit " + perPage + " offset " + employeePage.getOffset();
        employeePage.setData(jdbcTemplate.query(searchQuery, employeeMaper));

        return employeePage;
    }

    @Override
    public Optional<Employee> findById(Integer id) {
        return Optional.ofNullable(getById(id));
    }

    @Override
    public Employee getById(Integer id) {
        Assert.notNull(id, "The given employee id must not be null!");
        String query = "select * from Employee where empID = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, employeeMaper);
    }

    @Override
    public int delete(Integer id) {
        Assert.notNull(id, "The given id must not be null!");
        String query = "delete from Employee where empID = ?";
        return jdbcTemplate.update(query, id);
    }

    @Override
    public Employee save(Employee employee) {
        Assert.notNull(employee, "The entity must not be null!");
        if (employee.getId() == null) {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("empName", employee.getName());
            parameters.put("active", employee.getActive());
            parameters.put("depId", employee.getDepId());
            employee.setId(simpleJdbcInsert.executeAndReturnKey(parameters).intValue());
        } else {
            jdbcTemplate.update("update Employee set empName = ?, active = ?, depID =? where empID = ?", employee.getName(), employee.getActive(), employee.getDepId(), employee.getId());
        }
        return getById(employee.getId());
    }
}
