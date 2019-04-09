package com.demo.test.service.impl;

import com.demo.test.dto.EmployeeDTO;
import com.demo.test.entity.Employee;
import com.demo.test.model.EmployeeRequest;
import com.demo.test.model.Page;
import com.demo.test.service.EmployeeDTOService;
import com.demo.test.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeDTOServiceImpl implements EmployeeDTOService {

    private final EmployeeService employeeService;

    @Override
    public Page<EmployeeDTO> getAllEmployee(String search, Integer page, Integer perPage) {
        Page<Employee> employeePage = employeeService.getAllEmployee(search, page, perPage);

        Page<EmployeeDTO> employeeDTOPage = new Page<>();
        employeeDTOPage.setData(employeePage.getData().stream()
                .map(this::convertEmployeeToDTO)
                .collect(Collectors.toList()));

        employeeDTOPage.setTotal(employeePage.getTotal());
        employeeDTOPage.setPage(employeePage.getPage());
        employeeDTOPage.setPerPage(employeePage.getPerPage());

        return employeeDTOPage;
    }

    @Override
    public EmployeeDTO getEmployee(Integer id) {
        return convertEmployeeToDTO(employeeService.getEmployee(id));
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        return convertEmployeeToDTO(employeeService.createEmployee(convertDTOtoEmployee(employeeDTO)));
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
        return convertEmployeeToDTO(employeeService.updateEmployee(convertDTOtoEmployee(employeeDTO)));
    }

    @Override
    public Boolean deleteEmployee(Integer employeeId) {
        return employeeService.deleteEmployee(employeeId);
    }

    private EmployeeDTO convertEmployeeToDTO(Employee employee) {
        return EmployeeDTO
                .builder()
                .id(employee.getId())
                .name(employee.getName())
                .active(employee.getActive())
                .depId(employee.getDepId())
                .build();
    }

    private Employee convertDTOtoEmployee(EmployeeDTO employeeDTO){
        return Employee
                .builder()
                .id(employeeDTO.getId())
                .name(employeeDTO.getName())
                .active(employeeDTO.getActive())
                .depId(employeeDTO.getDepId())
                .build();
    }
}
