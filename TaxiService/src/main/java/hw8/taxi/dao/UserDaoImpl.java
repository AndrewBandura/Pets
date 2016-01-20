package hw8.taxi.dao;

import hw8.taxi.domain.User;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Andrew on 30.09.2015.
 */
@Repository
public class UserDaoImpl implements UserDao {
    private static Logger log = Logger.getLogger(UserDaoImpl.class);
    @Autowired
    private SessionFactory factory;

    public UserDaoImpl() {
    }

    public UserDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Long create(User user) {
        Session session = null;
        return (Long)factory.getCurrentSession().save(user);
    }

    @Override
    public User read(Long id) {
        return (User)factory.getCurrentSession().get(User.class, id);

    }

    @Override
    public boolean update(User user) {
        factory.getCurrentSession().update(user);
        return true;
    }

    @Override
    public boolean delete(User user) {
        factory.getCurrentSession().delete(user);
        return true;
    }

    @Override
    public List findAll() {
        return factory.getCurrentSession().createQuery("from User").list();
    }

    @Override
    public User getUserByName(String name) {
       // Query query=factory.getCurrentSession().createQuery("SELECT object(user) from User user where user.name=:name");
        Query query=factory.getCurrentSession().createQuery("SELECT user.id from User user where user.name=:name");

        query.setParameter("name",name);
        Long id = (Long)query.uniqueResult();
        System.out.println("user id="+id);
        if(id==null)
            return null;
        return read(id);
      //  return (User)query.uniqueResult();
    }

    @Override
    public boolean block(User user, boolean isBlocked) {
        user.setBlocked(isBlocked);
        update(user);
        return true;
    }

    //    @Override
//    public boolean isUserValid(String login, String password) {
//        Query query=factory.getCurrentSession().createQuery("SELECT object(user) from User user where user.name=:name and user.password=:password");
//        query.setParameter("name",login);
//        query.setParameter("password",password);
//        return (boolean)query.uniqueResult();
//    }
}
