package com.example.domain;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Author swj
 * @Date 2017/04/18 14:47
 */
//@RepositoryDefinition(domainClass = Employee.class,idClass = Integer.class)

public interface EmployeeRepository extends Repository<Employee, Integer> {

    Employee findByName(String name);

    List<Employee> findByNameStartingWithAndAgeLessThan(String name, Integer age);

    List<Employee> findByNameEndingWithAndAgeLessThan(String name, Integer age);

    List<Employee> findByNameInOrAgeLessThan(List<String> name, Integer age);

    List<Employee> findByNameInAndAgeLessThan(List<String> name, Integer age);

    @Query("from Employee where id=(select max(id) from Employee)")
    Employee getEmployeeById();

    @Query("from Employee where name=?1 and age=?2")
    List<Employee> queryParams(String name, Integer age);

    @Query("from Employee where name=:name and age=:age")
    List<Employee> queryParams1(@Param("name") String name, @Param("age") Integer age);

    @Query("from Employee where name like %?1%")
    List<Employee> queryParams3(String name);

    @Query("from Employee where name like %:name%")
    List<Employee> queryParams2(@Param("name") String name);

    @Query(nativeQuery = true, value = "select count(1) from employee")
    long getCount();

    @Query("select count(1) from Employee")
    long getCount2();

    @Modifying
    @Query("update Employee set age=:age where id=:id")
    void update(@Param("id") Integer id, @Param("age") Integer age);


}
