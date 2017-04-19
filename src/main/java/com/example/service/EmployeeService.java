package com.example.service;

import com.example.domain.Employee;
import com.example.domain.EmployeeRepository;
import com.example.domain.EmployeeRepository2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author swj
 * @Date 2017/04/19 14:30
 */
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeRepository2 employeeRepository2;
    @Transactional
    public void update(Integer id,Integer age){
        employeeRepository.update(id,age);
    }

    @Transactional
    public void save(List<Employee> employees){
        employeeRepository2.save(employees);
    }

}
