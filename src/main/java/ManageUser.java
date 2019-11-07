import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


import java.util.Date;
import java.util.List;

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

        list.stream().forEach(System.out::println);
        Session session = sessionFactory.openSession();
        List<User> users;
        String sql= "SELECT * from USERS1 WHERE id>5";
        SQLQuery query = session.createSQLQuery(sql);
     users =query.list();

        System.out.println(users
                .size());

        sessionFactory.close();
    }

    public List<User> fetchUsersFromDB() {

        List<User> users;

        Session session = sessionFactory.openSession();
//        Transaction transaction = null;
        String sql_query = "from User";
        Query query = session.createQuery(sql_query);
        users = query.list();
        return users;

    }


    public Integer addUser(String nick, String email, String password) {
        Session session = sessionFactory.openSession();

        Transaction tx = null;
        Integer userID = null;
        Date today = new Date();
        try {
            tx = session.beginTransaction();   //fixme (why transction is to cast)
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
}
