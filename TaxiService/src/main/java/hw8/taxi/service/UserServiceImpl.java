package hw8.taxi.service;

import hw8.taxi.dao.UserDao;
import hw8.taxi.domain.User;
import hw8.taxi.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Andrew on 15.10.2015.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private Properties properties;

    public UserServiceImpl() {
    }

    @Override
    public Long create(User user) {
        return userDao.create(user);
    }

    @Override
    public User read(Long id) {
        return userDao.read(id);
    }

    @Override
    public boolean update(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean delete(User user) {
        return userDao.delete(user);
    }

    @Override
    public List findAll() {
        return userDao.findAll();
    }

    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    @Override
    public boolean block(User user, boolean isBlocked) {
        return userDao.block(user,isBlocked);
    }
}
