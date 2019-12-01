package HibernateUtil;

import CustomerModel.Adress;
import CustomerModel.Client;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class ManageCustomer {

    public static SessionFactory sessionFactory;

    public static void main(String[] args) throws ParseException {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create session factory object." + ex);
            throw new ExceptionInInitializerError(ex);

        }
        ManageCustomer manageCustomer = new ManageCustomer();

//        Adress ad1 = new Adress("Poland", "Lodz","93-324","Socjalna","18A","2B");

//      manageCustomer.addClient("891001109123","Dominika","Lewandowska");

         manageCustomer.addClientwithAddress("680112850201", "Teresa", "Kobierzycka", "polska", "Tarnów", "68-300", "Podgórze", "2", null, "81052678900", "Tomasz", "Tomala");


//        List<Client> clients = createClientList();
//        manageCustomer.addListOfClientWithTheSameAddress("Polska", "Lodz", "Mieszczanska", "93-120", "1", "1", clients);

        for (Object[] arr : manageCustomer.fetchClientFirstNameLastNameCity()) {
            System.out.print(arr[0] + " " + arr[1] + " mieszka w ");
            System.out.println(arr[2] != null ? arr[2] : " ");

        }


        System.out.println("usunieto klientów: " + manageCustomer.deleteClient(84));
        System.out.println("zmodyfikowano imie w " + manageCustomer.updateClientName(83, "Zofia") + " pozycjach");
        System.out.println("Lista klientów po id");
        manageCustomer.fetchClientOrderById().stream().forEach(x -> System.out.println(x));
        System.out.println("Lista klientów po imieniu");
        manageCustomer.fetchClientOrderByFirstName()
                .stream().forEach(x -> System.out.println(x));
        System.out.println("Lista klientów po miescie");
        manageCustomer.fetchClientOrderByCity()
                .stream().forEach(x -> System.out.println(x));

        System.out.println("Lista klientów po miescie malejaca");
        manageCustomer.fetchClientOrderByCityDESC()
                .stream().forEach(x -> System.out.println(x));

        manageCustomer.addAddress("Polska", "Zamość", "80-120", "Wschodnia", "1", "2a");

        System.out.println("lista klientów urodzonych po 1995");
        manageCustomer.fetchClientBornAfter("1995-01-01").stream().forEach(x -> System.out.println(x));

        System.out.println("lista peseli");
        manageCustomer.fetchPesel().stream().forEach(x -> System.out.println(x));
    }

    public List<Object[]> fetchClientFirstNameLastNameCity() {

        List<Object[]> clients;

        Session session = sessionFactory.openSession();
//        Transaction transaction = null;
        String sql_query = "select client.firstName, client.lastName, address.city from Client client inner join client.address address";
        Query query = session.createQuery(sql_query);
        clients = query.list();
        session.close();
        return clients;

    }

    public List<Client> fetchClientOrderById() {
        List<Client> clients = Collections.EMPTY_LIST;

        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("from Client client order by client.id");
            clients = query.list();
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();

            e.printStackTrace();

        } finally {
            session.close();
        }

        return clients;
    }

    public List<Client> fetchClientOrderByCity() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<Client> clientsByCity = Collections.EMPTY_LIST;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from Client client order by client.address.city");

            clientsByCity = query.list();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();

            e.printStackTrace();


        } finally {
            session.close();
        }

        return clientsByCity;
    }

    public List<Client> fetchClientOrderByCityDESC() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<Client> clientsByCity = Collections.EMPTY_LIST;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from Client client order by client.address.city desc");

            clientsByCity = query.list();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();

            e.printStackTrace();


        } finally {
            session.close();
        }

        return clientsByCity;
    }

    public List<Client> fetchClientOrderByFirstName() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<Client> clientsByName = Collections.EMPTY_LIST;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from Client client order by client.firstName");

            clientsByName = query.list();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();

            e.printStackTrace();


        } finally {
            session.close();
        }

        return clientsByName;
    }

    public List<Client> fetchClientBornAfter(String date) throws ParseException {
        Session session = sessionFactory.openSession();
        Transaction tx;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = simpleDateFormat.parse(date);
        List<Client> clientsbornAfterDate = Collections.EMPTY_LIST;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("select client From Client client where client.dateOfBirth>:date1");
            query.setParameter("date1", date1);

            clientsbornAfterDate = query.list();


            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
        }

        return clientsbornAfterDate;
    }


    public static List<Client> addClientList() {
        Scanner scanner = new Scanner(System.in);
        List<Client> clients = new ArrayList<>();
        Date date = new Date();

        boolean isfinish = true;
        while (isfinish) {
            Client client = new Client();
            System.out.println("nazwisko");
            client.setLastName(scanner.nextLine());
            System.out.println("imie");
            client.setFirstName(scanner.nextLine());
            System.out.println("pesel");
            client.setPesel(scanner.nextLine());
            client.setDateOfBirth(date);
            clients.add(client);
            System.out.println("czy konczymy");
            String decyzja = scanner.nextLine();
            if (decyzja.equalsIgnoreCase("tak"))
                isfinish = false;
        }

        return clients;
    }

    public List<String> fetchPesel() {
        Session session = sessionFactory.openSession();
        Transaction tx;
        List<String> peselList = Collections.EMPTY_LIST;
        List<String> peselListnew = Collections.EMPTY_LIST;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("select client.pesel from Client client");

            peselList = query.list(); //fixme lista peselow 10 znaków
            System.out.println(peselList.size());

            peselListnew = peselList.stream().map(x -> x.substring(0, 10)).collect(Collectors.toList());

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return peselListnew;

    }


    //method to train one to many relation
    public void addListOfClientWithTheSameAddress(String country, String city, String street, String postcode, String no1, String no2, List<Client> clients) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
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

        } catch (HibernateException e) {
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
            client.setDateOfBirth(date);


            Client client1 = new Client();
            client1.setFirstName(name1);
            client1.setLastName(lastname1);
            client1.setPesel(pesel1);
            client1.setDateOfBirth(date);


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

    public int deleteClient(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        int numberOfRemovedClient = 0;
        try {
            tx = session.beginTransaction();
            String hqlQuery = "delete from Client client where client.id=:id";
            Query query = session.createQuery(hqlQuery);
            query.setParameter("id", id);
            numberOfRemovedClient = query.executeUpdate();
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();

        } finally {
            session.close();
        }
        return numberOfRemovedClient;
    }

    public int updateClientName(int id, String firstname) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        int numberOfModfificatedClient = 0;

        try {
            tx = session.beginTransaction();
            String hqlQuery = "update Client client set client.firstName=:firstname where client.id=:id";
            Query query = session.createQuery(hqlQuery);
            query
                    .setParameter("firstname", firstname)
                    .setParameter("id", id);
            System.out.println("test");
            numberOfModfificatedClient = query.executeUpdate();

            tx.commit();

        } catch (HibernateException e) {
//        if(tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return numberOfModfificatedClient;


    }

    public Integer addClientWithEmptyAddress(String pesel, String name, String lastname) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Date today = new Date();
        Integer clientId = null;
        Adress adress = new Adress();
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("select adress from Adress adress where adress.country=null");
            adress = (Adress) query.list().get(0);
            Client client = new Client(pesel, name, lastname, today, adress);
            session.save(adress);
            clientId = (Integer) session.save(client);

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return clientId;

    }

    public void addAddress(String country, String city, String postcode, String street, String noOfResidance, String noOfApartmnet) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Adress adress = new Adress(country, city, postcode, street, noOfResidance, noOfApartmnet);

            session.save(adress);

            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();

            e.printStackTrace();

        } finally {
            session.close();
        }
    }


}



