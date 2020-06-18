package hibernate.dao;

import hibernate.entity.DanhsachlopEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static hibernate.utils.HibernateUtils.openSession;

public class DanhsachlopDao {
    public DanhsachlopDao(){
    }

    public void addList(List<DanhsachlopEntity> students) {
        Session session = openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (DanhsachlopEntity sv : students) {
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

    public List<DanhsachlopEntity> readListStudents() {
        Session session = openSession();
        String query = "FROM DanhsachlopEntity";
        List<DanhsachlopEntity> sinhvien = session.createQuery(query, DanhsachlopEntity.class).list();
        return sinhvien;
    }

    public void add(DanhsachlopEntity student) {
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

    public void update(DanhsachlopEntity student) {
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

    public boolean delete(DanhsachlopEntity student) {
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

    public List<DanhsachlopEntity> readListByMon(String mon) {
        Session session = openSession();
        String query = "SELECT sv FROM DanhsachlopEntity sv WHERE sv.monhoc = '" + mon + "'";
        List<DanhsachlopEntity> sinhvien = session.createQuery(query, DanhsachlopEntity.class).list();
        return sinhvien;
    }
}
