package hibernate.controller;

import hibernate.dao.DotphuckhaoDao;
import hibernate.entity.DotphuckhaoEntity;
import hibernate.view.LichPhucKhaoView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LichPhucKhaoController {
    DotphuckhaoDao dotphuckhaoDao;
    LichPhucKhaoView lichView;
    List<DotphuckhaoEntity> listDot;

    public LichPhucKhaoController() {
        dotphuckhaoDao = new DotphuckhaoDao();
        lichView = new LichPhucKhaoView();
        listDot = dotphuckhaoDao.readListDotphuckhao();
        lichView.addAddStudentListener(new LichPhucKhaoController.AddStudentListener());
        lichView.addDeleteStudentListener(new LichPhucKhaoController.DeleteStudentListener());
        lichView.addClearListener(new LichPhucKhaoController.ClearStudentListener());
        lichView.addListSelectionListener(new LichPhucKhaoController.ListDotSelectionListener());
        lichView.showLich(listDot);
    }

    public Container getContentPane() {
        return lichView.getContentPane();
    }

    public void showlichView() {
        lichView.setVisible(true);
        lichView.showLich(listDot);
    }

    public void refreshTable() {
        listDot = dotphuckhaoDao.readListDotphuckhao();
        lichView.showLich(listDot);
    }

    public void onClick() {
        lichView.showLich(listDot);
    }

    class AddStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            DotphuckhaoEntity dot = lichView.getInfo();
            if (dot != null) {
                dotphuckhaoDao.add(dot);
                refreshTable();
                lichView.showMessage("Đã thêm lịch!");
            }
        }
    }

    class DeleteStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            DotphuckhaoEntity dot = lichView.getInfo();
            if (dot != null) {
                dotphuckhaoDao.delete(dot);
                lichView.clearInfo();
                refreshTable();
                lichView.showMessage("Xóa thành công!");
            }
        }
    }

    class ClearStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            lichView.clearInfo();
        }
    }

    class ListDotSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            lichView.fillFromSelectedRow();
        }
    }
}
