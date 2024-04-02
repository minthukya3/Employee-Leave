package com.example.employeeleave.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    @Enumerated(EnumType.STRING)
    private Role role;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    private String nrc;
    @Enumerated(EnumType.STRING)
    private Department department;
    private String phone;

    private String email;

    private String township;

    private String address;

    @OneToMany(mappedBy = "employee",orphanRemoval = true)
    private List<EmployeeDetail> employeeDetails=new ArrayList<>();

  public void addEmployeeDetail(EmployeeDetail employeeDetail){
    employeeDetail.setEmployee(this);
    employeeDetails.add(employeeDetail);
  }


}
