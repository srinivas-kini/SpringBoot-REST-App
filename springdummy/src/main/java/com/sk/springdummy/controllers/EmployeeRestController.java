package com.sk.springdummy.controllers;

import com.sk.springdummy.entities.Employee;
import com.sk.springdummy.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emp")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public List<Employee> getALlEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable int id) {
        Employee e = employeeService.findById(id);
        if (null == e) throw new RuntimeException("Employee with ID: " + id + " not found.");
        else return e;
    }

    @PostMapping("/add")
    public List<Employee> save(@RequestBody Employee newEmp) {
        newEmp.setId(0); //in case ID is passed, set it to 0 since it is auto incremented in the DB
        employeeService.save(newEmp);
        return employeeService.findAll();
    }
    
    @PutMapping("/update")
    public List<Employee> save(@RequestBody Employee newEmp) {
        employeeService.save(newEmp);
        return employeeService.findAll();
    }
    

    @DeleteMapping("/{id}")
    public List<Employee> deleteById(@PathVariable int id) {
        Employee e = employeeService.findById(id);
        if (e == null) throw new RuntimeException("Employee with ID: " + id + " does not exist.");
        else
            employeeService.deleteById(id);
        return employeeService.findAll();
    }


}
