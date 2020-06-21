package hibernate.controller;

import hibernate.dao.BangdiemDao;
import hibernate.dao.DonphuckhaoDao;
import hibernate.dao.DotphuckhaoDao;
import hibernate.entity.DonphuckhaoEntity;
import hibernate.entity.DotphuckhaoEntity;
import hibernate.view.DonPhucKhaoView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DonPhucKhaoController {
    DonphuckhaoDao donphuckhaoDao;
    DotphuckhaoDao dotphuckhaoDao;
    BangdiemDao bangdiemDao;
    DonPhucKhaoView phuckhaoView;
    List<DonphuckhaoEntity> listDon;
    public DonPhucKhaoController() {
        donphuckhaoDao = new DonphuckhaoDao();
        dotphuckhaoDao = new DotphuckhaoDao();
        bangdiemDao = new BangdiemDao();
        phuckhaoView = new DonPhucKhaoView();
        listDon = donphuckhaoDao.readListDonphuckhao();
        bangdiemDao = new BangdiemDao();
        List<String> monhocList = bangdiemDao.readListMonhoc();

        dotphuckhaoDao = new DotphuckhaoDao();
        List<DotphuckhaoEntity> dotPKEntity = dotphuckhaoDao.readListDotphuckhaoConHan();
        List<String> dotPKList = new ArrayList<String>();
        for (DotphuckhaoEntity dot: dotPKEntity){
            dotPKList.add(dot.getNgaybatdau() + "-" + dot.getNgayketthuc());
        }
        phuckhaoView.setMonhoc(monhocList.toArray(new String[0]));
        phuckhaoView.setDotPK(dotPKList.toArray(new String[0]));
        phuckhaoView.addEditStudentListener(new DonPhucKhaoController.EditStudentListener());
        phuckhaoView.addClearListener(new DonPhucKhaoController.ClearListener());
        phuckhaoView.addListSelectionListener(new DonPhucKhaoController.SelectionListener());
        phuckhaoView.showPhuckhao(listDon);
    }

    public Container getContentPane() {
        return phuckhaoView.getContentPane();
    }

    public void showlichView() {
        phuckhaoView.setVisible(true);
        phuckhaoView.showPhuckhao(listDon);
    }

    public void refreshTable() {
        listDon = donphuckhaoDao.readListDonphuckhao();
        phuckhaoView.showPhuckhao(listDon);
    }

    class EditStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            DonphuckhaoEntity don = phuckhaoView.getInfo();
            if (don != null) {
                donphuckhaoDao.update(don);
                refreshTable();
                phuckhaoView.showMessage("Đã cập nhật trạng thái!");
            }
        }
    }

    class ClearListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            phuckhaoView.clearInfo();
        }
    }

    class SelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            phuckhaoView.fillFromSelectedRow();
        }
    }
}
