package hibernate.controller;

import hibernate.dao.BangdiemDao;
import hibernate.entity.BangdiemEntity;
import hibernate.entity.UserEntity;
import hibernate.view.SinhVienXemDiemView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SinhVienXemDiemController {
    UserEntity isLogin;
    BangdiemDao bangdiemDao;
    SinhVienXemDiemView svView;
    public SinhVienXemDiemController(UserEntity user){
        isLogin = user;
        svView = new SinhVienXemDiemView();
        bangdiemDao = new BangdiemDao();
        List<String> monhocList = new ArrayList<String>();
        if (bangdiemDao.readListBySinhvien(isLogin.getUsername().trim()) != null) {
            List<BangdiemEntity> bangdiem = bangdiemDao.readListBySinhvien(isLogin.getUsername());

            for (BangdiemEntity bd : bangdiem) {
                String monhoc = bd.getLophoc().getMalop() + "-" + bd.getMonhoc();
                if (!monhocList.contains(monhoc)) {
                    monhocList.add(monhoc);
                }
            }
        }
        svView.setLop(monhocList.toArray(new String[0]));
        monhocList.add(0, "Tất cả");
        svView.setChonlop(monhocList.toArray(new String[0]));
        svView.initComponents();
        svView.addListStudentSelectionListener(new SinhVienXemDiemController.ListStudentSelectionListener());
        svView.addSortListener(new SinhVienXemDiemController.SortStudentListener());
        svView.showListStudents(bangdiemDao.readListBySinhvien(isLogin.getUsername()));
    }

    public Container getContentPane(){
        return svView.getContentPane();
    }

    class SortStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            svView.sort(svView.getSortIndex(), bangdiemDao, isLogin.getUsername());
        }
    }

    class ListStudentSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            svView.fillStudentFromSelectedRow();
        }
    }
}

