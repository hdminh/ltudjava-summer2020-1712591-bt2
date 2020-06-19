package hibernate.dao;

import hibernate.entity.BangdiemEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static hibernate.utils.HibernateUtils.openSession;

public class BangdiemDao {
    public BangdiemDao(){
    }

    public void addList(List<BangdiemEntity> students) {
        Session session = openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (BangdiemEntity sv : students) {
                session.save(sv);
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

    public List<BangdiemEntity> readListStudents() {
        Session session = openSession();
        String query = "FROM BangdiemEntity";
        List<BangdiemEntity> sinhvien = session.createQuery(query, BangdiemEntity.class).list();
        return sinhvien;
    }

    public void add(BangdiemEntity student) {
        Session session = openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(student);
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

    public void update(BangdiemEntity student) {
        Session session = openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(student);
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

    public boolean delete(BangdiemEntity student) {
        Session session = openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.delete(student);
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

    public List<BangdiemEntity> readListByMon(String mon) {
        Session session = openSession();
        String query = "SELECT sv FROM BangdiemEntity sv WHERE sv.monhoc = '" + mon + "'";
        List<BangdiemEntity> sinhvien = session.createQuery(query, BangdiemEntity.class).list();
        return sinhvien;
    }
}
