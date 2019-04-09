package com.demo.test.controller;

import com.demo.test.dto.EmployeeDTO;
import com.demo.test.model.EmployeeRequest;
import com.demo.test.model.Page;
import com.demo.test.service.EmployeeDTOService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@AllArgsConstructor
public class EmployController {

    private final EmployeeDTOService employeeDTOService;

    @GetMapping
    public Page<EmployeeDTO> getAllEmployees(@RequestParam String search, @RequestParam Integer page, @RequestParam Integer perPage) {
        return employeeDTOService.getAllEmployee(search, page, perPage);
    }

    @GetMapping("/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable("employeeId") Integer employeeId) {
        return employeeDTOService.getEmployee(employeeId);
    }

    @PostMapping
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeDTOService.createEmployee(employeeDTO);
    }

    @PutMapping
    public EmployeeDTO updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeDTOService.updateEmployee(employeeDTO);
    }

    @DeleteMapping("/{employeeId}")
    public Boolean deleteEmployee(@PathVariable("employeeId") Integer employeeId) {
        return employeeDTOService.deleteEmployee(employeeId);
    }
}
