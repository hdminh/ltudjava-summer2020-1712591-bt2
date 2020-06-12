package hibernate.view;

import hibernate.dao.LophocDao;
import hibernate.dao.StudentDao;
import hibernate.entity.LophocEntity;
import hibernate.entity.SinhvienEntity;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import hibernate.utils.HibernateUtils.*;

public class QuanLySinhVienView extends JFrame implements ActionListener, ListSelectionListener {
    LophocDao lophocDao = new LophocDao();

    String [] gioitinh = {"Nam", "Nữ"};
    String [] lop;
    String [] sortlop;

    private static final long serialVersionUID = 1L;
    private JButton addStudentBtn;
    private JButton editStudentBtn;
    private JButton deleteStudentBtn;
    private JButton clearBtn;
    private JButton importBtn;
    private JScrollPane jScrollPaneStudentTable;
    private JTable studentTable;

    private JLabel mssvLabel;
    private JLabel hotenLabel;
    private JLabel cmndLabel;
    private JLabel gioitinhLabel;
    private JLabel lopLabel;
    private JLabel chonlopLabel;

    private JTextField mssvField;
    private JTextField hotenField;
    private JTextField cmndField;
    private JComboBox gioitinhBox;
    private JComboBox lopBox;
    private JComboBox chonlopBox;


    // định nghĩa các cột của bảng student
    private String [] columnNames = new String [] {
            "STT", "MSSV", "Họ Tên", "CMND", "Giới tính", "Lớp"};
    // định nghĩa dữ liệu mặc định của bẳng student là rỗng
    private Object data = new Object [][] {};

    public QuanLySinhVienView() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // khởi tạo các phím chức năng
        addStudentBtn = new JButton("Thêm");
        editStudentBtn = new JButton("Sửa");
        deleteStudentBtn = new JButton("Xóa");
        clearBtn = new JButton("Nhập lại");
        importBtn = new JButton("Import");
        // khởi tạo bảng student
        jScrollPaneStudentTable = new JScrollPane();
        studentTable = new JTable();

        // khởi tạo các label
        mssvLabel = new JLabel("MSSV");
        hotenLabel = new JLabel("Họ Tên");
        cmndLabel = new JLabel("CMND");
        gioitinhLabel = new JLabel("Giới tính");
        lopLabel = new JLabel("Lớp");
        chonlopLabel = new JLabel("Chọn lớp");
        List<String> lophoc = lophocDao.readMaLop();

        lop = lophoc.toArray(new String[0]);
        lophoc.add(0, "Tất cả");
        sortlop = lophoc.toArray(new String[0]);
        // khởi tạo các trường nhập dữ liệu cho student
        mssvField = new JTextField(10);
        hotenField = new JTextField(15);
        cmndField = new JTextField(10);
        gioitinhBox = new JComboBox(gioitinh);
        lopBox = new JComboBox(lop);
        chonlopBox = new JComboBox(sortlop);
        // cài đặt các cột và data cho bảng student
        studentTable.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        jScrollPaneStudentTable.setViewportView(studentTable);
        jScrollPaneStudentTable.setPreferredSize(new Dimension(480, 300));

        // tạo spring layout
        SpringLayout layout = new SpringLayout();
        // tạo đối tượng panel để chứa các thành phần của màn hình quản lý Student
        JPanel panel = new JPanel();
        panel.setSize(800, 420);
        panel.setLayout(layout);
        panel.add(jScrollPaneStudentTable);

        panel.add(addStudentBtn);
        panel.add(editStudentBtn);
        panel.add(deleteStudentBtn);
        panel.add(clearBtn);
        panel.add(importBtn);

        panel.add(mssvLabel);
        panel.add(hotenLabel);
        panel.add(cmndLabel);
        panel.add(gioitinhLabel);
        panel.add(lopLabel);
        panel.add(chonlopLabel);

        panel.add(mssvField);
        panel.add(hotenField);
        panel.add(cmndField);
        panel.add(gioitinhBox);
        panel.add(lopBox);
        panel.add(chonlopBox);

        layout.putConstraint(SpringLayout.WEST, mssvLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, mssvLabel, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, hotenLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, hotenLabel, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, cmndLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, cmndLabel, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, gioitinhLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, gioitinhLabel, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, gioitinhBox, 90, SpringLayout.WEST, gioitinhLabel);
        layout.putConstraint(SpringLayout.NORTH, gioitinhBox, 0, SpringLayout.NORTH, gioitinhLabel);
        layout.putConstraint(SpringLayout.NORTH, lopLabel, 130, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, lopLabel, 10, SpringLayout.WEST, panel);

        layout.putConstraint(SpringLayout.WEST, lopBox, 90, SpringLayout.WEST, lopLabel);
        layout.putConstraint(SpringLayout.NORTH, lopBox, 0, SpringLayout.NORTH, lopLabel);

        layout.putConstraint(SpringLayout.WEST, mssvField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, mssvField, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, hotenField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, hotenField, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, cmndField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, cmndField, 70, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, jScrollPaneStudentTable, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneStudentTable, 10, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, addStudentBtn, 15, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addStudentBtn, 240, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, editStudentBtn, 70, SpringLayout.WEST, addStudentBtn);
        layout.putConstraint(SpringLayout.NORTH, editStudentBtn, 240, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, deleteStudentBtn, 60, SpringLayout.WEST, editStudentBtn);

        layout.putConstraint(SpringLayout.NORTH, clearBtn, 240, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, clearBtn, 60, SpringLayout.WEST, deleteStudentBtn);

        layout.putConstraint(SpringLayout.NORTH, deleteStudentBtn, 240, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, importBtn, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, importBtn, 330, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, chonlopLabel, 80, SpringLayout.WEST, importBtn);
        layout.putConstraint(SpringLayout.NORTH, chonlopLabel, 0, SpringLayout.NORTH, importBtn);

        layout.putConstraint(SpringLayout.WEST, chonlopBox, 60, SpringLayout.WEST, chonlopLabel);
        layout.putConstraint(SpringLayout.NORTH, chonlopBox, 0, SpringLayout.NORTH, chonlopLabel);

        this.add(panel);
        this.pack();
        this.setTitle("Student Information");
        this.setSize(800, 420);
        // disable Edit and Delete buttons
        editStudentBtn.setEnabled(false);
        deleteStudentBtn.setEnabled(false);
        // enable Add button
        addStudentBtn.setEnabled(true);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void showListStudents(List<SinhvienEntity> list) {
        int size = list.size();

        Object [][] students = new Object[size][6];
        for (int i = 0; i < size; i++) {
            students[i][0] = (i + 1);
            students[i][1] = list.get(i).getMssv();
            students[i][2] = list.get(i).getHoten();
            students[i][3] = list.get(i).getCmnd();
            students[i][4] = list.get(i).getGioitinh();
            students[i][5] = list.get(i).getLop().getMalop();
        }
        studentTable.setModel(new DefaultTableModel(students, columnNames));
    }

    public void showStudent(SinhvienEntity student) {
        mssvField.setText("" + student.getMssv());
        hotenField.setText(student.getHoten());
        cmndField.setText("" + student.getCmnd());
        if (student.getGioitinh().trim().equals("Nam"))
            gioitinhBox.setSelectedIndex(0);
        else
            gioitinhBox.setSelectedIndex(1);
        int index = 0;
        String lopStr = student.getLop().getMalop().trim();
        do {
            if (lop[index].equals(lopStr)){
                lopBox.setSelectedIndex(index);
                break;
            }else
                index++;
        }
        while (true);
        // enable Edit and Delete buttons
        editStudentBtn.setEnabled(true);
        deleteStudentBtn.setEnabled(true);
        // disable Add button
        addStudentBtn.setEnabled(false);
    }

    public void fillStudentFromSelectedRow() {
        // lấy chỉ số của hàng được chọn
        int row = studentTable.getSelectedRow();
        if (row >= 0) {
            mssvField.setText(studentTable.getModel().getValueAt(row, 1).toString());
            hotenField.setText(studentTable.getModel().getValueAt(row, 2).toString());
            cmndField.setText(studentTable.getModel().getValueAt(row, 3).toString());
            if (studentTable.getModel().getValueAt(row, 4).toString().trim().equals("Nam"))
                gioitinhBox.setSelectedIndex(0);
            else
                gioitinhBox.setSelectedIndex(1);
            int index = 0;
            String lopStr = studentTable.getModel().getValueAt(row, 5).toString().trim();
            do {
                if (lop[index].equals(lopStr)){
                    lopBox.setSelectedIndex(index);
                    break;
                }else
                    index++;
            }
            while (true);

            // enable Edit and Delete buttons
            editStudentBtn.setEnabled(true);
            deleteStudentBtn.setEnabled(true);
            // disable Add button
            addStudentBtn.setEnabled(false);
        }
    }

    public void clearStudentInfo() {
        mssvField.setText("");
        hotenField.setText("");
        cmndField.setText("");
        // disable Edit and Delete buttons
        editStudentBtn.setEnabled(false);
        deleteStudentBtn.setEnabled(false);
        // enable Add button
        addStudentBtn.setEnabled(true);
    }

    public SinhvienEntity getStudentInfo() {
        // validate student
        if (!validateName()) {
            return null;
        }
        try {
            SinhvienEntity student = new SinhvienEntity();
            if (mssvField.getText() != null && !"".equals(mssvField.getText().trim())) {
                student.setMssv(mssvField.getText());
            }
            student.setHoten(hotenField.getText().trim());
            student.setCmnd(cmndField.getText().trim());
            student.setGioitinh(gioitinhBox.getSelectedItem().toString());
            LophocEntity lophocEntity = new LophocEntity(lopBox.getSelectedItem().toString());
            student.setLop(lophocEntity);
            return student;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }

    private boolean validateName() {
        String name = hotenField.getText();
        if (name == null || "".equals(name.trim())) {
            hotenField.requestFocus();
            showMessage("Name không được trống.");
            return false;
        }
        return true;
    }

    public void valueChanged(ListSelectionEvent e) {
    }

    public void addAddStudentListener(ActionListener listener) {
        addStudentBtn.addActionListener(listener);
    }

    public void addEdiStudentListener(ActionListener listener) {
        editStudentBtn.addActionListener(listener);
    }

    public void addDeleteStudentListener(ActionListener listener) {
        deleteStudentBtn.addActionListener(listener);
    }

    public void addClearListener(ActionListener listener) {
        clearBtn.addActionListener(listener);
    }

    public void addImportListener(ActionListener listener) {
        importBtn.addActionListener(listener);
    }

    public void addSortListener(ActionListener listener) {
        chonlopBox.addActionListener(listener);
    }

    public int getSortIndex(){
        String item = chonlopBox.getSelectedItem().toString();
        int index = 0;
        do {
            if (sortlop[index].equals(item)){
                chonlopBox.setSelectedIndex(index);
                break;
            }else
                index++;
        }
        while (true);
        return index;
    }

    public void sort(int index, StudentDao studentDao){
        if (index == 0) {
            List<SinhvienEntity> sinhvien = studentDao.readListStudents();
            showListStudents(sinhvien);
        }
        else{
            List<SinhvienEntity> sinhvien = studentDao.readListByLop(sortlop[index]);
            showListStudents(sinhvien);
        }
    }

    public void addListStudentSelectionListener(ListSelectionListener listener) {
        studentTable.getSelectionModel().addListSelectionListener(listener);
    }

    public void actionPerformed(ActionEvent e) {

    }
}
