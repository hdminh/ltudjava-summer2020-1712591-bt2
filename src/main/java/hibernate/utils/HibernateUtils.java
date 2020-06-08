package hibernate.utils;

import hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import static jdk.nashorn.internal.objects.NativeError.printStackTrace;

public class HibernateUtils {
    private static final StandardServiceRegistry registry;
    private static final SessionFactory sessionFactory;

    static{
        registry = new StandardServiceRegistryBuilder()
                .configure().build();
        SessionFactory _sessionFacroty;
        try{
            MetadataSources metadataSources = new MetadataSources(registry);
            metadataSources.addAnnotatedClass(BangdiemEntity.class);
            metadataSources.addAnnotatedClass(LophocEntity.class);
            metadataSources.addAnnotatedClass(UserEntity.class);
            metadataSources.addAnnotatedClass(SinhvienEntity.class);
            metadataSources.addAnnotatedClass(MonhocEntity.class);
            metadataSources.addAnnotatedClass(DonphuckhaoEntity.class);

            Metadata metadata = metadataSources.buildMetadata();
            _sessionFacroty = metadata.buildSessionFactory();
        }
        catch (Exception ex){
            _sessionFacroty = null;
            printStackTrace(ex);

            StandardServiceRegistryBuilder.destroy(registry);
        }

        sessionFactory = _sessionFacroty;
    }

    public static Session openSession() {
        return sessionFactory.openSession();
    }

}

