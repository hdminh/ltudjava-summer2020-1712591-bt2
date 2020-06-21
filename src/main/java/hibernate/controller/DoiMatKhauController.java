package hibernate.controller;

import hibernate.dao.UserDao;
import hibernate.entity.UserEntity;
import hibernate.view.DoiMatKhauView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoiMatKhauController {
    private final UserDao userDao;
    private UserEntity isLogin;
    private DoiMatKhauView doiMatKhauView;

    public DoiMatKhauController(UserEntity user){
        this.doiMatKhauView = new DoiMatKhauView();
        isLogin = user;
        this.userDao = new UserDao();
        doiMatKhauView.addCommitListener(new DoiMatKhauController.CommitListener());
        doiMatKhauView.addLogoutListener(new DoiMatKhauController.LogoutListener());
    }

    public Container getContentPane() {
        return doiMatKhauView.getContentPane();
    }

    public void showView() {
        doiMatKhauView.setVisible(true);
    }

    class CommitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String oldP = doiMatKhauView.getOldField();
            String newP = doiMatKhauView.getNewField();
            String retypeP = doiMatKhauView.getRetypeField();

            if (!oldP.equals(isLogin.getPassword())){
                doiMatKhauView.showMessage("Mật khẩu không đúng");
            }
            else if (!newP.equals(retypeP)){
                doiMatKhauView.showMessage("Mật khẩu mới không khớp");
            }
            else {
                UserEntity changeP = new UserEntity();
                changeP.setUsername(isLogin.getUsername());
                changeP.setPassword(newP);
                userDao.edit(changeP);

                doiMatKhauView.showMessage("Đổi thành công!");
            }
        }
    }

    class LogoutListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

        }
    }
}
