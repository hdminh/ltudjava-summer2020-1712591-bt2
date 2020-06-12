package hibernate.view;

import hibernate.dao.LophocDao;
import hibernate.entity.LophocEntity;
import hibernate.entity.MonhocEntity;
import hibernate.entity.SinhvienEntity;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ThoiKhoaBieuView extends JFrame {
    LophocDao lophocDao = new LophocDao();

    String [] gioitinh = {"Nam", "Nữ"};
    String [] lop;

    private static final long serialVersionUID = 1L;
    private JButton addMonhocBtn;
    private JButton editMonhocBtn;
    private JButton deleteMonhocBtn;
    private JButton clearBtn;
    private JButton importBtn;
    private JScrollPane jScrollPaneMonhocTable;
    private JTable monhocTable;

    private JLabel mamonLabel;
    private JLabel tenmonLabel;
    private JLabel phongLabel;
    private JLabel lopLabel;

    private JTextField mamonField;
    private JTextField tenmonField;
    private JTextField phongField;
    private JComboBox lopBox;

    // định nghĩa các cột của bảng student
    private String [] columnNames = new String [] {
            "STT", "Mã Môn", "Tên Môn", "Phòng học", "Lớp"};
    // định nghĩa dữ liệu mặc định của bẳng student là rỗng
    private Object data = new Object [][] {};

    public ThoiKhoaBieuView() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // khởi tạo các phím chức năng
        addMonhocBtn = new JButton("Thêm");
        editMonhocBtn = new JButton("Sửa");
        deleteMonhocBtn = new JButton("Xóa");
        clearBtn = new JButton("Nhập lại");
        importBtn = new JButton("Import");
        // khởi tạo bảng student
        jScrollPaneMonhocTable = new JScrollPane();
        monhocTable = new JTable();

        // khởi tạo các label
        mamonLabel = new JLabel("Mã môn");
        tenmonLabel = new JLabel("Tên môn");
        phongLabel = new JLabel("Phòng học");;
        lopLabel = new JLabel("Lớp");

        lop = lophocDao.readMaLop().toArray(new String[0]);
        // khởi tạo các trường nhập dữ liệu cho student
        mamonField = new JTextField(10);
        tenmonField = new JTextField(15);
        phongField = new JTextField(10);
        lopBox = new JComboBox(lop);
        // cài đặt các cột và data cho bảng môn học
        monhocTable.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        jScrollPaneMonhocTable.setViewportView(monhocTable);
        jScrollPaneMonhocTable.setPreferredSize(new Dimension(480, 300));

        // tạo spring layout
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setSize(800, 420);
        panel.setLayout(layout);
        panel.add(jScrollPaneMonhocTable);

        panel.add(addMonhocBtn);
        panel.add(editMonhocBtn);
        panel.add(deleteMonhocBtn);
        panel.add(clearBtn);
        panel.add(importBtn);

        panel.add(mamonLabel);
        panel.add(tenmonLabel);
        panel.add(phongLabel);
        panel.add(lopLabel);

        panel.add(mamonField);
        panel.add(tenmonField);
        panel.add(phongField);
        panel.add(lopBox);

        layout.putConstraint(SpringLayout.WEST, mamonLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, mamonLabel, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, tenmonLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tenmonLabel, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, phongLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, phongLabel, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, lopLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, lopLabel, 100, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, mamonField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, mamonField, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, tenmonField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tenmonField, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, phongField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, phongField, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, lopBox, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, lopBox, 100, SpringLayout.NORTH, panel);


        layout.putConstraint(SpringLayout.WEST, jScrollPaneMonhocTable, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneMonhocTable, 10, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, addMonhocBtn, 15, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addMonhocBtn, 240, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, editMonhocBtn, 70, SpringLayout.WEST, addMonhocBtn);
        layout.putConstraint(SpringLayout.NORTH, editMonhocBtn, 240, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, deleteMonhocBtn, 60, SpringLayout.WEST, editMonhocBtn);

        layout.putConstraint(SpringLayout.NORTH, clearBtn, 240, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, clearBtn, 60, SpringLayout.WEST, deleteMonhocBtn);

        layout.putConstraint(SpringLayout.NORTH, deleteMonhocBtn, 240, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, importBtn, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, importBtn, 330, SpringLayout.NORTH, panel);

        this.add(panel);
        this.pack();
        this.setTitle("Thời khóa biểu");
        this.setSize(800, 420);
        // disable Edit and Delete buttons
        editMonhocBtn.setEnabled(false);
        deleteMonhocBtn.setEnabled(false);
        // enable Add button
        addMonhocBtn.setEnabled(true);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void showListMonHoc(List<MonhocEntity> list) {
        int size = list.size();

        Object [][] students = new Object[size][5];
        for (int i = 0; i < size; i++) {
            students[i][0] = (i + 1);
            students[i][1] = list.get(i).getMamon();
            students[i][2] = list.get(i).getTenmonhoc();
            students[i][3] = list.get(i).getPhonghoc();
            students[i][4] = list.get(i).getLophoc().getMalop();
        }
        monhocTable.setModel(new DefaultTableModel(students, columnNames));
    }

    public void fillTKBFromSelectedRow() {
        int row = monhocTable.getSelectedRow();
        if (row >= 0) {
            mamonField.setText(monhocTable.getModel().getValueAt(row, 1).toString());
            tenmonField.setText(monhocTable.getModel().getValueAt(row, 2).toString());
            phongField.setText(monhocTable.getModel().getValueAt(row, 3).toString());
            int index = 0;
            String lopStr = monhocTable.getModel().getValueAt(row, 4).toString().trim();
            do {
                if (lop[index].equals(lopStr)){
                    lopBox.setSelectedIndex(index);
                    break;
                }else
                    index++;
            }
            while (true);
            // enable Edit and Delete buttons
            editMonhocBtn.setEnabled(true);
            deleteMonhocBtn.setEnabled(true);
            // disable Add button
            addMonhocBtn.setEnabled(false);
        }
    }

    public void clearMonhocInfo() {
        mamonField.setText("");
        tenmonField.setText("");
        phongField.setText("");
        // disable Edit and Delete buttons
        editMonhocBtn.setEnabled(false);
        deleteMonhocBtn.setEnabled(false);
        // enable Add button
        addMonhocBtn.setEnabled(true);
    }

    public void showMonhoc(MonhocEntity monhoc) {
        mamonField.setText(monhoc.getMamon());
        tenmonField.setText(monhoc.getTenmonhoc());
        phongField.setText(monhoc.getPhonghoc());
        int index = 0;
        String lopStr = monhoc.getLophoc().getMalop().trim();
        do {
            if (lop[index].equals(lopStr)){
                lopBox.setSelectedIndex(index);
                break;
            }else
                index++;
        }
        while (true);

        editMonhocBtn.setEnabled(true);
        deleteMonhocBtn.setEnabled(true);
        addMonhocBtn.setEnabled(false);
    }

    public MonhocEntity getMonhocInfo() {

        try {
            MonhocEntity monhoc = new MonhocEntity();
            if (mamonField.getText() != null && !"".equals(mamonField.getText().trim())) {
                monhoc.setMamon(mamonField.getText());
            }
            monhoc.setTenmonhoc(tenmonField.getText().trim());
            monhoc.setPhonghoc(phongField.getText().trim());
            LophocEntity lophocEntity = new LophocEntity(lopBox.getSelectedItem().toString());
            monhoc.setLophoc(lophocEntity);
            return monhoc;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }

    public void valueChanged(ListSelectionEvent e) {
    }

    public void addAddMonhocListener(ActionListener listener) {
        addMonhocBtn.addActionListener(listener);
    }

    public void addEditMonhocListener(ActionListener listener) {
        editMonhocBtn.addActionListener(listener);
    }

    public void addDeleteMonhocListener(ActionListener listener) {
        deleteMonhocBtn.addActionListener(listener);
    }

    public void addClearListener(ActionListener listener) {
        clearBtn.addActionListener(listener);
    }

    public void addImportListener(ActionListener listener) {
        importBtn.addActionListener(listener);
    }


    public void addListMonhocSelectionListener(ListSelectionListener listener) {
        monhocTable.getSelectionModel().addListSelectionListener(listener);
    }

    public void actionPerformed(ActionEvent e) {

    }
}
