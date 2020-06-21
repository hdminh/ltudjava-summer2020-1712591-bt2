package hibernate.controller;

import hibernate.entity.UserEntity;
import hibernate.view.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainController {
    MainView mainView;

    QuanLySinhVienController qlsvController;
    ThoiKhoaBieuController tkbController;
    DanhSachLopController dslopController;
    BangdiemController bdController;
    LichPhucKhaoController lichPhucKhaoController;
    DonPhucKhaoController donPhucKhaoController;
    DoiMatKhauController doiMatKhauController;
    UserEntity isLogin;

    public MainController(UserEntity user){
        this.mainView = new MainView();
        isLogin = user;
        qlsvController = new QuanLySinhVienController();
        tkbController = new ThoiKhoaBieuController();
        dslopController = new DanhSachLopController();
        bdController = new BangdiemController();
        lichPhucKhaoController = new LichPhucKhaoController();
        donPhucKhaoController = new DonPhucKhaoController();
        doiMatKhauController = new DoiMatKhauController(isLogin);

        mainView.addTab("Thông tin sinh viên", qlsvController.getContentPane());
        mainView.addTab("Thời khóa biểu",tkbController.getContentPane());
        mainView.addTab("Danh sách lớp", dslopController.getContentPane());
        mainView.addTab("Bảng điểm", bdController.getContentPane());
        mainView.addTab("Lịch phúc khảo", lichPhucKhaoController.getContentPane());
        mainView.addTab("Xem phúc khảo", donPhucKhaoController.getContentPane());
        mainView.addTab("Thêm", doiMatKhauController.getContentPane());

        mainView.addChangeListener(changeListener);
    }

    public void showMainView(){
        mainView.setVisible(true);
    }

    ChangeListener changeListener = new ChangeListener() {
        public void stateChanged(ChangeEvent changeEvent) {
            JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
            int index = sourceTabbedPane.getSelectedIndex();
            switch (index){
                case 0:
                    qlsvController.onClick();
                    break;
                case 1:
                    tkbController.onClick();
                    break;
                case 2:
                    dslopController.onClick();
                    break;
                case 3:
                    bdController.onClick();
                    break;
                case 4:
                    lichPhucKhaoController.onClick();
                    break;
            }
        }
    };
}
