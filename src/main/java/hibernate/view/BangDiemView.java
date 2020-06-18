package hibernate.view;

import hibernate.controller.QuanLySinhVienController;
import hibernate.controller.ThoiKhoaBieuController;
import hibernate.dao.DanhsachlopDao;
import hibernate.dao.MonhocDao;
import hibernate.dao.StudentDao;
import hibernate.entity.DanhsachlopEntity;
import hibernate.entity.MonhocEntity;
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

public class BangDiemView extends JFrame implements ActionListener {
    StudentDao studentDao = new StudentDao();
    MonhocDao monhocDao = new MonhocDao();
    DanhsachlopDao svhocmonDao = new DanhsachlopDao();
    String [] lop;
    String [] chonlop;
    String [] mssv;

    private static final long serialVersionUID = 1L;
    private JButton addStudentBtn;
    private JButton deleteStudentBtn;
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

    private JTextField gkField;
    private JTextField ckField;
    private JTextField khacField;
    private JTextField tongField;
    private JComboBox svBox;
    private JComboBox lopBox;
    private JComboBox chonlopBox;

    private String [] columnNames = new String [] {
            "STT", "MSSV", "Họ Tên", "Điểm GK", "Điểm CK", "Điểm khác", "Điểm tổng", "Lớp", "KQ",};
    private Object data = new Object [][] {};

    public BangDiemView() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // khởi tạo các phím chức năng
        addStudentBtn = new JButton("Thêm");
        deleteStudentBtn = new JButton("Xóa");
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
//        List<MonhocEntity> monhoc = monhocDao.readListMonhoc();
//        List<String> monhocList = new ArrayList<String>();
//        for (MonhocEntity mh : monhoc){
//            String mamon = mh.getMamon();
//            String malop = mh.getLophoc().getMalop();
//            monhocList.add(malop + "-" + mamon);
//        }
//        List<SinhvienEntity> sinhvien = studentDao.readListStudents();
//        List<String> mssvList = new ArrayList<String>();
//        for (SinhvienEntity sv: sinhvien){
//            String mssv = sv.getMssv();
//            String ten = sv.getHoten();
//            mssvList.add(mssv + "-" + ten);
//        }
//        lop = monhocList.toArray(new String[0]);
//        monhocList.add(0, "Tất cả");
//        chonlop = monhocList.toArray(new String[0]);
       // mssv = mssvList.toArray(new String[0]);
        gkField = new JTextField(15);
        ckField = new JTextField(15);
        khacField = new JTextField(15);
        tongField = new JTextField(15);
//        svBox = new JComboBox(mssv);
//        lopBox = new JComboBox(lop);
//        chonlopBox = new JComboBox(chonlop);

        studentTable.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        jScrollPaneStudentTable.setViewportView(studentTable);
        jScrollPaneStudentTable.setPreferredSize(new Dimension(600, 400));

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
        panel.add(gkLabel);
        panel.add(ckLabel);
        panel.add(khacLabel);
        panel.add(tongLabel);

        panel.add(gkField);
        panel.add(ckField);
        panel.add(khacField);
        panel.add(tongField);

//        panel.add(svBox);
//        panel.add(lopBox);
//        panel.add(chonlopBox);

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


//        layout.putConstraint(SpringLayout.WEST, lopBox, 90, SpringLayout.WEST, lopLabel);
//        layout.putConstraint(SpringLayout.NORTH, lopBox, 0, SpringLayout.NORTH, lopLabel);
//
//        layout.putConstraint(SpringLayout.WEST, svBox, 90, SpringLayout.WEST, svLabel);
//        layout.putConstraint(SpringLayout.NORTH, svBox, 0, SpringLayout.NORTH, svLabel);

        layout.putConstraint(SpringLayout.WEST, jScrollPaneStudentTable, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneStudentTable, 10, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, addStudentBtn, 15, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addStudentBtn, 240, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, deleteStudentBtn, 60, SpringLayout.WEST, addStudentBtn);

        layout.putConstraint(SpringLayout.NORTH, clearBtn, 240, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, clearBtn, 60, SpringLayout.WEST, deleteStudentBtn);

        layout.putConstraint(SpringLayout.NORTH, deleteStudentBtn, 240, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, chonlopLabel, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, chonlopLabel, 330, SpringLayout.NORTH, panel);

//        layout.putConstraint(SpringLayout.WEST, chonlopBox, 60, SpringLayout.WEST, chonlopLabel);
//        layout.putConstraint(SpringLayout.NORTH, chonlopBox, 0, SpringLayout.NORTH, chonlopLabel);



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

    public void showListStudents(List<DanhsachlopEntity> list) {
        int size = list.size();

        Object [][] students = new Object[size][6];
        for (int i = 0; i < size; i++) {
            students[i][0] = (i + 1);
            students[i][1] = list.get(i).getSinhvien();
            students[i][2] = list.get(i).getHoten();
            students[i][3] = list.get(i).getCmnd();
            students[i][4] = list.get(i).getGioitinh();
            String tenlop = list.get(i).getLop()+ "-" + list.get(i).getMonhoc();
            students[i][5] = tenlop;
        }
        studentTable.setModel(new DefaultTableModel(students, columnNames));
    }

    public void showStudent(DanhsachlopEntity student) {
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
        String lopStr = student.getLop().trim();
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
            int indexmssv = 0;
            String sinhvien = studentTable.getModel().getValueAt(row, 1).toString().trim();
            do {
                String [] mssvStr = mssv[indexmssv].split("-");
                if (mssvStr[0].trim().equals(sinhvien)){
                    lopBox.setSelectedIndex(index);
                    break;
                }else{
                    index++;
                }
            }
            while (true);

            index = 0;
            int indexlop = 0;
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
            String monhoc = lopBox.getSelectedItem().toString();
            student.setSinhvien(mssv);
            student.setMonhoc(monhoc);
            return student;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }
    public void valueChanged(ListSelectionEvent e) {
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


    public void addSortListener(ActionListener listener) {
        chonlopBox.addActionListener(listener);
    }

    public int getSortIndex(){
        String item = chonlopBox.getSelectedItem().toString();
        int index = 0;
        do {
            if (chonlop[index].equals(item)){
                chonlopBox.setSelectedIndex(index);
                break;
            }else
                index++;
        }
        while (true);
        return index;
    }

    public void sort(int index, StudentDao studentDao){

        String[] split = chonlop[index].split("-");
        String malop = split[0];
        String mamon = split[1];
        if (index == 0) {
            List<DanhsachlopEntity> sinhvien = svhocmonDao.readListStudents();
            showListStudents(sinhvien);
        }
        else{
            List<DanhsachlopEntity> sinhvien = svhocmonDao.readListByMon(split[1]);
            showListStudents(sinhvien);
        }
    }

    public void addListStudentSelectionListener(ListSelectionListener listener) {
        studentTable.getSelectionModel().addListSelectionListener(listener);
    }

    public void actionPerformed(ActionEvent e) {

    }
}
