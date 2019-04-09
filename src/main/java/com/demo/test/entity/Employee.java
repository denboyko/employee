package com.demo.test.entity;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {
    private Integer id;
    private String name;
    private Boolean active;
    private Integer depId;
}
