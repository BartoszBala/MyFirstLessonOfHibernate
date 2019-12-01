package DAO;


import CustomerModel.Adress;
import CustomerModel.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CustomerDao implements CustomerDaoInterface<Client, Adress, Integer> {

    private Session session;
    private Transaction transaction;

    public CustomerDao() {

    }

    public Session openSession() {
        session = getSessionFactory().openSession();
        return session;
    }

    public Session openSessionWithTransaction(){
        session = getSessionFactory().openSession();
        transaction=session.beginTransaction();
        return session;
    }

    public void closeSessionwithTransaction(){
    transaction.commit();
    session.close();

    }

    public void closeSession() {
        session.close();
    }

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
    //    StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        return sessionFactory;
    }

    public Session getSession() {
        return session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public void persist(Client entity, Adress adress) {
        getSession().save(adress);
        getSession().save(entity);
    }

    @Override
    public void update(Client entity, Adress addressEntity) {
        getSession().update(addressEntity);
getSession().update(entity);
    }

    @Override
    public Client findById(Integer id) {
       Client client = getSession().get(Client.class,id);
       return client;
    }

    @Override
    public void delete(Client entity) {
getSession().delete(entity);
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients =getSession().createQuery("from Client").list();
        return clients;
    }

    @Override
    public void deleteAll() {
        List<Client> entityList = findAll();
        for (Client client : entityList) {

            delete(client);

        }
    }
}
