package com.demo.test.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequest {
    private String search;
    private Integer page;
    private Integer perPage;
}
