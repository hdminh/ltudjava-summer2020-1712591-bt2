package hibernate.dao;

import hibernate.entity.SinhvienEntity;
import hibernate.utils.CsvUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

import static hibernate.utils.CsvUtil.*;
import static hibernate.utils.HibernateUtils.*;
import hibernate.utils.CsvUtil.*;

public class StudentDao {
    private List<SinhvienEntity> listStudents;

    public StudentDao() {
        this.listStudents = readListStudents();
    }

    public void writeToDB(List<SinhvienEntity> students) {

        listStudents = students;
        Session session = openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (SinhvienEntity sv : listStudents) {
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

    public List<SinhvienEntity> readListStudents() {
        List<SinhvienEntity> students = new ArrayList<SinhvienEntity>();
        Session session = openSession();
        String query = "FROM SinhvienEntity";
        List<SinhvienEntity> sinhvien = session.createQuery(query, SinhvienEntity.class).list();
        return sinhvien;
    }

    public void add(SinhvienEntity student) {
    }

    public void edit(SinhvienEntity student) {

    }

    public boolean delete(SinhvienEntity student) {
        return true;
    }


    public List<SinhvienEntity> getListStudents() {
        return listStudents;
    }

    public void setListStudents(List<SinhvienEntity> listStudents) {
       this.listStudents = listStudents;
    }
}
