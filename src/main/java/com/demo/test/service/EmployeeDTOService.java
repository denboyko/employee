package com.demo.test.service;

import com.demo.test.dto.EmployeeDTO;
import com.demo.test.model.Page;

public interface EmployeeDTOService {
    Page<EmployeeDTO> getAllEmployee(String search, Integer page, Integer perPage);

    EmployeeDTO getEmployee(Integer id);

    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO);

    Boolean deleteEmployee(Integer employeeId);
}
