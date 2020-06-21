package hibernate.controller;

import hibernate.dao.DanhsachlopDao;
import hibernate.dao.LophocDao;
import hibernate.dao.MonhocDao;
import hibernate.dao.StudentDao;
import hibernate.entity.DanhsachlopEntity;
import hibernate.entity.MonhocEntity;
import hibernate.entity.SinhvienEntity;
import hibernate.view.DanhSachLopView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DanhSachLopController {
    StudentDao studentDao;
    MonhocDao monhocDao;
    LophocDao lophocDao;
    DanhsachlopDao danhsachlopDao;
    DanhSachLopView studentView;
    private List<SinhvienEntity> listStudents;
    private List<MonhocEntity> listMonhoc;
    private List<DanhsachlopEntity> danhsachlop;

    public DanhSachLopController(){
        studentDao = new StudentDao();
        monhocDao = new MonhocDao();
        danhsachlopDao = new DanhsachlopDao();
        studentView = new DanhSachLopView();
        lophocDao = new LophocDao();
        if (danhsachlopDao.readListStudents().size() == 0) {
            readListSinhvienTheoMon();
        }
        danhsachlop = danhsachlopDao.readListStudents();

        List<MonhocEntity> monhoc = monhocDao.readListMonhoc();
        List<SinhvienEntity> sinhvien = studentDao.readListStudents();
        List<String> monhocList = new ArrayList<String>();
        List<String> lophocList = lophocDao.readMaLop();
        List<String> mssvList = new ArrayList<String>();
        for (MonhocEntity mh : monhoc){
            String mamon = mh.getMamon();
            String malop = mh.getLophoc().getMalop();
            monhocList.add(malop + "-" + mamon);
        }

        for (SinhvienEntity sv: sinhvien){
            String mssv = sv.getMssv();
            String ten = sv.getHoten();
            mssvList.add(mssv + "-" + ten);
        }
        studentView.setLop(monhocList.toArray(new String[0]));
        monhocList.add(0, "Tất cả");
        lophocList.add(0, "Tất cả");
        studentView.setChonmon(monhocList.toArray(new String[0]));
        studentView.setChonlop(lophocList.toArray(new String[0]));
        studentView.setMssv(mssvList.toArray(new String[0]));
        studentView.initComponents();

        studentView.addAddStudentListener(new DanhSachLopController.AddStudentListener());
        studentView.addDeleteStudentListener(new DanhSachLopController.DeleteStudentListener());
        studentView.addClearListener(new DanhSachLopController.ClearStudentListener());
        studentView.addListStudentSelectionListener(new DanhSachLopController.ListStudentSelectionListener());
        studentView.addSortMonListener(new DanhSachLopController.SortMonListener());
        studentView.addSortLopListener(new DanhSachLopController.SortLopListener());
        if (danhsachlopDao.readListStudents() != null) {
            studentView.showListStudents(danhsachlop);
        }
    }

    public Container getContentPane(){
        return studentView.getContentPane();
    }

    private void readListSinhvienTheoMon(){
        if (danhsachlopDao.readListStudents().size() == 0) {
            listStudents = studentDao.readListStudents();
            listMonhoc = monhocDao.readListMonhoc();
            List<DanhsachlopEntity> list = new ArrayList<DanhsachlopEntity>();
            ;
            for (SinhvienEntity sv : listStudents) {
                for (MonhocEntity mh : listMonhoc) {
                    if (sv.getLop().getMalop().equals(mh.getLophoc().getMalop())) {
                        DanhsachlopEntity temp = new DanhsachlopEntity();
                        temp.setSinhvien(sv.getMssv());
                        temp.setHoten(sv.getHoten());
                        temp.setCmnd(sv.getCmnd());
                        temp.setGioitinh(sv.getGioitinh());
                        temp.setLop(sv.getLop());
                        temp.setMonhoc(mh.getMamon());
                        list.add(temp);
                    }
                }
            }
            danhsachlopDao.addList(list);
        }
        danhsachlop = danhsachlopDao.readListStudents();
    }

    public void showStudentView() {
        studentView.setVisible(true);
        readListSinhvienTheoMon();
        studentView.showListStudents(danhsachlop);
    }

    public void refreshTable(){
        readListSinhvienTheoMon();
        studentView.showListStudents(danhsachlop);
    }

    public void onClick() {
        refreshComboBox();
        readListSinhvienTheoMon();
        studentView.showListStudents(danhsachlop);
    }

    public void refreshComboBox(){
        List<MonhocEntity> monhoc = monhocDao.readListMonhoc();
        List<SinhvienEntity> sinhvien = studentDao.readListStudents();
        List<String> monhocList = new ArrayList<String>();
        List<String> lophocList = lophocDao.readMaLop();
        List<String> mssvList = new ArrayList<String>();
        for (MonhocEntity mh : monhoc){
            String mamon = mh.getMamon();
            String malop = mh.getLophoc().getMalop();
            monhocList.add(malop + "-" + mamon);
        }

        for (SinhvienEntity sv: sinhvien){
            String mssv = sv.getMssv();
            String ten = sv.getHoten();
            mssvList.add(mssv + "-" + ten);
        }

        studentView.setLop(monhocList.toArray(new String[0]));
        monhocList.add(0, "Tất cả");
        lophocList.add(0, "Tất cả");
        studentView.setChonmon(monhocList.toArray(new String[0]));
        studentView.setChonlop(lophocList.toArray(new String[0]));
        studentView.setMssv(mssvList.toArray(new String[0]));
        studentView.refreshComboBox();
    }

    class AddStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            DanhsachlopEntity student = studentView.getStudentInfo();
            if (student != null) {
                danhsachlopDao.add(student);
                refreshTable();
                studentView.showMessage("Đã thêm vào lớp học!");
            }
        }
    }

    class DeleteStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            DanhsachlopEntity student = studentView.getStudentInfoFromSelectedRow();
            if (student != null) {
                danhsachlopDao.delete(student);
                studentView.clearStudentInfo();
                refreshTable();
                studentView.showMessage("Xóa thành công!");
            }
        }
    }

    class ClearStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            studentView.clearStudentInfo();
        }
    }

    class SortMonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            studentView.sortMon(studentView.getSortMonIndex(), danhsachlopDao);
        }
    }

    class SortLopListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            studentView.sortLop(studentView.getSortLopIndex(), danhsachlopDao);
        }
    }

    class ListStudentSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            studentView.fillStudentFromSelectedRow();
        }
    }
}
