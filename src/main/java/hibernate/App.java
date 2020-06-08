package hibernate;

import hibernate.entity.SinhvienEntity;
import hibernate.view.LoginView;
import org.hibernate.*;
import org.hibernate.cfg.*;

import java.util.List;

public class App {
    public static void main(String[] args) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session session = sf.openSession();
        //session.beginTransaction();
        String query = "FROM SinhvienEntity";
        List<SinhvienEntity> sinhvien = session.createQuery(query, SinhvienEntity.class).list();

        for (SinhvienEntity sv: sinhvien){
            System.out.println(sv.getHoten());
        }
        LoginView loginView = new LoginView();
        loginView.setVisible(true);
    }
}
