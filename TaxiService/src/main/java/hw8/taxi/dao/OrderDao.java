package hw8.taxi.dao;

import hw8.taxi.domain.Order;

import java.util.List;

/**
 * Created by Andrew on 19.10.2015.
 */
public interface OrderDao {
    Long create(Order order);
    Order read(Long id);
    boolean update(Order order);
    boolean delete(Order order);
    List findAll();
    List findAllWeb(Long idDriver);
    List findAllAndroid();
    List showOrders(Long from, Long to);
    List showOrdersByPortion();
}
