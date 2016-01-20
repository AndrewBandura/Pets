package hw8.taxi.service;

import hw8.taxi.domain.Role;
import hw8.taxi.exception.AuthenticationException;

/**
 * Created by Andrew on 15.10.2015.
 */
public interface AuthorizationService {
    boolean registerWithPasswordConfirm(String login, String id, String pass, String confirm,Role role) throws AuthenticationException;
    boolean register(String login, String id, String pass,Role role) throws AuthenticationException;
}
