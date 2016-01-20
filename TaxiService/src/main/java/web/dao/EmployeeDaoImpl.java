package web.dao;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.domain.Employee;

import java.util.List;

/**
 * Created by Andrew on 30.09.2015.
 */
@Repository
public class EmployeeDaoImpl implements EmployeeDao {
    private static Logger log = Logger.getLogger(EmployeeDaoImpl.class);
    @Autowired
    private SessionFactory factory;

    public EmployeeDaoImpl() {
    }

    public EmployeeDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Long create(Employee emp) {
        Session session = null;
        return (Long)factory.getCurrentSession().save(emp);
    }

    @Override
    public Employee read(Long id) {
        return (Employee)factory.getCurrentSession().get(Employee.class,id);

    }

    @Override
    public boolean update(Employee emp) {
        return (boolean)factory.getCurrentSession().save(emp);
    }

    @Override
    public boolean delete(Employee emp) {
        factory.getCurrentSession().delete(emp);
        return true;
    }


    @Override
    public String findEmployee(String email, String lastName) {
        Session session = factory.getCurrentSession();
        String strQuery = "Select e.phoneNumber from Employee e  where e.email=:email and e.lastName=:lastName ";
        session.beginTransaction();
        Query query = session.createQuery(strQuery);
        query.setParameter("email", email);
        query.setParameter("lastName", lastName);
        return (String)query.uniqueResult();
    }
}
