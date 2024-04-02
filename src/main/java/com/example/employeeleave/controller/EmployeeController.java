package com.example.employeeleave.controller;

import com.example.employeeleave.entity.*;
import com.example.employeeleave.repo.EmployeeDetailRepo;
import com.example.employeeleave.repo.EmployeeRepo;
import com.example.employeeleave.service.EmployeeDetailService;
import com.example.employeeleave.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeDetailService employeeDetailService;
    private final EmployeeRepo employeeRepo;
    private final EmployeeDetailRepo employeeDetailRepo;


    @GetMapping("/")
    public String home(Model model) {
        EmployeeAndDetail employeeAndDetail = new EmployeeAndDetail();
        employeeAndDetail.setEmployee(new Employee());
        employeeAndDetail.setEmployeeDetails(new ArrayList<>());
        model.addAttribute("empAndDetail", employeeAndDetail);
        model.addAttribute("employee", new Employee());
        model.addAttribute("employeeDetail", new EmployeeDetail());
        model.addAttribute("employeeDetailList", employeeDetailRepo.findAll());
        return "employeeForm";
    }

    @PostMapping("/save-employee")
    public String saveEmployee(@ModelAttribute("employeeAndDetail") EmployeeAndDetail employeeAndDetail, Employee employee, EmployeeDetail employeeDetail) {
        // Save EntityOne
        Employee savedEmployee = employeeService.saveEmployee(employeeAndDetail.getEmployee());
        employeeDetail.setEmployee(savedEmployee);
        employeeDetailService.saveEmployeeDetail(employeeDetail);


        return "redirect:/";
    }


    @GetMapping("/employee-list")
    public String employeeList(Model model) {
        model.addAttribute("employees", employeeService.findAllEmployees());
        return "employeeList";
    }

    @GetMapping("/employee-show")
    public String employeeShow(int id, Model model) {
        model.addAttribute("employees", employeeService.findEmployeeById(id));
        model.addAttribute("employeeLeaves", employeeDetailService.getAllEmployeeDetailsById(id));
        this.empId = id;
        return "employeeDetail";
    }

    private int empId;

    @GetMapping("/employee-update")
    public String employeeUpdate(int id, Model model) {
        model.addAttribute("employee", employeeService.findEmployeeById(id));
        model.addAttribute("employeeLeaves", employeeDetailService.getAllEmployeeDetailsById(id));
        model.addAttribute("addEmployeeDetail", new EmployeeDetail());
        this.empId = id;
        return "employeeUpdate";
    }

    @PostMapping("/update-process")
    public String updateProcess(Employee employee, EmployeeDetail employeeDetail) {
        employee.setId(empId);
        Employee savedEmployee = employeeService.saveEmployee(employee);
        employeeDetail.setEmployee(savedEmployee);
        employeeDetailService.saveEmployeeDetail(employeeDetail);

        return "redirect:/employee-list";
    }


    @GetMapping("/employee-delete")
    public String deleteEmployee(@RequestParam("id") int id) {
        employeeService.deleteEmployeeById(id);

        return "redirect:/employee-list";
    }

    @PostMapping("/save-employeeDetail")
    public String saveEmployeeDetail(EmployeeDetail employeeDetail, BindingResult result) {
        if (result.hasErrors()) {
            return "employeeForm";
        }
        employeeDetailService.saveEmployeeDetail(employeeDetail);
        return "redirect:/";
    }

    @GetMapping("/employeeDetail-delete")
    public String deleteEmployeeDetail(@RequestParam("id") int id) {
        employeeDetailService.deleteEmployeeDetail(id);

        return "redirect:/employee-list";
    }

    @GetMapping("/search")
    public String getEmployees(Model model, @Param("keyword") String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            try {

                Integer id = Integer.parseInt(keyword);
                Optional<Employee> employee = employeeRepo.findById(id);

                if (employee.isPresent()) {
                    model.addAttribute("employee", employee.get());
                    return "searchEmployee";
                }
            } catch (NumberFormatException e) {
                // Ignore the exception if parsing as integer fails
            }
        }
        List<Employee> employees = employeeService.findAllEmployees(keyword);
        model.addAttribute("employee",employees);
        model.addAttribute("keyword", keyword);
        return "searchEmployee";
    }
}





