package hibernate.dao;

import hibernate.entity.MonhocEntity;
import hibernate.entity.SinhvienEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static hibernate.utils.HibernateUtils.openSession;

public class MonhocDao {
    public MonhocDao(){
    }

    public void writeToDB(List<MonhocEntity> monhoc) {
        Session session = openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (MonhocEntity mh: monhoc) {
                session.save(mh);
            }
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

    public List<MonhocEntity> readListMonhoc() {
        Session session = openSession();
        String query = "FROM MonhocEntity";
        List<MonhocEntity> monhoc = session.createQuery(query, MonhocEntity.class).list();
        return monhoc;
    }

    public void add(MonhocEntity monhoc) {
        Session session = openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(monhoc);
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

    public void edit(MonhocEntity monhoc) {
        Session session = openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(monhoc);
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

    public boolean delete(MonhocEntity monhoc) {
        Session session = openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.delete(monhoc);
            tx.commit();
        }
        catch (RuntimeException ex){
            tx.rollback();
            throw ex;
        }
        finally {
            session.close();
            return true;
        }
    }
}
