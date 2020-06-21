package hibernate.view;

import hibernate.dao.BangdiemDao;
import hibernate.entity.BangdiemEntity;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SinhVienXemDiemView extends JFrame implements ActionListener {

    String [] lop;
    String [] chonlop;

    private static final long serialVersionUID = 1L;
    private JScrollPane jScrollPaneStudentTable;
    private JTable studentTable;

    private JLabel lopLabel;
    private JLabel chonlopLabel;
    private JLabel gkLabel;
    private JLabel ckLabel;
    private JLabel khacLabel;
    private JLabel tongLabel;

    private JTextField gkField;
    private JTextField ckField;
    private JTextField khacField;
    private JTextField tongField;
    private JComboBox lopBox;
    private JComboBox chonlopBox;

    private String [] columnNames = new String [] {
            "STT", "MSSV", "Họ Tên", "Điểm GK", "Điểm CK", "Điểm khác", "Điểm tổng", "Lớp", "KQ",};
    private Object data = new Object [][] {};

    public SinhVienXemDiemView() {
    }

    public void setLop(String[] lop) {
        this.lop = lop;
    }

    public void setChonlop(String[] chonlop) {
        this.chonlop = chonlop;
    }

    public void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // khởi tạo các phím chức năng
        jScrollPaneStudentTable = new JScrollPane();
        studentTable = new JTable();

        lopLabel = new JLabel("Lớp");
        gkLabel = new JLabel("Giữa kỳ");
        ckLabel = new JLabel("Cuối kỳ");
        khacLabel = new JLabel("Điểm khác");
        tongLabel = new JLabel("Điểm tổng");
        chonlopLabel = new JLabel("Chọn lớp");

        gkField = new JTextField(15);
        ckField = new JTextField(15);
        khacField = new JTextField(15);
        tongField = new JTextField(15);
        gkField.setEditable(false);
        ckField.setEditable(false);
        khacField.setEditable(false);
        tongField.setEditable(false);
        lopBox = new JComboBox(lop);
        lopBox.setEditable(false);
        chonlopBox = new JComboBox(chonlop);

        studentTable.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        jScrollPaneStudentTable.setViewportView(studentTable);
        jScrollPaneStudentTable.setPreferredSize(new Dimension(650, 500));

        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setSize(1280, 720);
        panel.setLayout(layout);
        panel.add(jScrollPaneStudentTable);

        panel.add(lopLabel);
        panel.add(gkLabel);
        panel.add(ckLabel);
        panel.add(khacLabel);
        panel.add(tongLabel);
        panel.add(chonlopLabel);

        panel.add(gkField);
        panel.add(ckField);
        panel.add(khacField);
        panel.add(tongField);

        panel.add(lopBox);
        panel.add(chonlopBox);

        layout.putConstraint(SpringLayout.NORTH, lopLabel, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, lopLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, gkLabel, 30, SpringLayout.NORTH, lopLabel);
        layout.putConstraint(SpringLayout.WEST, gkLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ckLabel, 30, SpringLayout.NORTH, gkLabel);
        layout.putConstraint(SpringLayout.WEST, ckLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, khacLabel, 30, SpringLayout.NORTH, ckLabel);
        layout.putConstraint(SpringLayout.WEST, khacLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tongLabel, 30, SpringLayout.NORTH, khacLabel);
        layout.putConstraint(SpringLayout.WEST, tongLabel, 10, SpringLayout.WEST, panel);

        layout.putConstraint(SpringLayout.SOUTH, chonlopLabel, 30, SpringLayout.SOUTH, jScrollPaneStudentTable);
        layout.putConstraint(SpringLayout.WEST, chonlopLabel, 300, SpringLayout.WEST, panel);

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

        layout.putConstraint(SpringLayout.WEST, jScrollPaneStudentTable, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneStudentTable, 10, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, chonlopBox, 60, SpringLayout.WEST, chonlopLabel);
        layout.putConstraint(SpringLayout.NORTH, chonlopBox, 0, SpringLayout.NORTH, chonlopLabel);


        this.add(panel);
        this.pack();
        this.setTitle("Bảng điểm");
        this.setSize(1280, 720);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void showListStudents(List<BangdiemEntity> list) {
        int size = list.size();
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
            }
            else {
                students[i][8] = "Rớt";
            }
        }
        studentTable.setModel(new DefaultTableModel(students, columnNames));
    }

    public void fillStudentFromSelectedRow() {
        int row = studentTable.getSelectedRow();
        if (row >= 0) {
            int index = 0;
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
        }
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

    public void sort(int index, BangdiemDao bangdiemDao, String mssv){


        if (index == 0) {
            List<BangdiemEntity> sinhvien = bangdiemDao.readListBySinhvien(mssv);
            showListStudents(sinhvien);
        }
        else{
            String[] split = chonlop[index].split("-");
            List<BangdiemEntity> sinhvien = bangdiemDao.readListBySinhvienVaMon(mssv, split[1]);
            showListStudents(sinhvien);
        }
    }

    public void addListStudentSelectionListener(ListSelectionListener listener) {
        studentTable.getSelectionModel().addListSelectionListener(listener);
    }

    public void actionPerformed(ActionEvent e) {

    }

    public void setComboBox() {
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
        if (lopBox.getItemCount() > 0) {
            lopBox.removeAllItems();
        }
        while (index < sortsize){
            lopBox.addItem(lop[index]);
            index++;
        }
    }
}
