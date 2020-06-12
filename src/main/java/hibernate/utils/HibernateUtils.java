package hibernate.utils;

import hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class HibernateUtils {
    //private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    static{
        //registry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public static Session openSession() {
        return sessionFactory.openSession();
    }

}

