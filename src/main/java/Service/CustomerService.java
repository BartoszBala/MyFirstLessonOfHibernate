package Service;

import CustomerModel.Adress;
import CustomerModel.Client;
import DAO.CustomerDao;
import oracle.net.jdbc.TNSAddress.Address;

import java.util.List;

public class CustomerService {
    private static CustomerDao customerDao;

    public CustomerService() {
        customerDao = new CustomerDao();
    }

    public void persist(Client entity, Adress entityAddress)
    {customerDao.openSessionWithTransaction();
    customerDao.persist(entity, entityAddress);
    customerDao.closeSessionwithTransaction();
    }

    public void update(Client entity, Adress adress) {
        customerDao.openSessionWithTransaction();
        customerDao.update(entity,adress);
        customerDao.closeSessionwithTransaction();
    }

    public Client findById(Integer id)
    {customerDao.openSession();
    Client client =customerDao.findById(id);
    customerDao.closeSession();
    return client;
    }

    public void delete(Integer id){
        customerDao.openSessionWithTransaction();
        Client client = customerDao.findById(id);
        customerDao.delete(client);
        customerDao.closeSessionwithTransaction();
    }

    public List<Client> findAll() {
        customerDao.openSession();
        List<Client> clients = customerDao.findAll();
        customerDao.closeSession();
        return clients;
    }

    public void delteAll(){
        customerDao.closeSessionwithTransaction();
        customerDao.deleteAll();
        customerDao.closeSessionwithTransaction();
    }


}
