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
import java.util.List;

public class BangdiemController {
    private StudentDao studentDao;
    private MonhocDao monhocDao;
    private BangdiemDao bangdiemDao;
    private BangDiemView studentView;
    private List<SinhvienEntity> listStudents;
    private List<MonhocEntity> listMonhoc;
    private List<BangdiemEntity> listBangdiem;

    public BangdiemController(){
        listStudents = studentDao.readListStudents();
    }

    public BangdiemController(BangDiemView view) {
        studentDao = new StudentDao();
        monhocDao = new MonhocDao();
        bangdiemDao = new BangdiemDao();
        this.studentView = view;
        listBangdiem = bangdiemDao.readListStudents();
        view.addAddStudentListener(new BangdiemController.AddStudentListener());
        view.addDeleteStudentListener(new BangdiemController.DeleteStudentListener());
        view.addEditStudentListener(new BangdiemController.EditStudentListener());
        view.addClearListener(new BangdiemController.ClearStudentListener());
        view.addListStudentSelectionListener(new BangdiemController.ListStudentSelectionListener());
        view.addSortListener(new BangdiemController.SortStudentListener());
        view.addImportListener(new BangdiemController.ImportListener());

    }

    public Container showContentPane(){
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
            studentView.sort(studentView.getSortIndex(), studentDao);
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
            studentView.refreshComboBox();
        }
    }

    class ListStudentSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            studentView.fillStudentFromSelectedRow();
        }
    }
}
