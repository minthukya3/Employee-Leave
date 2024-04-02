package com.example.employeeleave.repo;

import com.example.employeeleave.entity.EmployeeDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeDetailRepo extends JpaRepository<EmployeeDetail,Integer> {
    List<EmployeeDetail> findAllEmployeeDetailsByEmployeeId(int id);


}
