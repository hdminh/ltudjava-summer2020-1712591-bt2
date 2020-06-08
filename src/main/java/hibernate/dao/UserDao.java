package hibernate.dao;

import hibernate.entity.UserEntity;

public class UserDao {
    public boolean checkAdmin(UserEntity user) {
        if (user != null) {
            if ("giaovu".equals(user.getUsername())
                    && "giaovu".equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
