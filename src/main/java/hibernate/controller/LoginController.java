package hibernate.controller;

import hibernate.dao.StudentDao;
import hibernate.dao.UserDao;
import hibernate.entity.UserEntity;
import hibernate.view.LoginView;
import hibernate.view.MainView;
import hibernate.view.QuanLySinhVienView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LoginController {
    private final UserDao userDao;
    private UserEntity isLogin;
    private StudentDao studentDao;
    private LoginView loginView;
    MainController mainController;
    SinhVienController sinhVienController;
    List<UserEntity> listUsers;
    String giaovuDefault = "giaovu";
    UserEntity giaovu = new UserEntity(giaovuDefault, giaovuDefault);

    public void updateListUser(){
        listUsers = userDao.readListUsers();
    }

    public LoginController(){
        listUsers = new ArrayList<UserEntity>();
        this.loginView = new LoginView();
        this.userDao = new UserDao();
        listUsers = userDao.readListUsers();
        if (listUsers.size() == 0) {
            userDao.add(giaovu);
            updateListUser();
        }
        if (listUsers.size() == 1) {
            studentDao = new StudentDao();
            if (studentDao.readListStudents() != null){
                userDao.addList(studentDao.readListStudents());
            }
        }
        updateListUser();
        loginView.addLoginListener(new LoginListener());
    }

    public LoginController(LoginView view) {
        listUsers = new ArrayList<UserEntity>();
        this.loginView = view;
        this.userDao = new UserDao();
        listUsers = userDao.readListUsers();
        if (listUsers.size() == 0) {
            userDao.add(giaovu);
            updateListUser();
        }
        if (listUsers.size() == 1) {
            studentDao = new StudentDao();
            if (studentDao.readListStudents() != null){
                userDao.addList(studentDao.readListStudents());
            }
        }
        updateListUser();
        view.addLoginListener(new LoginListener());
    }

    public void showLoginView() {
        loginView.setVisible(true);
    }

    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            UserEntity user = loginView.getUser();

            if (user.getUsername().equals("giaovu") && listUsers.contains(user)) {
                //giao vu
                isLogin = user;
                mainController = new MainController(isLogin);
                loginView.setVisible(false);
                mainController.showMainView();

            } else if (listUsers.contains(user)) {
                //sinh vien
                isLogin = user;
                sinhVienController = new SinhVienController(isLogin);
                loginView.setVisible(false);
                sinhVienController.showSinhVienView();
            }
            else{
                loginView.showMessage("Username hoặc Password không đúng.");
            }
        }
    }
}
