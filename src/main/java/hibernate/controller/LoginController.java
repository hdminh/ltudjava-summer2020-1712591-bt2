package hibernate.controller;

import hibernate.dao.StudentDao;
import hibernate.dao.UserDao;
import hibernate.entity.UserEntity;
import hibernate.view.LoginView;
import hibernate.view.MainView;
import hibernate.view.QuanLySinhVienView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoginController {
    private UserDao userDao;
    private StudentDao studentDao;
    private LoginView loginView;
    private QuanLySinhVienView studentView;
    List<UserEntity> listUsers;

    public void updateListUser(){
        listUsers = userDao.readListUsers();
    }

    public LoginController(LoginView view) {
        this.loginView = view;
        this.userDao = new UserDao();
        if (userDao.readListUsers() == null) {
            UserEntity giaovu = new UserEntity("giaovu", "giaovu");
            userDao.add(giaovu);
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
            if ((user.getUsername().equals("giaovu")) && user.getPassword().equals("giaovu")) {
                studentView = new QuanLySinhVienView();
                MainView mainView = new MainView();
                mainView.setVisible(true);
                loginView.setVisible(false);
            } else if (listUsers.contains(user)) {
                QuanLySinhVienController qlsv = new QuanLySinhVienController(new QuanLySinhVienView());
                qlsv.showStudentView();
            }
            else{
                loginView.showMessage("Username hoặc Password không đúng.");
            }
        }
    }
}
