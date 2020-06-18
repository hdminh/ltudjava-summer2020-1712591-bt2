package hibernate.view;

import com.toedter.calendar.JDateChooser;
import hibernate.dao.DanhsachlopDao;
import hibernate.dao.MonhocDao;
import hibernate.dao.StudentDao;
import hibernate.entity.DanhsachlopEntity;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LichPhucKhaoView extends JFrame implements ActionListener {
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

    private JLabel bdLabel;
    private JLabel ktLabel;

    private JDateChooser bdDate;
    private JDateChooser ktDate;

    private String [] columnNames = new String [] {
            "STT", "Ngày bắt đầu", "Ngày kế thúc"};
    private Object data = new Object [][] {};

    public LichPhucKhaoView() {
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

        bdLabel = new JLabel("Bắt đầu");
        ktLabel = new JLabel("Kết thúc");

        bdDate = new JDateChooser();
        ktDate = new JDateChooser();

        bdDate.setPreferredSize(new Dimension(100, 20));
        ktDate.setPreferredSize(new Dimension(100, 20));

        studentTable.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        jScrollPaneStudentTable.setViewportView(studentTable);
        jScrollPaneStudentTable.setPreferredSize(new Dimension(650, 500));

        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setSize(1000, 700);
        panel.setLayout(layout);
        panel.add(jScrollPaneStudentTable);

        panel.add(addStudentBtn);
        panel.add(deleteStudentBtn);
        panel.add(clearBtn);

        panel.add(bdLabel);
        panel.add(ktLabel);

        panel.add(bdDate);
        panel.add(ktDate);

        layout.putConstraint(SpringLayout.WEST, bdLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, bdLabel, 10, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.NORTH, ktLabel, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, ktLabel, 10, SpringLayout.WEST, panel);

        layout.putConstraint(SpringLayout.WEST, bdDate, 90, SpringLayout.WEST, bdLabel);
        layout.putConstraint(SpringLayout.NORTH, bdDate, 0, SpringLayout.NORTH, bdLabel);

        layout.putConstraint(SpringLayout.WEST, ktDate, 90, SpringLayout.WEST, ktLabel);
        layout.putConstraint(SpringLayout.NORTH, ktDate, 0, SpringLayout.NORTH, ktLabel);

        layout.putConstraint(SpringLayout.WEST, jScrollPaneStudentTable, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneStudentTable, 10, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, addStudentBtn, 15, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addStudentBtn, 240, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, deleteStudentBtn, 60, SpringLayout.WEST, addStudentBtn);

        layout.putConstraint(SpringLayout.NORTH, clearBtn, 240, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, clearBtn, 60, SpringLayout.WEST, deleteStudentBtn);

        layout.putConstraint(SpringLayout.NORTH, deleteStudentBtn, 240, SpringLayout.NORTH, panel);

        this.add(panel);
        this.pack();
        this.setTitle("Lớp theo môn học");
        this.setSize(1000, 700);
        deleteStudentBtn.setEnabled(false);
        addStudentBtn.setEnabled(true);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void showListStudents(java.util.List<DanhsachlopEntity> list) {
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

    public void fillStudentFromSelectedRow() {
        int row = studentTable.getSelectedRow();

    }

    public void clearStudentInfo() {
         deleteStudentBtn.setEnabled(false);
        addStudentBtn.setEnabled(true);
    }

//    public DanhsachlopEntity getStudentInfo() {
//        try {
//
//        } catch (Exception e) {
//            showMessage(e.getMessage());
//        }
//        return null;
//    }
//    public void valueChanged(ListSelectionEvent e) {
   // }

    public void addAddStudentListener(ActionListener listener) {
        addStudentBtn.addActionListener(listener);
    }

    public void addDeleteStudentListener(ActionListener listener) {
        deleteStudentBtn.addActionListener(listener);
    }

    public void addClearListener(ActionListener listener) {
        clearBtn.addActionListener(listener);
    }


//    public void addSortListener(ActionListener listener) {
//        chonlopBox.addActionListener(listener);
//    }

//    public int getSortIndex(){
//        String item = chonlopBox.getSelectedItem().toString();
//        int index = 0;
//        do {
//            if (chonlop[index].equals(item)){
//                chonlopBox.setSelectedIndex(index);
//                break;
//            }else
//                index++;
//        }
//        while (true);
//        return index;
//    }

    public void sort(int index, StudentDao studentDao){

        String[] split = chonlop[index].split("-");
        String malop = split[0];
        String mamon = split[1];
        if (index == 0) {
            java.util.List<DanhsachlopEntity> sinhvien = svhocmonDao.readListStudents();
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
