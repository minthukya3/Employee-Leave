package com.example.employeeleave.service;

import com.example.employeeleave.entity.Employee;
import com.example.employeeleave.entity.EmployeeDetail;
import com.example.employeeleave.repo.EmployeeDetailRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeDetailService {
    private final EmployeeDetailRepo employeeDetailRepo;

    public EmployeeDetail saveEmployeeDetail(EmployeeDetail employeeDetail) {

        return employeeDetailRepo.save(employeeDetail);
    }
    public List<EmployeeDetail> getAllEmployeeDetailsById(int id){
        return employeeDetailRepo.findAllEmployeeDetailsByEmployeeId(id);
    }


    public void deleteEmployeeDetail(int id) {
        if(employeeDetailRepo.existsById(id)) {
            employeeDetailRepo.deleteById(id);
        }else {
            throw new EntityNotFoundException(id + "Not Found");
        }

    }
}
