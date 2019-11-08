import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;


import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ManageUser {

    public static SessionFactory sessionFactory;

    public static void main(String[] args) {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create session factory object." + ex);
            throw new ExceptionInInitializerError(ex);

        }

        ManageUser manageUser = new ManageUser();
        //Integer userId = manageUser.addUser("superkolo", "michal2005k@gmail.com", "qeoipwjrijrfewwffgwij923");

        List<User> list = manageUser.fetchUsersFromDB();
        System.out.println("number of user in db:" + list.size());

        // list.stream().forEach(System.out::println);
        Session session = sessionFactory.openSession();
        List<User> users;
//        String sql= "SELECT * from USERS1 WHERE id>5";
//    SQLQuery query = session.createSQLQuery(sql); //  to jest przestarzały typ zapytań

        //   NativeQuery nativeQuery = session.createNativeQuery("SELECT * from USERS1 WHERE id>5");
        // users= nativeQuery.list();


//     users =query.list();


//        System.out.println(users
//                .size());

//        users.stream().forEach(x-> System.out.println(x));

        System.out.println("*********wszystkie nicki w bazie*****");
        manageUser.returnAllnick().stream().forEach(x -> System.out.println(x));

        System.out.println("*********wszystkie maile w bazie*****");
        manageUser.returnAllemail().stream().forEach(x -> System.out.println(x));


        manageUser.searchUserbyEmail("jadzia1970@wp.com").stream().forEach(x -> System.out.println(x));

        Date date = new Date("2019/11/01");


        System.out.println("uzytkownicy zarejestrowani po " + date);
        manageUser.findUserRegisterAfterDate(date).stream().forEach(System.out::println);


        manageUser.updateNick("Balik123111",120);
        System.out.println("usunięto: "+manageUser.removeUserFromDB(45)+" rekordów");
    }

    public List<User> fetchUsersFromDB() {

        List<User> users;

        Session session = sessionFactory.openSession();
//        Transaction transaction = null;
        String sql_query = "from User";
        Query query = session.createQuery(sql_query);
        users = query.list();
        session.close();
        return users;

    }

    public List<String> returnAllnick() {
        List<String> list;

        Session session = sessionFactory.openSession();

        Query query = session.createQuery("select user.nick from User user");


        list = query.list();
        session.close();
        return list;


    }

    public List<String> returnAllemail() {
        List<String> list;

        Session session = sessionFactory.openSession();

        Query query = session.createQuery("select user.email from User user");


        list = query.list();
        session.close();
        return list;


    }

    public void updateNick(String nick, int id) {
        Session session = sessionFactory.openSession();

        Transaction tx;
        tx = session.beginTransaction();
        String hqlQuery = "update User user set user.nick=:nick where user.id=:id";
        Query query = session.createQuery(hqlQuery);
        query.setParameter("nick",nick).setParameter("id",id);
        int modification = query.executeUpdate();
        System.out.println("numberOfModification:"+modification);
        tx.commit();
        session.close();

    }

    public List<User> searchUserbyEmail(String email) {
        List<User> users;

        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select user from User user where user.email=: email");
        query.setParameter("email", email);

        users = query.list();
        session.close();
        return users;


    }

    public List<User> findUserRegisterAfterDate(Date date) {
        List<User> users;

        Session session = sessionFactory.openSession();

        Query query = session.createQuery("select user from User user where user.dateOfFirstRegister>:date");

        query.setParameter("date", date);

        users = query.list();
        session.close();
        return users;

    }


    public Integer addUser(String nick, String email, String password) {
        Session session = sessionFactory.openSession();

        Transaction tx = null;
        Integer userID = null;
        Date today = new Date();
        try {
            tx = session.beginTransaction();
            User user = new User(email, nick, password, today);
            userID = (Integer) session.save(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return userID;

    }

    public int removeUserFromDB(int id)
    {Session session=sessionFactory.openSession();
    Transaction tx;
    tx = session.beginTransaction();
    Query query= session.createQuery("delete from User user where user.id=:iduser");
    query.setParameter("iduser",id);

    int rowsdeleted =query.executeUpdate();
    tx.commit();
    session.close();

    return rowsdeleted;



    }
}
