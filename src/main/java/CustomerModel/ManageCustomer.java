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

        Adress ad1 = new Adress("Poland", "Lodz","93-324","Socjalna","18A","2B");

        manageCustomer.addClient("791001109123","Maria","Limonkowska",ad1);


    }

    public Integer addClient(String pesel, String name, String lastname, Adress adress)
    {
        Session session = sessionFactory.openSession();
        Transaction tx =null;
        Date today = new Date();
        Integer clientId=null;

        try {
            tx = session.beginTransaction();
            Client client = new Client(pesel, name, lastname, today,adress);
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



