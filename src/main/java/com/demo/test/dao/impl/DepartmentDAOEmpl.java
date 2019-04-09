package com.demo.test.dao.impl;

import com.demo.test.dao.DepartmentDAO;
import com.demo.test.entity.Department;
import com.demo.test.utils.DepartmentMaper;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class DepartmentDAOEmpl implements DepartmentDAO {
    private final JdbcTemplate jdbcTemplate;
    private final DepartmentMaper departmentMaper;

    @Override
    public List<Department> findAll() {
        String query = "select * from Department";
        return jdbcTemplate.query(query, departmentMaper);
    }

    @PostConstruct
    public void fillDefaultData(){
        if(findAll().size()==0){
            Resource resource = new ClassPathResource("/db_scripts/departments.sql");
            ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(resource);
            databasePopulator.execute(Objects.requireNonNull(jdbcTemplate.getDataSource()));
        }
    }
}
