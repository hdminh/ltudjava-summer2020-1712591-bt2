package hibernate.utils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtils {
    private static SessionFactory sessionFactory;

    static{
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public static Session openSession() {
        return sessionFactory.openSession();
    }

}

