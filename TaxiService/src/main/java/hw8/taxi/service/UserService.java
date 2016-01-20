package hw8.taxi.service;

import hw8.taxi.domain.User;

import java.util.List;

/**
 * Created by Andrew on 15.10.2015.
 */
public interface UserService {
    Long create(User user);
    User read(Long id);
    boolean update(User user);
    boolean delete(User user);
    List findAll();
    User getUserByName(String name);
    boolean block(User user,boolean isBlocked);
}
