package web.service;

import web.domain.Employee;

import java.util.List;

/**
 * Created by Andrew on 30.09.2015.
 */
public interface EmployeeService {
    Long create(Employee emp);
    Employee read(Long id);
    boolean update(Employee emp);
    boolean delete(Employee emp);
    String findEmployee(String email,String lastName);
}
