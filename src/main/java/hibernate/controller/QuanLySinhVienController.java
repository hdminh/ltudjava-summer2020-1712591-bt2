package hibernate.controller;

import hibernate.dao.StudentDao;
import hibernate.entity.SinhvienEntity;
import hibernate.view.QuanLySinhVienView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class QuanLySinhVienController {
        private StudentDao studentDao;
        private QuanLySinhVienView studentView;

    public QuanLySinhVienController(QuanLySinhVienView view) {
            this.studentView = view;
            studentDao = new StudentDao();

            view.addAddStudentListener(new AddStudentListener());
            view.addEdiStudentListener(new EditStudentListener());
            view.addDeleteStudentListener(new DeleteStudentListener());
            view.addClearListener(new ClearStudentListener());
            }

        public void showStudentView() {
            List<SinhvienEntity> studentList = studentDao.getListStudents();
            studentView.setVisible(true);
            studentView.showListStudents(studentList);
        }

        /**
         * Lớp AddStudentListener
         * chứa cài đặt cho sự kiện click button "Add"
         *
         * @author viettuts.vn
         */
        class AddStudentListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                SinhvienEntity student = studentView.getStudentInfo();
                if (student != null) {
                    studentDao.add(student);
                    studentView.showStudent(student);
                    studentView.showListStudents(studentDao.getListStudents());
                    studentView.showMessage("Thêm thành công!");
                }
            }
        }

        /**
         * Lớp EditStudentListener
         * chứa cài đặt cho sự kiện click button "Edit"
         *
         * @author viettuts.vn
         */
        class EditStudentListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                SinhvienEntity student = studentView.getStudentInfo();
                if (student != null) {
                    studentDao.edit(student);
                    studentView.showStudent(student);
                    studentView.showListStudents(studentDao.getListStudents());
                    studentView.showMessage("Cập nhật thành công!");
                }
            }
        }

        /**
         * Lớp DeleteStudentListener
         * chứa cài đặt cho sự kiện click button "Delete"
         *
         * @author viettuts.vn
         */
        class DeleteStudentListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                SinhvienEntity student = studentView.getStudentInfo();
                if (student != null) {
                    studentDao.delete(student);
                    studentView.clearStudentInfo();
                    studentView.showListStudents(studentDao.getListStudents());
                    studentView.showMessage("Xóa thành công!");
                }
            }
        }

        /**
         * Lớp ClearStudentListener
         * chứa cài đặt cho sự kiện click button "Clear"
         *
         * @author viettuts.vn
         */
        class ClearStudentListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                studentView.clearStudentInfo();
            }
        }

        /**
         * Lớp SortStudentGPAListener
         * chứa cài đặt cho sự kiện click button "Sort By GPA"
         *
         * @author viettuts.vn
         */
}
