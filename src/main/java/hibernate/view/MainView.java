package hibernate.view;

import hibernate.controller.DanhSachLopController;
import hibernate.controller.QuanLySinhVienController;
import hibernate.controller.ThoiKhoaBieuController;
import hibernate.dao.UserDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MainView extends JFrame implements ActionListener {
    QuanLySinhVienView qlsvView;
    ThoiKhoaBieuView tkbView;
    DanhSachLopView dslopView;
    BangDiemView bdView;
    LichPhucKhaoView lichView;
    QuanLySinhVienController qlsvController;
    ThoiKhoaBieuController tkbController;
    DanhSachLopController dslopController;
    UserDao userDao;
    JTabbedPane pane;

    public MainView(){
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pane = new JTabbedPane();
        qlsvView = new QuanLySinhVienView();
        tkbView = new ThoiKhoaBieuView();
        dslopView = new DanhSachLopView();
        bdView = new BangDiemView();
        lichView = new LichPhucKhaoView();
        qlsvController = new QuanLySinhVienController(qlsvView);
        tkbController = new ThoiKhoaBieuController(tkbView);
        dslopController = new DanhSachLopController(dslopView);

        pane.addTab("Thông tin sinh viên", qlsvController.showContentPane());
        pane.addTab("Thời khóa biểu",tkbController.showContentPane());
        pane.addTab("Danh sách lớp", dslopController.showContentPane());
        pane.addTab("Bảng điểm", bdView.getContentPane());
        pane.addTab("Lịch thi", lichView.getContentPane());
        this.add(pane);
        this.pack();
        this.setTitle("Portal HCMUS");
        this.setSize(1000, 700);
        this.setResizable(false);
    }

    public void actionPerformed(ActionEvent e) {

    }
}
