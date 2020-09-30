package com.sk.springdummy.dao;

import com.sk.springdummy.entities.Employee;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface EmployeeDao {

    public List<Employee> findAll();

    public Employee findById(int id);

    public void deleteById(int id);

    public void save(Employee e);

}
