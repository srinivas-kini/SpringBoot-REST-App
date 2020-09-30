package com.sk.springdummy.services;

import com.sk.springdummy.entities.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> findAll();

    public Employee findById(int id);

    public void deleteById(int id);

    public void save(Employee e);
}
