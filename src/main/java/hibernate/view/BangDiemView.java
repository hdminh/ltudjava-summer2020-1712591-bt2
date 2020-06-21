package hibernate.view;

import hibernate.controller.QuanLySinhVienController;
import hibernate.dao.BangdiemDao;
import hibernate.dao.LophocDao;
import hibernate.dao.MonhocDao;
import hibernate.dao.StudentDao;
import hibernate.entity.*;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BangDiemView extends JFrame implements ActionListener {
    String [] lop;
    String [] chonlop;
    String [] mssv;

    private static final long serialVersionUID = 1L;
    private JButton addStudentBtn;
    private JButton editStudentBtn;
    private JButton deleteStudentBtn;
    private JButton importBtn;
    private JButton clearBtn;
    private JScrollPane jScrollPaneStudentTable;
    private JTable studentTable;

    private JLabel lopLabel;
    private JLabel svLabel;
    private JLabel chonlopLabel;
    private JLabel gkLabel;
    private JLabel ckLabel;
    private JLabel khacLabel;
    private JLabel tongLabel;
    private JLabel dauLabel;
    private JLabel rotLabel;

    private JTextField gkField;
    private JTextField ckField;
    private JTextField khacField;
    private JTextField tongField;

    public void setLop(String[] lop) {
        this.lop = lop;
    }

    public void setChonlop(String[] chonlop) {
        this.chonlop = chonlop;
    }

    public void setMssv(String[] mssv) {
        this.mssv = mssv;
    }

    private JComboBox svBox;
    private JComboBox lopBox;
    private JComboBox chonlopBox;

    private String [] columnNames = new String [] {
            "STT", "MSSV", "Họ Tên", "Điểm GK", "Điểm CK", "Điểm khác", "Điểm tổng", "Lớp", "KQ",};
    private Object data = new Object [][] {};

    public BangDiemView() {
    }

    public void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // khởi tạo các phím chức năng
        addStudentBtn = new JButton("Thêm");
        deleteStudentBtn = new JButton("Xóa");
        editStudentBtn = new JButton("Sửa");
        importBtn = new JButton("Import");
        clearBtn = new JButton("Nhập lại");
        jScrollPaneStudentTable = new JScrollPane();
        studentTable = new JTable();

        svLabel = new JLabel("Sinh viên");
        lopLabel = new JLabel("Lớp");
        gkLabel = new JLabel("Giữa kỳ");
        ckLabel = new JLabel("Cuối kỳ");
        khacLabel = new JLabel("Điểm khác");
        tongLabel = new JLabel("Điểm tổng");
        chonlopLabel = new JLabel("Chọn lớp");
        dauLabel = new JLabel("Đậu: 0 (0%)");
        rotLabel = new JLabel("Rớt: 0 (0%)");

        gkField = new JTextField(15);
        ckField = new JTextField(15);
        khacField = new JTextField(15);
        tongField = new JTextField(15);
        svBox = new JComboBox(mssv);
        lopBox = new JComboBox(lop);
        chonlopBox = new JComboBox(chonlop);

        studentTable.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        jScrollPaneStudentTable.setViewportView(studentTable);
        jScrollPaneStudentTable.setPreferredSize(new Dimension(650, 500));

        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setSize(1280, 720);
        panel.setLayout(layout);
        panel.add(jScrollPaneStudentTable);

        panel.add(addStudentBtn);
        panel.add(deleteStudentBtn);
        panel.add(editStudentBtn);
        panel.add(importBtn);
        panel.add(clearBtn);

        panel.add(svLabel);
        panel.add(lopLabel);
        panel.add(gkLabel);
        panel.add(ckLabel);
        panel.add(khacLabel);
        panel.add(tongLabel);
        panel.add(chonlopLabel);

        panel.add(dauLabel);
        panel.add(rotLabel);

        panel.add(gkField);
        panel.add(ckField);
        panel.add(khacField);
        panel.add(tongField);

        panel.add(svBox);
        panel.add(lopBox);
        panel.add(chonlopBox);

        layout.putConstraint(SpringLayout.WEST, svLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, svLabel, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.NORTH, lopLabel, 40, SpringLayout.NORTH, svLabel);
        layout.putConstraint(SpringLayout.WEST, lopLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, gkLabel, 30, SpringLayout.NORTH, lopLabel);
        layout.putConstraint(SpringLayout.WEST, gkLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ckLabel, 30, SpringLayout.NORTH, gkLabel);
        layout.putConstraint(SpringLayout.WEST, ckLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, khacLabel, 30, SpringLayout.NORTH, ckLabel);
        layout.putConstraint(SpringLayout.WEST, khacLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tongLabel, 30, SpringLayout.NORTH, khacLabel);
        layout.putConstraint(SpringLayout.WEST, tongLabel, 10, SpringLayout.WEST, panel);

        layout.putConstraint(SpringLayout.WEST, gkField, 90, SpringLayout.WEST, gkLabel);
        layout.putConstraint(SpringLayout.NORTH, gkField, 0, SpringLayout.NORTH, gkLabel);

        layout.putConstraint(SpringLayout.WEST, ckField, 90, SpringLayout.WEST, ckLabel);
        layout.putConstraint(SpringLayout.NORTH, ckField, 0, SpringLayout.NORTH, ckLabel);

        layout.putConstraint(SpringLayout.WEST, khacField, 90, SpringLayout.WEST, khacLabel);
        layout.putConstraint(SpringLayout.NORTH, khacField, 0, SpringLayout.NORTH, khacLabel);

        layout.putConstraint(SpringLayout.WEST, tongField, 90, SpringLayout.WEST, tongLabel);
        layout.putConstraint(SpringLayout.NORTH, tongField, 0, SpringLayout.NORTH, tongLabel);


        layout.putConstraint(SpringLayout.WEST, lopBox, 90, SpringLayout.WEST, lopLabel);
        layout.putConstraint(SpringLayout.NORTH, lopBox, 0, SpringLayout.NORTH, lopLabel);

        layout.putConstraint(SpringLayout.WEST, svBox, 90, SpringLayout.WEST, svLabel);
        layout.putConstraint(SpringLayout.NORTH, svBox, 0, SpringLayout.NORTH, svLabel);

        layout.putConstraint(SpringLayout.WEST, jScrollPaneStudentTable, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneStudentTable, 10, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, addStudentBtn, 15, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addStudentBtn, 240, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, editStudentBtn, 70, SpringLayout.WEST, addStudentBtn);
        layout.putConstraint(SpringLayout.NORTH, editStudentBtn, 0, SpringLayout.NORTH, addStudentBtn);

        layout.putConstraint(SpringLayout.WEST, deleteStudentBtn, 60, SpringLayout.WEST, editStudentBtn);

        layout.putConstraint(SpringLayout.NORTH, clearBtn, 240, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, clearBtn, 60, SpringLayout.WEST, deleteStudentBtn);

        layout.putConstraint(SpringLayout.NORTH, deleteStudentBtn, 240, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, importBtn, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.SOUTH, importBtn, 50, SpringLayout.SOUTH, jScrollPaneStudentTable);

        layout.putConstraint(SpringLayout.WEST, chonlopLabel, 80, SpringLayout.WEST, importBtn);
        layout.putConstraint(SpringLayout.NORTH, chonlopLabel, 0, SpringLayout.NORTH, importBtn);

        layout.putConstraint(SpringLayout.WEST, chonlopBox, 60, SpringLayout.WEST, chonlopLabel);
        layout.putConstraint(SpringLayout.NORTH, chonlopBox, 0, SpringLayout.NORTH, chonlopLabel);

        layout.putConstraint(SpringLayout.WEST, dauLabel, 200, SpringLayout.WEST, chonlopBox);
        layout.putConstraint(SpringLayout.NORTH, dauLabel, 0, SpringLayout.NORTH, chonlopBox);

        layout.putConstraint(SpringLayout.WEST, rotLabel, 200, SpringLayout.WEST, chonlopBox);
        layout.putConstraint(SpringLayout.NORTH, rotLabel, 50, SpringLayout.NORTH, dauLabel);


        this.add(panel);
        this.pack();
        this.setTitle("Bảng điểm");
        this.setSize(1280, 720);
        deleteStudentBtn.setEnabled(false);
        addStudentBtn.setEnabled(true);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void showListStudents(List<BangdiemEntity> list) {
        int size = list.size();
        int dau = 0;
        int rot = 0;

        Object [][] students = new Object[size][9];
        for (int i = 0; i < size; i++) {
            students[i][0] = (i + 1);
            students[i][1] = list.get(i).getSinhvien();
            students[i][2] = list.get(i).getHoten();
            students[i][3] = list.get(i).getDiemgk();
            students[i][4] = list.get(i).getDiemck();
            students[i][5] = list.get(i).getDiemkhac();
            students[i][6] = list.get(i).getDiemtong();
            String tenlop = list.get(i).getLophoc().getMalop() + "-" + list.get(i).getMonhoc();
            students[i][7] = tenlop;
            if (list.get(i).getDau() == 1) {
                students[i][8] = "Đậu";
                dau++;
            }
            else {
                students[i][8] = "Rớt";
                rot++;
            }
        }
        double dauPercent = Math.round((dau * 100.0 / size) * 100.0) / 100.0;
        double rotPercent = 100 - dauPercent;
        dauLabel.setText("Đậu: " + dau + " (" + dauPercent + "%)");
        rotLabel.setText("Rớt: " + rot + " (" + rotPercent + "%)");
        studentTable.setModel(new DefaultTableModel(students, columnNames));
    }

    public void showStudent(BangdiemEntity student) {
        int index = 0;
        String sinhvien = student.getSinhvien().trim();
        do {
            if (mssv[index].equals(sinhvien)){
                lopBox.setSelectedIndex(index);
                break;
            }else
                index++;
        }
        while (true);
        index = 0;
        String lopStr = student.getLophoc().getMalop().trim();
        do {
            if (lop[index].equals(lopStr)){
                lopBox.setSelectedIndex(index);
                break;
            }else
                index++;
        }
        while (true);
        deleteStudentBtn.setEnabled(true);
        addStudentBtn.setEnabled(false);
    }

    public void fillStudentFromSelectedRow() {
        int row = studentTable.getSelectedRow();
        if (row >= 0) {
            int index = 0;
            String sinhvien = studentTable.getModel().getValueAt(row, 1).toString().trim();
            do {
                String [] mssvStr = mssv[index].split("-");
                if (mssvStr[0].trim().equals(sinhvien)){
                    svBox.setSelectedIndex(index);
                    break;
                }else{
                    index++;
                }
            }
            while (true);

            index = 0;
            String lopStr = studentTable.getModel().getValueAt(row, 7).toString().trim();
            do {

                if (lop[index].trim().equals(lopStr)){
                    lopBox.setSelectedIndex(index);
                    break;
                }else {
                    index++;
                }
            }
            while (true);
            gkField.setText(studentTable.getModel().getValueAt(row, 3).toString());
            ckField.setText(studentTable.getModel().getValueAt(row, 4).toString());
            khacField.setText(studentTable.getModel().getValueAt(row, 5).toString());
            tongField.setText(studentTable.getModel().getValueAt(row, 6).toString());
            deleteStudentBtn.setEnabled(true);
            addStudentBtn.setEnabled(false);
        }
    }

    public void clearStudentInfo() {
        svBox.setSelectedIndex(0);
        lopBox.setSelectedIndex(0);
        deleteStudentBtn.setEnabled(false);
        addStudentBtn.setEnabled(true);
    }

    public BangdiemEntity getStudentInfo() {
        try {
            BangdiemEntity student = new BangdiemEntity();
            String mssv = svBox.getSelectedItem().toString();
            String [] splitMssv = mssv.split("-");
            String monhoc = lopBox.getSelectedItem().toString();
            String [] splitMon = monhoc.split("-");
            LophocEntity lophocEntity = new LophocEntity(splitMon[0]);
            student.setSinhvien(splitMssv[0]);
            student.setHoten(splitMssv[1]);
            student.setDiemgk(Double.parseDouble(gkField.toString()));
            student.setDiemck(Double.parseDouble(ckField.toString()));
            student.setDiemkhac(Double.parseDouble(khacField.toString()));
            student.setDiemtong(Double.parseDouble(tongField.toString()));
            if (student.getDiemtong() >= 5) {
                student.setDau((byte) 1);
            }
            else{
                student.setDau((byte) 0);
            }

            student.setMonhoc(splitMon[1]);
            student.setLophoc(lophocEntity);
            return student;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }

    public BangdiemEntity getStudentInfoFromSelectedRow(){
        int row = studentTable.getSelectedRow();
        try {
            BangdiemEntity bangdiem = new BangdiemEntity();

            bangdiem.setSinhvien(studentTable.getModel().getValueAt(row, 1).toString());
            bangdiem.setHoten(studentTable.getModel().getValueAt(row, 2).toString());
            bangdiem.setDiemgk(Double.parseDouble(studentTable.getModel().getValueAt(row, 3).toString()));
            bangdiem.setDiemck(Double.parseDouble(studentTable.getModel().getValueAt(row, 4).toString()));
            bangdiem.setDiemkhac(Double.parseDouble(studentTable.getModel().getValueAt(row, 5).toString()));
            bangdiem.setDiemtong(Double.parseDouble(studentTable.getModel().getValueAt(row, 6).toString()));
            String monhoc = studentTable.getModel().getValueAt(row, 7).toString();
            String [] splitMon = monhoc.split("-");
            LophocEntity lophocEntity = new LophocEntity(splitMon[0]);
            bangdiem.setMonhoc(splitMon[1]);
            bangdiem.setLophoc(lophocEntity);
            if (studentTable.getModel().getValueAt(row, 8).toString().trim().equals("Đậu")){
                bangdiem.setDau((byte) 1);
            }
            else{
                bangdiem.setDau((byte) 0);

            }
            return bangdiem;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }

    public BangdiemEntity setUpdateBangdiem() {
        BangdiemEntity bangdiem = getStudentInfoFromSelectedRow();
        if (bangdiem != null) {
            bangdiem.setDiemgk(Double.parseDouble(gkField.getText().trim()));
            bangdiem.setDiemck(Double.parseDouble(ckField.getText().trim()));
            bangdiem.setDiemkhac(Double.parseDouble(khacField.getText().trim()));
            bangdiem.setDiemtong(Double.parseDouble(tongField.getText().trim()));
            if (bangdiem.getDiemtong() >= 5){
                bangdiem.setDau((byte) 1);
            }
            else {
                bangdiem.setDau((byte) 0);
            }
            return bangdiem;
        }
        return null;
    }

    public void addAddStudentListener(ActionListener listener) {
        addStudentBtn.addActionListener(listener);
    }

    public void addDeleteStudentListener(ActionListener listener) {
        deleteStudentBtn.addActionListener(listener);
    }

    public void addEditStudentListener(ActionListener listener) {
        editStudentBtn.addActionListener(listener);
    }

    public void addClearListener(ActionListener listener) {
        clearBtn.addActionListener(listener);
    }

    public void addSortListener(ActionListener listener) {
        chonlopBox.addActionListener(listener);
    }

    public int getSortIndex(){
        int index = 0;
        if (chonlopBox.getItemCount() > 0) {
            String item = chonlopBox.getSelectedItem().toString();
            do {
                if (chonlop[index].equals(item)) {
                    chonlopBox.setSelectedIndex(index);
                    break;
                } else
                    index++;
            }
            while (true);
        }
        return index;
    }

    public void sort(int index, BangdiemDao bangdiemDao){


        if (index == 0) {
            List<BangdiemEntity> sinhvien = bangdiemDao.readListStudents();
            showListStudents(sinhvien);
        }
        else{
            String[] split = chonlop[index].split("-");
            String malop = split[0];
            String mamon = split[1];
            List<BangdiemEntity> sinhvien = bangdiemDao.readListByMon(split[1]);
            showListStudents(sinhvien);
        }
    }

    public void refreshComboBox(){

        int sortsize = chonlop.length;
        int index = 0;
        if (chonlopBox.getItemCount() > 0) {
            chonlopBox.removeAllItems();
        }
        while (index < sortsize){
            chonlopBox.addItem(chonlop[index]);
            index++;
        }
        sortsize = lop.length;
        index = 0;
        lopBox.removeAllItems();
        while (index < sortsize){
            lopBox.addItem(lop[index]);
            index++;
        }

        sortsize = mssv.length;
        index = 0;
        svBox.removeAllItems();
        while (index < sortsize){
            svBox.addItem(mssv[index]);
            index++;
        }
    }

    public void addImportListener(ActionListener listener) {
        importBtn.addActionListener(listener);
    }


    public void addListStudentSelectionListener(ListSelectionListener listener) {
        studentTable.getSelectionModel().addListSelectionListener(listener);
    }

    public void actionPerformed(ActionEvent e) {

    }


}
