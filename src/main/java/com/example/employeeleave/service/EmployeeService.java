package com.example.employeeleave.service;

import com.example.employeeleave.entity.Employee;
import com.example.employeeleave.entity.EmployeeAndDetail;
import com.example.employeeleave.entity.EmployeeDetail;
import com.example.employeeleave.entity.Role;
import com.example.employeeleave.repo.EmployeeDetailRepo;
import com.example.employeeleave.repo.EmployeeRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final EmployeeDetailRepo employeeDetailRepo;

    public Employee findEmployeeById(int id){
        return employeeRepo.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    public List<Employee> findAllEmployees(){

        return  employeeRepo.findAll();
    }


    public List<Employee> findAllEmployees(String keyword) {
        if (keyword != null){
            return employeeRepo.search(keyword);
        }
        return (List<Employee>)employeeRepo.findAll();
    }


    public Employee saveEmployee(Employee employee){

     return    employeeRepo.save(employee);
    }

    public void deleteEmployeeById(int id) {
        if (employeeRepo.existsById(id)){
            employeeRepo.deleteById(id);
        }else{
            throw new EntityNotFoundException(id + "Not Found!");
        }
    }


}
