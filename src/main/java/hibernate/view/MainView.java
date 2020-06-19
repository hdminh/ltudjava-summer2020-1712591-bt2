package hibernate.view;

import hibernate.controller.BangdiemController;
import hibernate.controller.DanhSachLopController;
import hibernate.controller.QuanLySinhVienController;
import hibernate.controller.ThoiKhoaBieuController;
import hibernate.dao.UserDao;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
    BangdiemController bdController;
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
        bdController = new BangdiemController(bdView);

        pane.addTab("Thông tin sinh viên", qlsvController.showContentPane());
        pane.addTab("Thời khóa biểu",tkbController.showContentPane());
        pane.addTab("Danh sách lớp", dslopController.showContentPane());
        pane.addTab("Bảng điểm", bdController.showContentPane());
        pane.addTab("Lịch phúc khảo", lichView.getContentPane());
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

                }
            }
        };
        pane.addChangeListener(changeListener);
        this.add(pane, BorderLayout.CENTER);
        this.pack();
        this.setTitle("Portal HCMUS");
        this.setSize(1000, 700);
        this.setResizable(false);
    }

    public void actionPerformed(ActionEvent e) {

    }
}
