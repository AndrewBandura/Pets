package hw8.taxi.service;

import hw8.taxi.domain.Client;
import hw8.taxi.domain.Order;
import hw8.taxi.exception.OrderException;

import java.util.List;

/**
 * Created by Andrew on 19.10.2015.
 */
public interface OrderService {
    Long create(Order order);
    Order read(Long id);
    boolean update(Order order);
    boolean delete(Order order);
    List findAll();
    List findAllWeb(Long idDriver);
    List findAllAndroid();
    boolean createOrder(Long id, Client client, String amount, String addressFrom, String addressTo) throws OrderException;
    void editOrder(Long id, Client client, String amount, String addressFrom, String addressTo) throws OrderException;
    List showOrders(Long from, Long to);
    List showOrdersByPortion();
}
