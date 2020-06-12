package hibernate.controller;

import hibernate.dao.StudentDao;
import hibernate.entity.SinhvienEntity;
import hibernate.utils.CsvUtil;
import hibernate.view.QuanLySinhVienView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class QuanLySinhVienController {
    private StudentDao studentDao;
    private QuanLySinhVienView studentView;
    private List<SinhvienEntity> listStudents;

    public QuanLySinhVienController(){
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
            studentDao.writeToDB(sinhvien);
            listStudents = studentDao.readListStudents();
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
