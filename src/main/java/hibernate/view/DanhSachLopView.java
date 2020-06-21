package hibernate.view;

import hibernate.controller.QuanLySinhVienController;
import hibernate.dao.DanhsachlopDao;
import hibernate.entity.DanhsachlopEntity;
import hibernate.entity.LophocEntity;
import hibernate.entity.SinhvienEntity;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DanhSachLopView extends JFrame implements ActionListener {
    QuanLySinhVienController qlsvController = new QuanLySinhVienController();
    String [] lop;
    String [] chonmon;
    String [] chonlop;
    String [] mssv;

    private static final long serialVersionUID = 1L;
    private JButton addStudentBtn;
    private JButton deleteStudentBtn;
    private JButton clearBtn;
    private JScrollPane jScrollPaneStudentTable;
    private JTable studentTable;

    private JLabel svLabel;
    private JLabel lopLabel;
    private JLabel chonlopLabel;
    private JLabel chonmonLabel;

    private JComboBox svBox;
    private JComboBox lopBox;
    private JComboBox chonlopBox;
    private JComboBox chonmonBox;

    private String [] columnNames = new String [] {
            "STT", "MSSV", "Họ Tên", "CMND", "Giới tính", "Lớp"};
    private Object data = new Object [][] {};

    public void setLop(String[] lop) {
        this.lop = lop;
    }

    public void setChonmon(String[] chonmon) {
        this.chonmon = chonmon;
    }

    public void setChonlop(String[] chonlop) {
        this.chonlop = chonlop;
    }

    public void setMssv(String[] mssv) {
        this.mssv = mssv;
    }

    public DanhSachLopView() {
    }

    public void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // khởi tạo các phím chức năng
        addStudentBtn = new JButton("Thêm");
        deleteStudentBtn = new JButton("Xóa");
        clearBtn = new JButton("Nhập lại");
        jScrollPaneStudentTable = new JScrollPane();
        studentTable = new JTable();

        svLabel = new JLabel("Sinh viên");
        lopLabel = new JLabel("Lớp");
        chonlopLabel = new JLabel("Chọn lớp");
        chonmonLabel = new JLabel("Chọn môn");

        svBox = new JComboBox(mssv);
        lopBox = new JComboBox(lop);
        chonlopBox = new JComboBox(chonlop);
        chonmonBox = new JComboBox(chonmon);

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
        panel.add(clearBtn);

        panel.add(svLabel);
        panel.add(lopLabel);
        panel.add(chonlopLabel);
        panel.add(chonmonLabel);

        panel.add(svBox);
        panel.add(lopBox);
        panel.add(chonlopBox);
        panel.add(chonmonBox);

        layout.putConstraint(SpringLayout.WEST, svLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, svLabel, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.NORTH, lopLabel, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, lopLabel, 10, SpringLayout.WEST, panel);

        layout.putConstraint(SpringLayout.WEST, lopBox, 90, SpringLayout.WEST, lopLabel);
        layout.putConstraint(SpringLayout.NORTH, lopBox, 0, SpringLayout.NORTH, lopLabel);

        layout.putConstraint(SpringLayout.WEST, svBox, 90, SpringLayout.WEST, svLabel);
        layout.putConstraint(SpringLayout.NORTH, svBox, 0, SpringLayout.NORTH, svLabel);

        layout.putConstraint(SpringLayout.WEST, jScrollPaneStudentTable, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneStudentTable, 10, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, addStudentBtn, 15, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addStudentBtn, 240, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, deleteStudentBtn, 60, SpringLayout.WEST, addStudentBtn);

        layout.putConstraint(SpringLayout.NORTH, clearBtn, 240, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, clearBtn, 60, SpringLayout.WEST, deleteStudentBtn);

        layout.putConstraint(SpringLayout.NORTH, deleteStudentBtn, 240, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, chonlopLabel, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.SOUTH, chonlopLabel, 50, SpringLayout.SOUTH, jScrollPaneStudentTable);

        layout.putConstraint(SpringLayout.WEST, chonlopBox, 60, SpringLayout.WEST, chonlopLabel);
        layout.putConstraint(SpringLayout.NORTH, chonlopBox, 0, SpringLayout.NORTH, chonlopLabel);

        layout.putConstraint(SpringLayout.WEST, chonmonLabel, 80, SpringLayout.WEST, chonlopBox);
        layout.putConstraint(SpringLayout.NORTH, chonmonLabel, 0, SpringLayout.NORTH, chonlopBox);

        layout.putConstraint(SpringLayout.WEST, chonmonBox, 70, SpringLayout.WEST, chonmonLabel);
        layout.putConstraint(SpringLayout.NORTH, chonmonBox, 0, SpringLayout.NORTH, chonmonLabel);

        this.add(panel);
        this.pack();
        this.setSize(1000, 700);
        deleteStudentBtn.setEnabled(false);
        addStudentBtn.setEnabled(true);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void showListStudents(List<DanhsachlopEntity> list) {
        int size = list.size();

        Object [][] students = new Object[size][6];
        for (int i = 0; i < size; i++) {
            students[i][0] = (i + 1);
            students[i][1] = list.get(i).getSinhvien();
            students[i][2] = list.get(i).getHoten();
            students[i][3] = list.get(i).getCmnd();
            students[i][4] = list.get(i).getGioitinh();
            String tenlop = list.get(i).getLop().getMalop() + "-" + list.get(i).getMonhoc();
            students[i][5] = tenlop;
        }
        studentTable.setModel(new DefaultTableModel(students, columnNames));
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
            String lopStr = studentTable.getModel().getValueAt(row, 5).toString().trim();
            do {

                if (lop[index].trim().equals(lopStr)){
                    lopBox.setSelectedIndex(index);
                    break;
                }else {
                    index++;
                }
            }
            while (true);
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

    public DanhsachlopEntity getStudentInfo() {
        try {
            DanhsachlopEntity student = new DanhsachlopEntity();
            String mssv = svBox.getSelectedItem().toString();
            String [] splitMssv = mssv.split("-");
            SinhvienEntity sinhvienEntity = qlsvController.findSinhVienByMssv(splitMssv[0]);
            System.out.println(sinhvienEntity.getCmnd());
            String monhoc = lopBox.getSelectedItem().toString();
            String [] splitMon = monhoc.split("-");
            LophocEntity lophocEntity = new LophocEntity(splitMon[0]);
            student.setSinhvien(sinhvienEntity.getMssv());
            student.setHoten(sinhvienEntity.getHoten());
            student.setCmnd(sinhvienEntity.getCmnd());
            student.setGioitinh(sinhvienEntity.getGioitinh());
            student.setMonhoc(splitMon[1]);
            student.setLop(lophocEntity);
            return student;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }

    public DanhsachlopEntity getStudentInfoFromSelectedRow(){
        int row = studentTable.getSelectedRow();
        try {
            DanhsachlopEntity student = new DanhsachlopEntity();

            student.setSinhvien(studentTable.getModel().getValueAt(row, 1).toString());
            student.setHoten(studentTable.getModel().getValueAt(row, 2).toString());
            student.setCmnd(studentTable.getModel().getValueAt(row, 3).toString());
            student.setGioitinh(studentTable.getModel().getValueAt(row, 4).toString());
            String monhoc = studentTable.getModel().getValueAt(row, 5).toString();
            String [] splitMon = monhoc.split("-");
            LophocEntity lophocEntity = new LophocEntity(splitMon[0]);
            student.setMonhoc(splitMon[1]);
            student.setLop(lophocEntity);
            return student;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;

    }

    public void addAddStudentListener(ActionListener listener) {
        addStudentBtn.addActionListener(listener);
    }

    public void addDeleteStudentListener(ActionListener listener) {
        deleteStudentBtn.addActionListener(listener);
    }

    public void addClearListener(ActionListener listener) {
        clearBtn.addActionListener(listener);
    }

    public void addSortMonListener(ActionListener listener) {
        chonmonBox.addActionListener(listener);
    }

    public void addSortLopListener(ActionListener listener){
        chonlopBox.addActionListener(listener);
    }

    public int getSortMonIndex(){

        int index = 0;
        if (chonmonBox.getItemCount() > 0) {
            String item = chonmonBox.getSelectedItem().toString();
            do {
                if (chonmon[index].equals(item)) {
                    break;
                } else
                    index++;
            }
            while (true);
        }

        return index;
    }

    public int getSortLopIndex(){
        int index = 0;
        if (chonlopBox.getItemCount() > 0) {
            String item = chonlopBox.getSelectedItem().toString();
            do {
                if (chonlop[index].equals(item)) {
                    break;
                } else
                    index++;
            }
            while (true);
        }
        return index;
    }

    public void sortMon(int index, DanhsachlopDao danhsachlopDao){

        if (index == 0) {
            if (chonlopBox.getSelectedIndex() > 0){
                showListStudents(danhsachlopDao.readListStudentsByLop(chonlopBox.getSelectedItem().toString().trim()));
            }
            else{
                List<DanhsachlopEntity> sinhvien = danhsachlopDao.readListStudents();
                showListStudents(sinhvien);
            }
        }
        else{
            String[] split = chonmon[index].split("-");
            List<DanhsachlopEntity> sinhvien = danhsachlopDao.readListByMon(split[1]);
            showListStudents(sinhvien);
        }
    }

    public void sortLop(int index, DanhsachlopDao danhsachlopDao){

        if (index == 0) {
            List<DanhsachlopEntity> sinhvien = danhsachlopDao.readListStudents();
            showListStudents(sinhvien);
            int sortsize = chonmon.length;
            int indexBox = 0;
            if (chonmonBox.getItemCount() > 0) {
                chonmonBox.removeAllItems();
            }
            while (indexBox < sortsize){
                chonmonBox.addItem(chonmon[indexBox]);
                indexBox++;
            }
        }
        else{
            List<DanhsachlopEntity> sinhvien = danhsachlopDao.readListStudentsByLop(chonlop[index]);
            chonmonBox.removeAllItems();
            chonmonBox.addItem(chonmon[0]);
            for (String s: chonmon){
                String [] split = s.split("-");
                if (split[0].trim().equals(chonlop[index])){
                    chonmonBox.addItem(s);
                }
            }
            showListStudents(sinhvien);
        }
    }

    public void refreshComboBox(){
        int sortsize = chonmon.length;
        int index = 0;
        if (chonmonBox.getItemCount() > 0) {
            chonmonBox.removeAllItems();
        }
        while (index < sortsize){
            chonmonBox.addItem(chonmon[index]);
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

        sortsize = chonlop.length;
        index = 0;
        chonlopBox.removeAllItems();
        while (index < sortsize){
            chonlopBox.addItem(chonlop[index]);
            index++;
        }
    }

    public void addListStudentSelectionListener(ListSelectionListener listener) {
        studentTable.getSelectionModel().addListSelectionListener(listener);
    }

    public void actionPerformed(ActionEvent e) {
    }
}
