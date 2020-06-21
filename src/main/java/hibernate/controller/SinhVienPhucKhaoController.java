package hibernate.controller;

import hibernate.dao.BangdiemDao;
import hibernate.dao.DonphuckhaoDao;
import hibernate.dao.DotphuckhaoDao;
import hibernate.entity.BangdiemEntity;
import hibernate.entity.DonphuckhaoEntity;
import hibernate.entity.DotphuckhaoEntity;
import hibernate.entity.UserEntity;
import hibernate.view.SinhVienPhucKhaoView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SinhVienPhucKhaoController {
    DonphuckhaoDao donphuckhaoDao;
    BangdiemDao bangdiemDao;
    DotphuckhaoDao dotphuckhaoDao;
    SinhVienPhucKhaoView phucKhaoView;
    List<DonphuckhaoEntity> listDon;
    UserEntity sinhvien;
    public SinhVienPhucKhaoController(UserEntity user) {
        sinhvien = user;
        donphuckhaoDao = new DonphuckhaoDao();
        phucKhaoView = new SinhVienPhucKhaoView();
        bangdiemDao = new BangdiemDao();

        List<BangdiemEntity> bangdiemList = bangdiemDao.readListBySinhvien(sinhvien.getUsername());
        List<String> monhocList = new ArrayList<String>();
        for (BangdiemEntity bd : bangdiemList){
            monhocList.add(bd.getMonhoc());
        }
        String [] monhoc = monhocList.toArray(new String[0]);
        phucKhaoView.setMonhoc(monhoc);
        dotphuckhaoDao = new DotphuckhaoDao();
        List<DotphuckhaoEntity> dotPKEntity = dotphuckhaoDao.readListDotphuckhaoConHan();
        List<String> dotPKList = new ArrayList<String>();
        for (DotphuckhaoEntity dot: dotPKEntity){
            dotPKList.add(dot.getNgaybatdau() + "-" + dot.getNgayketthuc());
        }
        String [] dotPK = dotPKList.toArray(new String[0]);
        phucKhaoView.setDotPK(dotPK);
        phucKhaoView.initComponents();
        phucKhaoView.setMssvField(user.getUsername());

        listDon = donphuckhaoDao.readListDonBySinhvien(sinhvien.getUsername());

        phucKhaoView.addAddListener(new SinhVienPhucKhaoController.AddListener());
        phucKhaoView.addClearListener(new SinhVienPhucKhaoController.ClearListener());
        phucKhaoView.addListSelectionListener(new SinhVienPhucKhaoController.SelectionListener());
        phucKhaoView.showPhuckhao(listDon);
    }

    public Container getContentPane() {
        return phucKhaoView.getContentPane();
    }

    public void showlichView() {
        phucKhaoView.setVisible(true);
        phucKhaoView.showPhuckhao(listDon);
    }

    public void refreshTable() {
        listDon = donphuckhaoDao.readListDonBySinhvien(sinhvien.getUsername());
        phucKhaoView.showPhuckhao(listDon);
    }

    class AddListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            DonphuckhaoEntity don = phucKhaoView.getInfo();
            if (don != null) {
                donphuckhaoDao.add(don);
                refreshTable();
                phucKhaoView.showMessage("Đã thêm đơn phúc khảo!");
            }
        }
    }

    class ClearListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            phucKhaoView.clearInfo();
        }
    }

    class SelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            phucKhaoView.fillFromSelectedRow();
        }
    }
}
