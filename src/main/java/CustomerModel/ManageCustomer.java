package CustomerModel;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Date;

public class ManageCustomer {

    public static SessionFactory sessionFactory;

    public static void main(String[] args) {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create session factory object." + ex);
            throw new ExceptionInInitializerError(ex);

        }
        ManageCustomer manageCustomer = new ManageCustomer();

        manageCustomer.addClient("901212d09123","ANNia","Kowalska123");


    }

    public Integer addClient(String pesel, String name, String lastname)
    {
        Session session = sessionFactory.openSession();
        Transaction tx =null;
        Date today = new Date();
        Integer clientId=null;

        try {
            tx = session.beginTransaction();
            Client client = new Client(pesel, name, lastname, today);
            clientId = (Integer) session.save(client);

            tx.commit();

        }

        catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return clientId;

        }


    }



