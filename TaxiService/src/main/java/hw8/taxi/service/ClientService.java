package hw8.taxi.service;

import hw8.taxi.domain.Client;
import hw8.taxi.exception.ClientException;

import java.util.List;

/**
 * Created by Andrew on 19.10.2015.
 */
public interface ClientService {
    Long create(Client client);
    boolean createClient(String name, String surname, String phone, String address) throws ClientException;
    Client read(Long id);
    boolean update(Client client);
    boolean delete(Client client);
    List findAll();
    List showClientsByPortion(int portionSize);
    List showClientsGtSum(int sum);
    List showClientsLastMonth();
}
