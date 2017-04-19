package com.example.Repository;


import com.example.Application;
import com.example.domain.*;
import com.example.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})
public class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeRepository2 employeeRepository2;

    @Autowired
    private EmployeeRepository3 employeeRepository3;

    @Autowired
    private EmployeeRepository4 employeeRepository4;

    @Autowired
    private EmployeeRepository5 employeeRepository5;

    @Autowired
    private EmployeeService employeeService;


    @Test
    public void test() throws Exception {
        Employee employee = employeeRepository.findByName("zhangsan");
        System.out.println(employee.toString());
    }

    @Test
    public void test1() throws Exception {
        List<Employee> employees = employeeRepository.findByNameStartingWithAndAgeLessThan("zhangsan", 30);
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }

    }

    @Test
    public void test2() throws Exception {
        List<Employee> employees = employeeRepository.findByNameEndingWithAndAgeLessThan("zhangsan", 30);
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }

    }

    @Test
    public void test3() throws Exception {
        List<String> names = new ArrayList<String>();
        names.add("zhangsan");
        names.add("zhangsan2");
        List<Employee> employees = employeeRepository.findByNameInOrAgeLessThan(names, 50);
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }
    }

    @Test
    public void test4() throws Exception {
        List<String> names = new ArrayList<String>();
        names.add("zhangsan");
        names.add("zhangsan2");
        List<Employee> employees = employeeRepository.findByNameInAndAgeLessThan(names, 50);
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }
    }

    @Test
    public void test5() throws Exception {
        Employee employee = employeeRepository.getEmployeeById();
        System.out.println(employee.toString());
    }

    @Test
    public void test6() throws Exception {
        List<Employee> employees = employeeRepository.queryParams("zhangsan", 20);
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }
    }

    @Test
    public void test7() throws Exception {
        List<Employee> employees = employeeRepository.queryParams1("zhangsan", 20);
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }
    }

    @Test
    public void test8() throws Exception {
        List<Employee> employees = employeeRepository.queryParams2("zhangsan");
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }
    }

    @Test
    public void test9() throws Exception {
        long number = employeeRepository.getCount();
        System.out.println(number);
    }

    @Test
    public void test10() throws Exception {
        long number = employeeRepository.getCount2();
        System.out.println(number);
    }

    @Test
    public void test11() throws Exception {
        employeeRepository.update(1, 55);
    }

    @Test
    public void test12() throws Exception {
        employeeService.update(1, 55);
    }

    @Test
    public void test13() throws Exception {
        List<Employee> employees = new ArrayList<Employee>();
        for (int i = 0; i < 100; i++) {
            Employee employee = new Employee();
            employee.setName("test" + i);
            employee.setAge(100 - i);
            employees.add(employee);
        }
        employeeService.save(employees);
    }

    @Test
    public void test14() throws Exception {
        Employee employee = employeeRepository2.findOne(1);
        System.out.println(employee);

    }

    @Test
    public void test15() throws Exception {
        Pageable pageable = new PageRequest(20, 10);
        Page<Employee> page = employeeRepository3.findAll(pageable);
        System.out.println("======");
        System.out.println(page.getContent());
        System.out.println("======");
    }

    @Test
    public void test16() throws Exception {
        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "id");
        Sort sort = new Sort(order);
        Pageable pageable = new PageRequest(0, 5, sort);


        Page<Employee> page = employeeRepository3.findAll(pageable);
        System.out.println("======");
        System.out.println(page.getContent());
        System.out.println("======");
    }

    @Test
    public void test17() throws Exception {
        Employee employee = employeeRepository4.findOne(99);
        System.out.println(employee);
    }

    /**
     * @throws Exception
     */

    @Test
    public void test18() throws Exception {
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "id");
        Sort sort = new Sort(order);
        Pageable pageable = new PageRequest(0, 5, sort);

        /**
         * root:查询的类型
         * query：添加查询的条件
         * cb:构建
         */

        Specification<Employee> specification = new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path path = root.get("age");
                return criteriaBuilder.gt(path, 50);
            }

        };
        Page<Employee> page = employeeRepository5.findAll(specification, pageable);
        System.out.println("========================");
        System.out.println(page.getContent());
        System.out.println("========================");


    }
}