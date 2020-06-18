package hibernate.controller;

import hibernate.dao.MonhocDao;
import hibernate.dao.StudentDao;
import hibernate.entity.MonhocEntity;
import hibernate.entity.SinhvienEntity;
import hibernate.utils.CsvUtil;
import hibernate.view.QuanLySinhVienView;
import hibernate.view.ThoiKhoaBieuView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ThoiKhoaBieuController {
    private MonhocDao monhocDao;
    private ThoiKhoaBieuView tkbView;
    private List<MonhocEntity> listMonhoc;

    public ThoiKhoaBieuController(){
        monhocDao = new MonhocDao();
        listMonhoc = monhocDao.readListMonhoc();
    }

    public Container showContentPane(){
        return tkbView.getContentPane();
    }

    public ThoiKhoaBieuController(ThoiKhoaBieuView view) {
        this.tkbView = view;
        monhocDao = new MonhocDao();
        listMonhoc = monhocDao.readListMonhoc();
        view.addAddMonhocListener(new AddStudentListener());
        view.addEditMonhocListener(new EditStudentListener());
        view.addDeleteMonhocListener(new DeleteStudentListener());
        view.addClearListener(new ClearStudentListener());
        view.addImportListener(new ImportStudentListener());
        view.addSortListener(new SortStudentListener());
        view.addListMonhocSelectionListener(new ListStudentSelectionListener());
        tkbView.showListMonHoc(listMonhoc);
    }

    public void showTkbView() {
        tkbView.setVisible(true);
        tkbView.showListMonHoc(listMonhoc);
    }

    public void refreshTable(){
        listMonhoc = monhocDao.readListMonhoc();
        tkbView.showListMonHoc(listMonhoc);
    }

    public MonhocEntity findMonById(String id){
        for (MonhocEntity mh: listMonhoc){
            if (mh.getMamon().equals("id")){
                return mh;
            }
        }
        return null;
    }

    class AddStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            MonhocEntity monhoc = tkbView.getMonhocInfo();
            if (monhoc != null) {
                monhocDao.add(monhoc);
                tkbView.showMonhoc(monhoc);
                refreshTable();
                tkbView.showMessage("Thêm thành công!");
            }
        }
    }

    class EditStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            MonhocEntity monhoc = tkbView.getMonhocInfo();
            if (monhoc != null) {
                monhocDao.edit(monhoc);
                tkbView.showMonhoc(monhoc);
                refreshTable();
                tkbView.showMessage("Cập nhật thành công!");
            }
        }
    }

    class DeleteStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            MonhocEntity monhoc = tkbView.getMonhocInfo();
            if (monhoc != null) {
                monhocDao.delete(monhoc);
                tkbView.clearMonhocInfo();
                refreshTable();
                tkbView.showMessage("Xóa thành công!");
            }
        }
    }

    class ClearStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            tkbView.clearMonhocInfo();
        }
    }

    class ImportStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            List<MonhocEntity> monhoc = CsvUtil.ImportMonhoc();
            if (monhoc == null)
                return;
            monhocDao.writeToDB(monhoc);
            listMonhoc = monhocDao.readListMonhoc();
            tkbView.refreshComboBox();
            tkbView.showListMonHoc(listMonhoc);
        }
    }

    class SortStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            tkbView.sort(tkbView.getSortIndex());
        }
    }

    class ListStudentSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            tkbView.fillTKBFromSelectedRow();
        }
    }
}
