package hibernate.controller;

import hibernate.entity.UserEntity;
import hibernate.view.SinhVienView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SinhVienController {
    UserEntity isLogin;
    SinhVienView sinhVienView;
    SinhVienXemDiemController sinhVienXemDiemController;
    SinhVienPhucKhaoController sinhVienPhucKhaoController;
    DoiMatKhauController doiMatKhauController;

    public SinhVienController(UserEntity user){
        isLogin = user;
        sinhVienView = new SinhVienView();
        sinhVienXemDiemController = new SinhVienXemDiemController(isLogin);
        sinhVienPhucKhaoController = new SinhVienPhucKhaoController(isLogin);
        doiMatKhauController = new DoiMatKhauController(isLogin);

        sinhVienView.addTab("Xem điểm môn học", sinhVienXemDiemController.getContentPane());
        sinhVienView.addTab("Phúc khảo", sinhVienPhucKhaoController.getContentPane());
        sinhVienView.addTab("Thêm", doiMatKhauController.getContentPane());
        sinhVienView.addLogout();
        sinhVienView.addLogoutListener(new SinhVienController.LogoutListener());
    }

    public void showSinhVienView() {
        sinhVienView.setVisible(true);
    }

    class LogoutListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            sinhVienView.setVisible(false);
            LoginController loginController = new LoginController();
            loginController.showLoginView();
        }
    }
}
