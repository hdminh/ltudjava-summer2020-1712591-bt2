package hibernate.dao;

import hibernate.entity.DotphuckhaoEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static hibernate.utils.HibernateUtils.openSession;

public class DotphuckhaoDao {
    public DotphuckhaoDao(){

    }
    public void addList(List<DotphuckhaoEntity> students) {
        Session session = openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (DotphuckhaoEntity sv : students) {
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

    public List<DotphuckhaoEntity> readListDotphuckhao() {
        Session session = openSession();
        String query = "FROM DotphuckhaoEntity";
        List<DotphuckhaoEntity> sinhvien = session.createQuery(query, DotphuckhaoEntity.class).list();
        return sinhvien;
    }

    public List<DotphuckhaoEntity> readListDotphuckhaoConHan() {
        Session session = openSession();
        String query = "FROM DotphuckhaoEntity WHERE ngaybatdau <= CURRENT_DATE AND ngayketthuc >= CURRENT_DATE";
        List<DotphuckhaoEntity> sinhvien = session.createQuery(query, DotphuckhaoEntity.class).list();
        return sinhvien;
    }


    public void add(DotphuckhaoEntity dot) {
        Session session = openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(dot);
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

    public void update(DotphuckhaoEntity student) {
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

    public boolean delete(DotphuckhaoEntity dot) {
        Session session = openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String query = "SELECT d FROM DotphuckhaoEntity d WHERE d.ngaybatdau = '"
                    + dot.getNgaybatdau() + "' AND d.ngayketthuc = '" + dot.getNgayketthuc() + "'";
            DotphuckhaoEntity dotToDelete = session.createQuery(query, DotphuckhaoEntity.class).getSingleResult();

            session.delete(dotToDelete);
            tx.commit();
        } catch (RuntimeException ex) {
            tx.rollback();
            throw ex;
        } finally {
            session.close();
            return true;
        }
    }
}
