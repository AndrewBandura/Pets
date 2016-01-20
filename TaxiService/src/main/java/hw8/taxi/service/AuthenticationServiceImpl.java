package hw8.taxi.service;

import hw8.taxi.domain.Role;
import hw8.taxi.domain.User;
import hw8.taxi.exception.AuthenticationException;
import hw8.taxi.exception.InvalidPasswordException;
import hw8.taxi.exception.PasswordExpiredException;
import hw8.taxi.exception.UserBlockedException;
import hw8.taxi.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by Andrew on 15.10.2015.
 */
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    UserService userService;

    @Autowired
    Properties properties;

    public AuthenticationServiceImpl() {
    }

    @Override
    public boolean authenticate(String login, String pass) throws AuthenticationException {
        User user = userService.getUserByName(login);
        if(user==null)
            throw new AuthenticationException();

        if(user.isBlocked()){
            throw new UserBlockedException();
        }

        if(user.isPasswordExpired(properties.getPasswordTermDays())){
            throw new PasswordExpiredException();
        }

        if(!user.isPasswordValid(pass)){
            throw new InvalidPasswordException();
        }

        return true;
    }

    @Override
    public boolean block(String login, String pass,boolean isBlocked) {
        User user  = userService.getUserByName(login);
        if(user==null) throw new NullPointerException();
        return userService.block(user,isBlocked);
    }

    @Override
    public boolean register(String login, String id, String pass, String confirm, Role role) throws AuthenticationException {
        if(!pass.equals(confirm)) throw new AuthenticationException("Password mismatch!");
        User user = new User(login,pass,new Date(System.currentTimeMillis()),role,id);
        userService.create(user);
        return true;
    }

    @Override
    public Role getRole(String login) throws AuthenticationException {
        User user = userService.getUserByName(login);
        if(user==null)
            throw new AuthenticationException();
        return user.getRole();
    }
}
