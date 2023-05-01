package com.self.sudip.departmentservice.controller;

import com.self.sudip.departmentservice.client.EmployeeClient;
import com.self.sudip.departmentservice.model.Department;
import com.self.sudip.departmentservice.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private static final Logger LOGGEER = LoggerFactory.getLogger(DepartmentController.class);
    private final DepartmentRepository departmentRepository;
    private final EmployeeClient employeeClient;

    @Autowired
    public DepartmentController(DepartmentRepository departmentRepository, EmployeeClient employeeClient) {
        this.departmentRepository = departmentRepository;
        this.employeeClient = employeeClient;
    }

    @PostMapping
    public Department addDepartment(@RequestBody Department department) {

        LOGGEER.info("Department added with id {} ", department.getId());
        return departmentRepository.addDepartment(department);

    }

    @GetMapping
    public List<Department> findAll() {

        LOGGEER.info("Department find all");
        return departmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Department findById(@PathVariable Long id) {
        LOGGEER.info("Department find by id {} ", id);
        return departmentRepository.findById(id);
    }

    @GetMapping("/with-employees")
    public List<Department> findAllWithEmployees() {

        LOGGEER.info("Department find all");
        List<Department> departments = departmentRepository.findAll();

        departments.forEach(department ->
                department.setEmployees(employeeClient.findEmployeesByDeptId(department.getId())
                )
        );
        return departments;
    }

}
