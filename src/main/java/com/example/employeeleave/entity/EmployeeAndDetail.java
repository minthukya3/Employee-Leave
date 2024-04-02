package com.example.employeeleave.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class EmployeeAndDetail {
    private Employee employee;
    private List<EmployeeDetail>employeeDetails;
}
