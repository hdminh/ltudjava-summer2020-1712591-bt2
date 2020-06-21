package hibernate.controller;

import hibernate.entity.UserEntity;
import hibernate.view.SinhVienView;

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

    }

    public void showSinhVienView() {
        sinhVienView.setVisible(true);
    }
}
