package hibernate.controller;

import hibernate.dao.BangdiemDao;
import hibernate.dao.MonhocDao;
import hibernate.dao.StudentDao;
import hibernate.entity.BangdiemEntity;
import hibernate.entity.MonhocEntity;
import hibernate.entity.SinhvienEntity;
import hibernate.utils.CsvUtil;
import hibernate.view.BangDiemView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BangdiemController {
    private StudentDao studentDao;
    private MonhocDao monhocDao;
    private BangdiemDao bangdiemDao;
    private BangDiemView studentView;
    private List<SinhvienEntity> listStudents;
    private List<BangdiemEntity> listBangdiem;

    public BangdiemController(){
        studentDao = new StudentDao();
        monhocDao = new MonhocDao();
        bangdiemDao = new BangdiemDao();
        listStudents = studentDao.readListStudents();
        studentView = new BangDiemView();
        List<BangdiemEntity> bangdiem = bangdiemDao.readListStudents();
        List<String> monhocList = new ArrayList<String>();
        List<String> mssvList = new ArrayList<String>();

        for (BangdiemEntity bd : bangdiem){
            String monhoc = bd.getLophoc().getMalop() + "-" + bd.getMonhoc();
            String sinhvien = bd.getSinhvien() + "-" + bd.getHoten();
            if (!monhocList.contains(monhoc)) {
                monhocList.add(monhoc);
            }
            if (!mssvList.contains(sinhvien)) {
                mssvList.add(sinhvien);
            }
        }
        studentView.setLop(monhocList.toArray(new String[0]));
        monhocList.add(0, "Tất cả");
        studentView.setChonlop(monhocList.toArray(new String[0]));
        studentView.setMssv(mssvList.toArray(new String[0]));
        studentView.initComponents();

        studentView.addAddStudentListener(new BangdiemController.AddStudentListener());
        studentView.addDeleteStudentListener(new BangdiemController.DeleteStudentListener());
        studentView.addEditStudentListener(new BangdiemController.EditStudentListener());
        studentView.addClearListener(new BangdiemController.ClearStudentListener());
        studentView.addListStudentSelectionListener(new BangdiemController.ListStudentSelectionListener());
        studentView.addSortListener(new BangdiemController.SortStudentListener());
        studentView.addImportListener(new BangdiemController.ImportListener());
    }

    public Container getContentPane(){
        return studentView.getContentPane();
    }

    public void showStudentView() {
        studentView.setVisible(true);
        studentView.showListStudents(listBangdiem);
    }

    public void refreshTable(){
        listBangdiem = bangdiemDao.readListStudents();
        studentView.showListStudents(listBangdiem);
    }

    public void onClick() {
        studentView.refreshComboBox();
    }

    class AddStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            BangdiemEntity student = studentView.getStudentInfo();
            if (student != null) {
                bangdiemDao.add(student);
                refreshTable();
                studentView.showMessage("Đã thêm vào lớp học!");
            }
        }
    }
    class DeleteStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            BangdiemEntity bangdiem = studentView.getStudentInfoFromSelectedRow();
            if (bangdiem != null) {
                bangdiemDao.delete(bangdiem);
                studentView.clearStudentInfo();
                refreshTable();
                studentView.showMessage("Xóa thành công!");
            }
        }
    }

    class EditStudentListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            BangdiemEntity bangdiem = studentView.setUpdateBangdiem();
            if (bangdiem != null){
                bangdiemDao.update(bangdiem);
                studentView.clearStudentInfo();
                refreshTable();
                studentView.showMessage("Sửa thành công!");
            }
        }
    }

    class ClearStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            studentView.clearStudentInfo();
        }
    }

    class SortStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            studentView.sort(studentView.getSortIndex(), bangdiemDao);
        }
    }

    class ImportListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            List<BangdiemEntity> bangdiem = CsvUtil.ImportBangdiem();
            if (bangdiem == null){
                return;
            }
            bangdiemDao.addList(bangdiem);
            listBangdiem = bangdiemDao.readListStudents();
            studentView.showListStudents(listBangdiem);
            refreshComboBox();
        }
    }

    public void refreshComboBox(){
        List<BangdiemEntity> bangdiem = bangdiemDao.readListStudents();
        List<String> monhocList = new ArrayList<String>();
        List<String> mssvList = new ArrayList<String>();

        for (BangdiemEntity bd : bangdiem){
            String monhoc = bd.getLophoc().getMalop() + "-" + bd.getMonhoc();
            String sinhvien = bd.getSinhvien() + "-" + bd.getHoten();
            if (!monhocList.contains(monhoc)) {
                monhocList.add(monhoc);
            }
            if (!mssvList.contains(sinhvien)) {
                mssvList.add(sinhvien);
            }
        }
        studentView.setLop(monhocList.toArray(new String[0]));
        monhocList.add(0, "Tất cả");
        studentView.setChonlop(monhocList.toArray(new String[0]));
        studentView.setMssv(mssvList.toArray(new String[0]));
        studentView.refreshComboBox();
    }

    class ListStudentSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            studentView.fillStudentFromSelectedRow();
        }
    }
}
