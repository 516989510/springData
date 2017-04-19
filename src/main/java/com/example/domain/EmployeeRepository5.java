package com.example.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author swj
 * @Date 2017/04/19 14:38
 */
public interface EmployeeRepository5 extends JpaRepository<Employee,Integer> ,JpaSpecificationExecutor<Employee> {




}
