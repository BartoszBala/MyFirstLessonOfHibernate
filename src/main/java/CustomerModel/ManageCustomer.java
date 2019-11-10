package CustomerModel;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.*;

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

     //   manageCustomer.addClientwithAddress("680112850201", "Teresa", "Kobierzycka", "polska", "Tarnów", "68-300", "Podgórze", "2", null, "81052678900", "Tomasz", "Tomala");

 //  manageCustomer.createClientList().stream().forEach(x-> System.out.println(x));

List<Client> clients = createClientList();
        manageCustomer.addListOfClientWithTheSameAddress("Polska","Lodz","Mieszczanska","93-120","1","1",clients);
    }

    public static List<Client> createClientList() {
        Scanner scanner = new Scanner(System.in);
        List<Client> clients = new ArrayList<>();
        Date date = new Date();

        boolean isfinish =true;
        while (isfinish) {
            Client client = new Client();
            System.out.println("nazwisko");
            client.setLastName(scanner.nextLine());
            System.out.println("imie");
            client.setFirstName(scanner.nextLine());
            System.out.println("pesel");
            client.setPesel(scanner.nextLine());
            client.setDob(date);
            clients.add(client);
            System.out.println("czy konczymy");
            String decyzja  = scanner.nextLine();
            if(decyzja.equalsIgnoreCase("tak"))
                isfinish=false;
        }

return clients;
    }

    public void addListOfClientWithTheSameAddress(String country, String city, String street, String postcode, String no1, String no2,List<Client> clients) {
        Session session = sessionFactory.openSession();
        Transaction tx=null;
        Date date = new Date();

        Adress adress = new Adress();
        adress.setCity(city);
        adress.setCountry(country);
        adress.setPostCode(postcode);
        adress.setStreet(street);
        adress.setNumberOfAppartment(no1);
        adress.setNumberOfAppartment(no2);

        Set<Client> addreses = new HashSet<Client>();

        System.out.println("test");

        for (Client client : clients) {

            client.setAdress(adress);
            addreses.add(client);

        }
        adress.setClients(addreses);


        try {
            tx = session.beginTransaction();
            session.save(adress);

            for (Client client : addreses) {


                session.save(client);
            }

        }

    catch (HibernateException e) {
        if (tx != null) tx.rollback();
        e.printStackTrace();
    } finally {
        session.close();
    }


    }

    public void addClientwithAddress(String pesel, String name, String lastname, String country, String city, String postcode, String street, String no1, String no2, String pesel1, String name1, String lastname1) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Date date = new Date();

        try {
            tx = session.beginTransaction();
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


            Adress adress = new Adress(country, city, postcode, street, no1, no2);

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

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public Integer addClient(String pesel, String name, String lastname) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Date today = new Date();
        Integer clientId = null;

        try {
            tx = session.beginTransaction();
//            Client client = new Client(pesel, name, lastname, today,a);
//            clientId = (Integer) session.save(client);

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return clientId;

    }


}



