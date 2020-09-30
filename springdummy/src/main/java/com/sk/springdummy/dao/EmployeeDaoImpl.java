package com.sk.springdummy.dao;


import com.sk.springdummy.entities.Employee;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class EmployeeDaoImpl implements EmployeeDao {

    //Inject the entityManager
    private EntityManager em;

    @Autowired
    public EmployeeDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Employee> findAll() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
    }

    @Override
    public Employee findById(int id) {
        return em.unwrap(Session.class).get(Employee.class, id);
        // return em.find(Employee.class,id);
    }

    @Override
    public void deleteById(int id) {
        em.unwrap(Session.class)
                .createQuery("DELETE FROM Employee e WHERE e.id = :id")
                .setParameter("id", id).executeUpdate();
    }

    @Override
    public void save(Employee e) {
        em.unwrap(Session.class).saveOrUpdate(e);
    }

}
