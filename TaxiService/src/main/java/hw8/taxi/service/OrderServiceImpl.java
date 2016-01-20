package hw8.taxi.service;

import hw8.taxi.dao.OrderDao;
import hw8.taxi.domain.Client;
import hw8.taxi.domain.Order;
import hw8.taxi.exception.OrderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Andrew on 16.10.2015.
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    public OrderServiceImpl() {
    }

    @Override
    public Long create(Order order) {
        return orderDao.create(order);
    }

    @Override
    @Transactional(readOnly = true)
    public Order read(Long id) {
        return orderDao.read(id);
    }

    @Override
    public boolean update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public boolean delete(Order order) {
        return orderDao.delete(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List findAllWeb(Long idDriver) {
        return orderDao.findAllWeb(idDriver);
    }

    @Override
    @Transactional(readOnly = true)
    public List findAllAndroid() {
        return orderDao.findAllAndroid();
    }
    @Override
    public boolean createOrder(Long id, Client client, String amount,
                               String addressFrom, String addressTo) throws OrderException {
        if (orderDao.read(id) != null) {
            throw new OrderException("Order with the same id already exist!");
        }
        Order order = new Order(id, new Date(), client, addressFrom, addressTo, Double.valueOf(amount));
        if (orderDao.create(order) == null) {
            throw new OrderException("Order creation failed");
        }
        return true;
    }

    @Override
    public void editOrder(Long id, Client client, String amount, String addressFrom, String addressTo) throws OrderException {
        Order order = orderDao.read(id);
        if (order == null) {
            throw new OrderException("No such order!");
        }
        order.setClient(client);
        order.setAddressFrom(addressFrom);
        order.setAddressTo(addressTo);
        order.setAmount(Double.valueOf(amount));
        orderDao.update(order);

    }

    @Override
    @Transactional(readOnly = true)
    public List showOrders(Long from, Long to) {
        return orderDao.showOrders(from, to);
    }

    @Override
    @Transactional(readOnly = true)
    public List showOrdersByPortion() {
        return orderDao.showOrdersByPortion();
    }
}
