package hw8.taxi.service;
import hw8.taxi.dao.ClientDao;
import hw8.taxi.domain.Client;
import hw8.taxi.domain.Order;
import hw8.taxi.exception.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Andrew on 19.10.2015.
 */
@Service
@Transactional
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientDao clientDao;

    public ClientServiceImpl() {
    }

    @Override
    public Long create(Client client) {
        return clientDao.create(client);
    }

    @Override
    public boolean createClient(String name, String surname, String phone, String address) throws ClientException{
        Client client = new Client(name, surname, phone, address);
        if (clientDao.create(client) == null) {
            throw new ClientException();
        }
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public Client read(Long id) {
        return clientDao.read(id);
    }

    @Override
    public boolean update(Client client) {
        return clientDao.update(client);
    }

    @Override
    public boolean delete(Client client) {
        return clientDao.delete(client);
    }

    @Override
    @Transactional(readOnly = true)
    public List findAll() {
        return clientDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List showClientsByPortion(int portionSize) {
        return clientDao.showClientsByPortion(portionSize);
    }

    @Override
    @Transactional(readOnly = true)
    public List showClientsGtSum(int sum) {return clientDao.showClientsGtSum(sum);
    }

    @Override
    @Transactional(readOnly = true)
    public List showClientsLastMonth() {return clientDao.showClientsLastMonth();
    }
}
