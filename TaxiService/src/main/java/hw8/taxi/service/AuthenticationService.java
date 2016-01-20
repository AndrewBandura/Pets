package hw8.taxi.service;

import hw8.taxi.domain.Role;
import hw8.taxi.exception.AuthenticationException;

/**
 * Created by Andrew on 15.10.2015.
 */
public interface AuthenticationService {
    boolean authenticate(String login, String pass) throws AuthenticationException;
    boolean block(String login, String pass,boolean isBlocked);
    boolean register(String login, String id, String pass, String confirm,Role role) throws AuthenticationException;
    Role getRole(String login) throws AuthenticationException;
}
