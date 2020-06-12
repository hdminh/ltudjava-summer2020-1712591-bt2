package hibernate.dao;

import hibernate.entity.LophocEntity;
import hibernate.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

import static hibernate.utils.HibernateUtils.openSession;


public class LophocDao {
    //SessionFactory sf;
    private List<LophocEntity> listLophoc;

    public LophocDao(){
        //sf = new Configuration().configure().buildSessionFactory();
        listLophoc = readListLopHoc();

    }

    public List<LophocEntity> readListLopHoc() {
        Session session = openSession();
        String query = "FROM LophocEntity";
        List<LophocEntity> lophoc = session.createQuery(query, LophocEntity.class).list();
        return lophoc;
    }

    public List<String> readMaLop(){
        List<LophocEntity> entity = readListLopHoc();
        List<String> lophoc = new ArrayList<String>();
        for (LophocEntity lop: entity){
            lophoc.add(lop.getMalop());
        }
        return lophoc;
    }

    public void add(String malop){
        LophocEntity lophocEntity = new LophocEntity(malop);
        Session session = openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(lophocEntity);
            tx.commit();
        }
        catch (RuntimeException ex){
            tx.rollback();
            throw ex;
        }
        finally {
            session.close();
        }


    }
}
