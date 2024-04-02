package com.example.employeeleave.repo;

import com.example.employeeleave.entity.Department;
import com.example.employeeleave.entity.Employee;
import com.example.employeeleave.entity.EmployeeDetail;
import com.example.employeeleave.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee,Integer> {
    Optional<Employee> findById(Integer id);

    @Query("SELECT emp FROM Employee emp WHERE CONCAT(emp.name,' ',emp.role,' ',emp.department,' ',emp.phone,' ',emp.email)LIKE  %?1%")
        public List<Employee>search(String keyword);



}
