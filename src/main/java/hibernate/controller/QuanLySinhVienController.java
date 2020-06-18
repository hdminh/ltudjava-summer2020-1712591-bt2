package hibernate.controller;

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
    private UserDao userDao;
    private QuanLySinhVienView studentView;
    private List<SinhvienEntity> listStudents;

    public QuanLySinhVienController(){
        studentDao = new StudentDao();
        listStudents = studentDao.readListStudents();
    }

    public QuanLySinhVienController(QuanLySinhVienView view) {
        this.studentView = view;
        studentDao = new StudentDao();
        listStudents = studentDao.readListStudents();
        view.addAddStudentListener(new AddStudentListener());
        view.addEdiStudentListener(new EditStudentListener());
        view.addDeleteStudentListener(new DeleteStudentListener());
        view.addClearListener(new ClearStudentListener());
        view.addImportListener(new ImportStudentListener());
        view.addListStudentSelectionListener(new ListStudentSelectionListener());
        view.addSortListener(new SortStudentListener());
        studentView.showListStudents(listStudents);

    }

        public Container showContentPane(){
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
            studentView.refreshComboBox();
            studentView.showListStudents(listStudents);
        }
    }

    class SortStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            studentView.sort(studentView.getSortIndex());
        }
    }

        class ListStudentSelectionListener implements ListSelectionListener {
            public void valueChanged(ListSelectionEvent e) {
                studentView.fillStudentFromSelectedRow();
            }
        }
}
