package hibernate.dao;

import hibernate.entity.SinhvienEntity;
import hibernate.entity.UserEntity;
import org.hibernate.Session;

import java.util.List;

import static hibernate.utils.HibernateUtils.openSession;

public class UserDao {
    List<UserEntity> listUsers;

    public UserDao(){
        listUsers = readListUsers();
    }

    private List<UserEntity> readListUsers() {
        Session session = openSession();
        String query = "FROM UserEntity ";
        List<UserEntity> user = session.createQuery(query, UserEntity.class).list();
        return user;
    }

    public boolean checkAdmin(UserEntity user) {
        if (user != null) {
            if ("giaovu".equals(user.getUsername())
                    && "giaovu".equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkLogin(String username, String password){
        UserEntity userEntity = new UserEntity(username, password);
        return (listUsers.contains(userEntity));
    }
}
