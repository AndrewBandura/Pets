package hw8.taxi.dao;

import hw8.taxi.domain.User;
import java.util.List;

/**
 * Created by Andrew on 29.09.2015.
 */
public interface UserDao {
    Long create(User user);
    User read(Long id);
    boolean update(User user);
    boolean delete(User user);
    List findAll();
    User getUserByName(String name);
    boolean block(User user, boolean isBlocked);


}
