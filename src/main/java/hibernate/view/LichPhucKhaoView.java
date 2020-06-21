package hibernate.view;

import com.toedter.calendar.JDateChooser;
import hibernate.dao.DotphuckhaoDao;
import hibernate.entity.DotphuckhaoEntity;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;

public class LichPhucKhaoView extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JButton addBtn;
    private JButton deleteBtn;
    private JButton clearBtn;
    private JScrollPane jScrollPaneTable;
    private JTable lichTable;

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
        addBtn = new JButton("Thêm");
        deleteBtn = new JButton("Xóa");
        clearBtn = new JButton("Nhập lại");
        jScrollPaneTable = new JScrollPane();
        lichTable = new JTable();

        bdLabel = new JLabel("Bắt đầu");
        ktLabel = new JLabel("Kết thúc");

        bdDate = new JDateChooser();
        ktDate = new JDateChooser();

        bdDate.setPreferredSize(new Dimension(100, 20));
        ktDate.setPreferredSize(new Dimension(100, 20));

        lichTable.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        jScrollPaneTable.setViewportView(lichTable);
        jScrollPaneTable.setPreferredSize(new Dimension(650, 500));

        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setSize(1000, 700);
        panel.setLayout(layout);
        panel.add(jScrollPaneTable);

        panel.add(addBtn);
        panel.add(deleteBtn);
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

        layout.putConstraint(SpringLayout.WEST, jScrollPaneTable, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneTable, 10, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, addBtn, 15, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addBtn, 240, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, deleteBtn, 60, SpringLayout.WEST, addBtn);

        layout.putConstraint(SpringLayout.NORTH, clearBtn, 240, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, clearBtn, 60, SpringLayout.WEST, deleteBtn);

        layout.putConstraint(SpringLayout.NORTH, deleteBtn, 240, SpringLayout.NORTH, panel);

        this.add(panel);
        this.pack();
        this.setSize(1000, 700);
        deleteBtn.setEnabled(false);
        addBtn.setEnabled(true);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void showLich(java.util.List<DotphuckhaoEntity> list) {
        int size = list.size();

        Object [][] lich = new Object[size][6];
        for (int i = 0; i < size; i++) {
            lich[i][0] = i + 1;
            lich[i][1] = list.get(i).getNgaybatdau();
            lich[i][2] = list.get(i).getNgayketthuc();
        }
        lichTable.setModel(new DefaultTableModel(lich, columnNames));
    }

    public void fillFromSelectedRow() {
        int row = lichTable.getSelectedRow();
        System.out.println("dong: " + row);
        if (row < 0)
            return;
        bdDate.setDate(Date.valueOf(lichTable.getModel().getValueAt(row, 1).toString().trim()));
        ktDate.setDate(Date.valueOf(lichTable.getModel().getValueAt(row, 2).toString().trim()));
        deleteBtn.setEnabled(true);
        addBtn.setEnabled(false);
    }

    public void clearInfo() {
        bdDate.setDate(null);
        ktDate.setDate(null);
        deleteBtn.setEnabled(false);
        addBtn.setEnabled(true);
    }

    public void addAddStudentListener(ActionListener listener) {
        addBtn.addActionListener(listener);
    }

    public void addDeleteStudentListener(ActionListener listener) {
        deleteBtn.addActionListener(listener);
    }

    public void addClearListener(ActionListener listener) {
        clearBtn.addActionListener(listener);
    }


    public void addListSelectionListener(ListSelectionListener listener) {
        lichTable.getSelectionModel().addListSelectionListener(listener);
    }

    public void actionPerformed(ActionEvent e) {

    }

    public DotphuckhaoEntity getInfo() {
        try {
            DotphuckhaoEntity dot = new DotphuckhaoEntity();
            Date bd = new Date(bdDate.getDate().getTime());
            Date kt = new Date(ktDate.getDate().getTime());
            System.out.println(bd + "    " + kt);
            dot.setNgaybatdau(bd);
            dot.setNgayketthuc(kt);
            return dot;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }

    public DotphuckhaoEntity getInfoFromSelectedRow() {
        int row = lichTable.getSelectedRow();
        try {
            DotphuckhaoEntity dot = new DotphuckhaoEntity();
            Date bd = Date.valueOf(lichTable.getModel().getValueAt(row, 1).toString().trim());
            Date kt = Date.valueOf(lichTable.getModel().getValueAt(row, 2).toString().trim());
            dot.setNgaybatdau(bd);
            dot.setNgayketthuc(kt);
            return dot;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }
}
