package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.EmployeeDao;
import web.domain.Employee;

import java.util.List;

/**
 * Created by Andrew on 30.09.2015.
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao empDao;

    public EmployeeServiceImpl() {
    }

    @Override
    public Long create(Employee emp) {
        return empDao.create(emp);
    }

    @Override
    public Employee read(Long id) {
        return empDao.read(id);
    }

    @Override
    public boolean update(Employee emp) {
        return empDao.update(emp);
    }

    @Override
    public boolean delete(Employee emp) {
        return empDao.delete(emp);
    }

    @Override
    public String findEmployee(String email, String lastName) {
        return empDao.findEmployee(email,lastName);
    }
}
