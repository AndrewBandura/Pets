package hw8.taxi.dao;

import hw8.taxi.domain.Order;
import hw8.taxi.domain.User;
import hw8.taxi.util.Properties;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 19.10.2015.
 */
@Repository
public class OrderDaoImpl implements OrderDao{
    private static Logger log = Logger.getLogger(OrderDaoImpl.class);

    @Autowired
    private SessionFactory factory;
    @Autowired
    Properties properties;

    public OrderDaoImpl() {
    }

    @Override
    public Long create(Order order) {
        return (Long)factory.getCurrentSession().save(order);
    }

    @Override
    public Order read(Long id) {
        return (Order) factory.getCurrentSession().get(Order.class, id);
    }

    @Override
    public boolean update(Order order) {
        try {
            factory.getCurrentSession().update(order);
        } catch (HibernateException e) {
            log.error("Open session failed", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Order order) {
        try {
            factory.getCurrentSession().delete(order);
        } catch (HibernateException e) {
            log.error("Open session failed", e);
            return false;
        }
        return true;
    }

    @Override
    public List findAll() {
        return factory.getCurrentSession().createQuery("from Order").list();
    }

    @Override
    public List findAllWeb(Long idDriver) {
        User currentUser = (User) factory.getCurrentSession().get(User.class, idDriver);
        String strQuery="Select o.id,c.name, c.phone,o.addressFrom,o.addressTo,o.amount from Order o left join o.client c where o.user=:user OR o.user=null";
        Query query = factory.getCurrentSession().createQuery(strQuery);
        query.setParameter("user", currentUser);
        return query.list();
    }

    @Override
    public List findAllAndroid() {
        String strQuery="Select o.id,c.name, c.phone,o.addressFrom,o.addressTo,o.amount from Order o left join o.client c";
        Query query = factory.getCurrentSession().createQuery(strQuery);
        return query.list();
    }

    @Override
    public List showOrders(Long from, Long to) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Order o where o.amount >= :amountFrom and o.amount < :amountTo");
        query.setParameter("amountFrom", Double.valueOf(from));
        query.setParameter("amountTo", Double.valueOf(to));
        return query.list();
    }

    @Override
    public List showOrdersByPortion() {
        int portionSize = properties.getPortionSize();
        List<Order> resultList = new ArrayList<>();
        try {
            Session session = factory.getCurrentSession();
            Query countQuery = session.createQuery("Select count (*) from Order");
            Long countResults = (Long) countQuery.uniqueResult();
            int currentPosition =0;
            String strQuery = "SELECT OBJECT(o) from Order o";
            Query orderQuery = session.createQuery(strQuery);
            while (currentPosition < countResults) {
                orderQuery.setFirstResult(currentPosition);
                orderQuery.setMaxResults(portionSize);
                resultList.addAll(orderQuery.list());
                currentPosition+=portionSize;
            }
        } catch (HibernateException e) {
            log.error("Open session failed", e);
            return null;
        }
        return resultList;
    }

}
