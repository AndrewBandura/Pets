package hw8.taxi.service;

import hw8.taxi.dao.UserDao;
import hw8.taxi.domain.Role;
import hw8.taxi.domain.User;
import hw8.taxi.exception.AuthenticationException;
import hw8.taxi.exception.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by Andrew on 15.10.2015.
 */
@Service
@Transactional
public class AuthorizationServiceImpl implements AuthorizationService{
    @Autowired
    private UserDao userDao;

    public AuthorizationServiceImpl() {
    }
    @Override
    public boolean registerWithPasswordConfirm(String login, String id, String pass, String confirm, Role role) throws AuthenticationException {
        if (userDao.getUserByName(login) != null) {
            throw new AuthorizationException("Login is not unique!");
        }
        if (login.length()<4) {
            throw new AuthorizationException("Login should be at least 4 symbols long!");
        }
        if (login.contains(" ")) {
            throw new AuthorizationException("Login shouldn't contain spaces!");
        }
        if (!pass.matches("(?=^.{8,}$)(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$")) {
            throw new AuthorizationException("Password should be at least 8 symbols length,\n" +
                    "contains capital and small letters and digits");
        }
        if (!confirm.equals(pass)) {
            throw new AuthorizationException("Password and confirmation mismatch!");
        }
        if (!id.matches("[0-9]{10}")) {
            throw new AuthorizationException("ID should be 10 symbols length and contain only digits");
        }

        User user = new User(login, pass, new Date(System.currentTimeMillis()), role, id);
        try {
            userDao.create(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AuthorizationException(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean register(String login, String id, String pass,Role role) throws AuthenticationException {
        if (userDao.getUserByName(login) != null) {
            throw new AuthorizationException("Login is not unique!");
        }
        if (login.length()<4) {
            throw new AuthorizationException("Login should be at least 4 symbols long!");
        }
        if (login.contains(" ")) {
            throw new AuthorizationException("Login shouldn't contain spaces!");
        }
        if (!pass.matches("(?=^.{8,}$)(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$")) {
            throw new AuthorizationException("Password should be at least 8 symbols length,\n" +
                    "contains capital and small letters and digits");
        }

        if (!id.matches("[0-9]{10}")) {
            throw new AuthorizationException("ID should be 10 symbols length and contain only digits");
        }

        User user = new User(login, pass, new Date(System.currentTimeMillis()),role, id);
        try {
            userDao.create(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AuthorizationException(e.getMessage());
        }
        return true;
    }
}
