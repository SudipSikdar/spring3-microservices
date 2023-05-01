package com.self.sudip.employeeservice.controller;

import com.self.sudip.employeeservice.model.Employee;
import com.self.sudip.employeeservice.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        LOGGER.info("Add Employee in Employee controller {} ", employee);
        return employeeRepository.addEmployee(employee);

    }

    @GetMapping
    public List<Employee> findAll() {

        LOGGER.info("find all employee");
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable Long id) {

        LOGGER.info("Employee find by id {}", id);
        return employeeRepository.findById(id);
    }

    @GetMapping("/department/{departmentId}")
    public List<Employee> findEmployeesByDeptId(@PathVariable Long departmentId) {

        LOGGER.info("Find Employee by Department {} ", departmentId);
        return employeeRepository.findByDepartment(departmentId);
    }

}
