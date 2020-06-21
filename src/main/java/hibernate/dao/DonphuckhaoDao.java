package hibernate.dao;

import hibernate.entity.DonphuckhaoEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static hibernate.utils.HibernateUtils.openSession;

public class DonphuckhaoDao {
    public DonphuckhaoDao(){
    }

    public void addList(List<DonphuckhaoEntity> students) {
        Session session = openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (DonphuckhaoEntity sv : students) {
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

    public List<DonphuckhaoEntity> readListDonphuckhao() {
        Session session = openSession();
        String query = "FROM DonphuckhaoEntity";
        List<DonphuckhaoEntity> sinhvien = session.createQuery(query, DonphuckhaoEntity.class).list();
        return sinhvien;
    }


    public void add(DonphuckhaoEntity don) {
        Session session = openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(don);
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

    public void update(DonphuckhaoEntity don) {
        Session session = openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String query = "FROM DonphuckhaoEntity  WHERE sinhvien = '" + don.getSinhvien() + "' AND monhoc = '" + don.getMonhoc()
                    + "' AND cotdiem = '" + don.getCotdiem() + "'" ;
            DonphuckhaoEntity donToEdit = session.createQuery(query, DonphuckhaoEntity.class).getSingleResult();
            donToEdit.setTrangthai(don.getTrangthai());
            donToEdit.setDiemsaupk(don.getDiemsaupk());
            session.update(donToEdit);
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

    public boolean delete(DonphuckhaoEntity don) {
        Session session = openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String query = "FROM DonphuckhaoEntity  WHERE sinhvien = '" + don.getSinhvien() + "' AND monhoc = '" + don.getMonhoc()
                    + "' AND cotdiem = '" + don.getCotdiem() + "'" ;
            DonphuckhaoEntity donToDelete = session.createQuery(query, DonphuckhaoEntity.class).getSingleResult();

            session.delete(donToDelete);
            tx.commit();
        } catch (RuntimeException ex) {
            tx.rollback();
            throw ex;
        } finally {
            session.close();
            return true;
        }
    }

    public List<DonphuckhaoEntity> readListDonBySinhvien(String mssv) {
        Session session = openSession();
        String query = "FROM DonphuckhaoEntity WHERE sinhvien = '" + mssv + "'";
        List<DonphuckhaoEntity> sinhvien = session.createQuery(query, DonphuckhaoEntity.class).list();
        return sinhvien;
    }
}
