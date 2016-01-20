package hw8.taxi.dao;

import hw8.taxi.domain.Client;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Andrew on 19.10.2015.
 */
@Repository
public class ClientDaoImpl implements ClientDao {

    private static Logger log = Logger.getLogger(ClientDaoImpl.class);

    @Autowired
    private SessionFactory factory;

    public ClientDaoImpl() {
    }

    @Override
    public Long create(Client client) {
        return (Long)factory.getCurrentSession().save(client);
    }

    @Override
    public Client read(Long id) {
        return (Client) factory.getCurrentSession().get(Client.class, id);
    }

    @Override
    public boolean update(Client client) {
        try {
            factory.getCurrentSession().update(client);
        } catch (HibernateException e) {
            log.error("Open session failed", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Client client) {
        try {
            factory.getCurrentSession().delete(client);
        } catch (HibernateException e) {
            log.error("Open session failed", e);
            return false;
        }
        return true;
    }

    @Override
    public List findAll() {
        return factory.getCurrentSession().createQuery("from Client").list();
    }

    @Override
    public List showClientsByPortion(int portionSize) {
        List<Client> resultList = new ArrayList<>();
        try {
            Session session = factory.getCurrentSession();
            Query countQuery = session.createQuery("Select count (*) from Client");
            Long countResults = (Long) countQuery.uniqueResult();
            int currentPosition =0;
            String strQuery = "SELECT OBJECT(c) from Client c";
            Query clientQuery = session.createQuery(strQuery);
            while (currentPosition < countResults) {
                clientQuery.setFirstResult(currentPosition);
                clientQuery.setMaxResults(portionSize);
                resultList.addAll(clientQuery.list());
                currentPosition+=portionSize;
            }
        } catch (HibernateException e) {
            log.error("Open session failed", e);
            return null;
        }
        return resultList;
    }

    @Override
    public List showClientsGtSum(int sum) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("SELECT OBJECT(c) from Client c where c in" +
                " (select o.client from Order o group by o.client having sum(o.amount) > :amount)");
        query.setParameter("amount", Double.valueOf(sum));
        return query.list();
    }

    @Override
    public List showClientsLastMonth() {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("SELECT OBJECT(c) from Client c where c in (select o.client from Order o where o.date > :date)");
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        query.setParameter("date", calendar.getTime());
        return query.list();
    }
}
