package hibernate.controller;

import hibernate.dao.LophocDao;
import hibernate.dao.StudentDao;
import hibernate.dao.UserDao;
import hibernate.entity.SinhvienEntity;
import hibernate.entity.UserEntity;
import hibernate.utils.CsvUtil;
import hibernate.view.QuanLySinhVienView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class QuanLySinhVienController {
    private StudentDao studentDao;
    LophocDao lophocDao;
    UserDao userDao;
    private QuanLySinhVienView studentView;
    private List<SinhvienEntity> listStudents;

    public QuanLySinhVienController(){
        studentDao = new StudentDao();
        lophocDao = new LophocDao();
        listStudents = studentDao.readListStudents();
        studentView = new QuanLySinhVienView();

        List<String> lophocList = lophocDao.readMaLop();
        String [] lop = lophocList.toArray(new String[0]);
        studentView.setLop(lop);
        lophocList.add(0, "Tất cả");
        String [] chonlop = lophocList.toArray(new String[0]);
        studentView.setChonlop(chonlop);
        studentView.initComponents();
        studentView.addAddStudentListener(new AddStudentListener());
        studentView.addEdiStudentListener(new EditStudentListener());
        studentView.addDeleteStudentListener(new DeleteStudentListener());
        studentView.addClearListener(new ClearStudentListener());
        studentView.addImportListener(new ImportStudentListener());
        studentView.addListStudentSelectionListener(new ListStudentSelectionListener());
        studentView.addSortListener(new SortStudentListener());
        studentView.showListStudents(listStudents);
    }

    public SinhvienEntity findSinhVienByMssv(String mssv){
        return studentDao.getStudentByMssv(mssv);
    }

        public Container getContentPane(){
            return studentView.getContentPane();
        }

        public void showStudentView() {
            studentView.setVisible(true);
            studentView.showListStudents(listStudents);
        }

        public void refreshTable(){
            listStudents = studentDao.readListStudents();
            studentView.showListStudents(listStudents);
        }

    public void onClick() {
        refreshComboBox();
    }

    public void refreshComboBox(){
        List<String> lophoc = lophocDao.readMaLop();
        studentView.setLop(lophoc.toArray(new String[0]));
        lophoc.add(0, "Tất cả");
        studentView.setChonlop(lophoc.toArray(new String[0]));
        studentView.refreshComboBox();
    }

    class AddStudentListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                SinhvienEntity student = studentView.getStudentInfo();
                if (student != null) {
                    studentDao.add(student);
                    studentView.showStudent(student);
                    refreshTable();
                    studentView.showMessage("Thêm thành công!");
                    UserEntity user = new UserEntity(student.getMssv().trim(), student.getMssv().trim());
                    userDao.add(user);
                }
            }
        }

        class EditStudentListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                SinhvienEntity student = studentView.getStudentInfo();
                if (student != null) {
                    studentDao.edit(student);
                    studentView.showStudent(student);
                    refreshTable();
                    UserEntity user = new UserEntity(student.getMssv().trim(), student.getMssv().trim());
                    userDao.edit(user);
                    studentView.showMessage("Cập nhật thành công!");
                }
            }
        }

        class DeleteStudentListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                SinhvienEntity student = studentView.getStudentInfo();
                if (student != null) {
                    studentDao.delete(student);
                    studentView.clearStudentInfo();
                    refreshTable();
                    UserEntity user = new UserEntity(student.getMssv().trim(), student.getMssv().trim());
                    userDao.delete(user);
                    studentView.showMessage("Xóa thành công!");
                }
            }
        }

        class ClearStudentListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                studentView.clearStudentInfo();
            }
        }

    class ImportStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            List<SinhvienEntity> sinhvien = CsvUtil.ImportCsv();
            if (sinhvien == null){
                return;
            }
            studentDao.writeToDB(sinhvien);
            listStudents = studentDao.readListStudents();
            refreshComboBox();
            studentView.showListStudents(listStudents);
        }
    }

    class SortStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            studentView.sort(studentView.getSortIndex(), studentDao);
        }
    }

        class ListStudentSelectionListener implements ListSelectionListener {
            public void valueChanged(ListSelectionEvent e) {
                studentView.fillStudentFromSelectedRow();
            }
        }
}
