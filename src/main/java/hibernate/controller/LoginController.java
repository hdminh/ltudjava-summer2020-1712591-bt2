package hibernate.controller;

import hibernate.dao.UserDao;
import hibernate.entity.UserEntity;
import hibernate.view.LoginView;
import hibernate.view.QuanLySinhVienView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private UserDao userDao;
    private LoginView loginView;
    private QuanLySinhVienView studentView;

    public LoginController(LoginView view) {
        this.loginView = view;
        this.userDao = new UserDao();
        view.addLoginListener(new LoginListener());
    }

    public void showLoginView() {
        loginView.setVisible(true);
    }

    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            UserEntity user = loginView.getUser();
            if (userDao.checkAdmin(user)) {
                studentView = new QuanLySinhVienView();
                QuanLySinhVienController studentController = new QuanLySinhVienController(studentView);
                studentController.showStudentView();
                loginView.setVisible(false);
            } else {
                loginView.showMessage("username hoặc password không đúng.");
            }
        }
    }
}
