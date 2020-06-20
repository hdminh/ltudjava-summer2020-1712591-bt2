package hibernate.controller;

import hibernate.dao.DanhsachlopDao;
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
    private StudentDao studentDao;
    private MonhocDao monhocDao;
    private DanhsachlopDao svhocmonDao;
    private DanhSachLopView studentView;
    private List<SinhvienEntity> listStudents;
    private List<MonhocEntity> listMonhoc;
    private List<DanhsachlopEntity> svhocmon;

    public DanhSachLopController(){
        listStudents = studentDao.readListStudents();
    }

    public DanhSachLopController(DanhSachLopView view) {
        studentDao = new StudentDao();
        monhocDao = new MonhocDao();
        svhocmonDao = new DanhsachlopDao();
        this.studentView = view;
        if (svhocmonDao.readListStudents().size() == 0) {
            readListSinhvienTheoMon();
        }
        svhocmon = svhocmonDao.readListStudents();
        view.addAddStudentListener(new DanhSachLopController.AddStudentListener());
        view.addDeleteStudentListener(new DanhSachLopController.DeleteStudentListener());
        view.addClearListener(new DanhSachLopController.ClearStudentListener());
        view.addListStudentSelectionListener(new DanhSachLopController.ListStudentSelectionListener());
        view.addSortMonListener(new DanhSachLopController.SortMonListener());
        view.addSortLopListener(new DanhSachLopController.SortLopListener());
        if (svhocmonDao.readListStudents() != null) {
            studentView.showListStudents(svhocmon);
        }
    }

    public Container showContentPane(){
        return studentView.getContentPane();
    }

    private void readListSinhvienTheoMon(){
        if (svhocmonDao.readListStudents().size() == 0) {
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
            svhocmonDao.addList(list);
        }
        svhocmon = svhocmonDao.readListStudents();
    }

    public void showStudentView() {
        studentView.setVisible(true);
        readListSinhvienTheoMon();
        studentView.showListStudents(svhocmon);
    }

    public void refreshTable(){
        readListSinhvienTheoMon();
        studentView.showListStudents(svhocmon);
    }

    public void onClick() {
        studentView.refreshComboBox();
        readListSinhvienTheoMon();
        studentView.showListStudents(svhocmon);
    }

    class AddStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            DanhsachlopEntity student = studentView.getStudentInfo();
            if (student != null) {
                svhocmonDao.add(student);
                refreshTable();
                studentView.showMessage("Đã thêm vào lớp học!");
            }
        }
    }

    class DeleteStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            DanhsachlopEntity student = studentView.getStudentInfoFromSelectedRow();
            if (student != null) {
                svhocmonDao.delete(student);
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
            studentView.sortMon(studentView.getSortMonIndex(), studentDao);
        }
    }

    class SortLopListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            studentView.sortLop(studentView.getSortLopIndex(), studentDao);
        }
    }

    class ListStudentSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            studentView.fillStudentFromSelectedRow();
        }
    }
}
