package CustomerModel;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

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

//        Adress ad1 = new Adress("Poland", "Lodz","93-324","Socjalna","18A","2B");

//        manageCustomer.addClient("791001109123","Maria","Limonkowska",ad1);

        manageCustomer.addClientwithAddress("680112850201","Teresa","Kobierzycka","polska","Tarnów","68-300","Podgórze","2",null,"81052678900","Tomasz","Tomala");

        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();

    }

    public void addClientwithAddress(String pesel, String name, String lastname, String country, String city, String postcode, String street, String no1, String no2,String pesel1, String name1, String lastname1)

    {Session session = sessionFactory.openSession();
    Transaction tx = null;
    Date date = new Date();

    try {
tx =session.beginTransaction();
Client client = new Client();
client.setFirstName(name);
client.setLastName(lastname);
client.setPesel(pesel);
client.setDob(date);


        Client client1 = new Client();
        client1.setFirstName(name1);
        client1.setLastName(lastname1);
        client1.setPesel(pesel1);
        client1.setDob(date);



Adress adress = new Adress(country,city,postcode,street,no1,no2);

        client.setAdress(adress);
        client1.setAdress(adress);
        Set<Client> addreses = new HashSet<Client>();
        addreses.add(client);
        addreses.add(client1);
adress.setClients(addreses);

session.save(adress);
        session.save(client);
        session.save(client1);
tx.commit();

    }

    catch (HibernateException e) {
        if (tx != null) tx.rollback();
        e.printStackTrace();
    } finally {
        session.close();
    }

    }

    public Integer addClient(String pesel, String name, String lastname)
    {
        Session session = sessionFactory.openSession();
        Transaction tx =null;
        Date today = new Date();
        Integer clientId=null;

        try {
            tx = session.beginTransaction();
//            Client client = new Client(pesel, name, lastname, today,a);
//            clientId = (Integer) session.save(client);

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



