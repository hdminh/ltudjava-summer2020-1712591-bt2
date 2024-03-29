package hibernate.dao;

import hibernate.entity.SinhvienEntity;
import hibernate.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static hibernate.utils.HibernateUtils.openSession;

public class UserDao {

    public UserDao(){
    }

    public void addList(List<SinhvienEntity> students) {
        Session session = openSession();
        Transaction tx = null;
        UserEntity user;
        try {
            tx = session.beginTransaction();
            for (SinhvienEntity sv : students) {
                user = new UserEntity(sv.getMssv().trim(), sv.getMssv().trim());
                session.save(user);
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

    public List<UserEntity> readListUsers() {
        Session session = openSession();
        String query = "FROM UserEntity ";
        List<UserEntity> user = session.createQuery(query, UserEntity.class).list();
        return user;
    }

    public void add(UserEntity user) {
        Session session = openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(user);
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

    public void edit(UserEntity user) {
        Session session = openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(user);
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

    public boolean delete(UserEntity user) {
        Session session = openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.delete(user);
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
