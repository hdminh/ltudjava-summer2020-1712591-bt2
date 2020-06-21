package hibernate.view;

import hibernate.entity.DonphuckhaoEntity;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DonPhucKhaoView extends JFrame implements ActionListener {
    private final String [] cotdiem = {"Giữa kỳ", "Cuối kỳ", "Điểm khác", "Điểm tổng"};
    private final String [] trangthai = {"Chưa xem", "Đã cập nhật điểm", "Không cập nhật điểm"};
    private String [] dotPK;
    private String [] monhoc;

    private static final long serialVersionUID = 1L;
    private JButton edtBtn;
    private JButton clearBtn;
    private JScrollPane jScrollPaneTable;
    private JTable phuckhaoTable;

    private JLabel diemTruocLabel;
    private JLabel diemSauLabel;
    private JLabel dotPKLabel;
    private JLabel monhocPKLabel;

    public void setDotPK(String[] dotPK) {
        this.dotPK = dotPK;
    }

    public void setMonhoc(String[] monhoc) {
        this.monhoc = monhoc;
    }

    private JLabel lydoPKLabel;
    private JLabel cotdiemPKLabel;
    private JLabel mssvLabel;
    private JLabel trangthaiLabel;

    private JComboBox trangthaiBox;

    private JTextField diemTruocField;
    private JTextField diemSauField;
    private JTextField mssvField;
    private JTextField dotPKField;
    private JTextField monhocField;
    private JTextField cotdiemField;

    private JTextArea lydoArea;

    private String [] columnNames = new String [] {
            "STT", "MSSV", "Môn học", "Cột điểm", "Điểm mong muốn", "Điểm sau phúc khảo", "Lý do", "Trạng thái", "Đợt"};
    private Object data = new Object [][] {};

    public DonPhucKhaoView() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // khởi tạo các phím chức năng
        edtBtn = new JButton("Cập nhật");
        clearBtn = new JButton("Nhập lại");
        edtBtn.setPreferredSize(new Dimension(100, 30));
        clearBtn.setPreferredSize(new Dimension(100, 30));
        jScrollPaneTable = new JScrollPane();
        phuckhaoTable = new JTable();

        dotPKLabel = new JLabel("Đợt phúc khảo");
        monhocPKLabel = new JLabel("Môn học");
        lydoPKLabel = new JLabel("Lý do");
        cotdiemPKLabel = new JLabel("Cột điểm");
        diemTruocLabel = new JLabel("Điểm mong muốn");
        diemSauLabel = new JLabel("Điểm sau phúc khảo");
        mssvLabel = new JLabel("MSSV");
        trangthaiLabel = new JLabel("Trạng thái");

        trangthaiBox = new JComboBox(trangthai);
        trangthaiBox.setPreferredSize(new Dimension(200, 20));

        diemTruocField = new JTextField(20);
        cotdiemField = new JTextField(20);
        diemSauField = new JTextField(20);
        monhocField = new JTextField(20);
        dotPKField = new JTextField(20);
        mssvField = new JTextField(20);

        diemTruocField.setEditable(false);
        cotdiemField.setEditable(false);
        monhocField.setEditable(false);
        dotPKField.setEditable(false);
        mssvField.setEditable(false);

        lydoArea = new JTextArea(5, 20);
        lydoArea.setEditable(false);
        phuckhaoTable.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        jScrollPaneTable.setViewportView(phuckhaoTable);
        jScrollPaneTable.setPreferredSize(new Dimension(940, 330));

        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setSize(1000, 700);
        panel.setLayout(layout);
        panel.add(jScrollPaneTable);

        panel.add(clearBtn);
        panel.add(edtBtn);

        panel.add(cotdiemPKLabel);
        panel.add(trangthaiLabel);
        panel.add(diemTruocLabel);
        panel.add(monhocPKLabel);
        panel.add(diemSauLabel);
        panel.add(lydoPKLabel);
        panel.add(dotPKLabel);
        panel.add(mssvLabel);

        panel.add(dotPKField);
        panel.add(monhocField);
        panel.add(cotdiemField);
        panel.add(trangthaiBox);

        panel.add(diemTruocField);
        panel.add(diemSauField);
        panel.add(mssvField);

        panel.add(lydoArea);

        layout.putConstraint(SpringLayout.WEST, jScrollPaneTable, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneTable, 300, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, edtBtn, 850, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, edtBtn, 50, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.NORTH, clearBtn, 50, SpringLayout.NORTH, edtBtn);
        layout.putConstraint(SpringLayout.WEST, clearBtn, 0, SpringLayout.WEST, edtBtn);

        layout.putConstraint(SpringLayout.WEST, mssvLabel, 50, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, mssvLabel, 50, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, mssvField, 100, SpringLayout.WEST, mssvLabel);
        layout.putConstraint(SpringLayout.NORTH, mssvField, 0, SpringLayout.NORTH, mssvLabel);

        layout.putConstraint(SpringLayout.WEST, dotPKLabel, 0, SpringLayout.WEST, mssvLabel);
        layout.putConstraint(SpringLayout.NORTH, dotPKLabel, 50, SpringLayout.NORTH, mssvLabel);

        layout.putConstraint(SpringLayout.WEST, dotPKField, 100, SpringLayout.WEST, dotPKLabel);
        layout.putConstraint(SpringLayout.NORTH, dotPKField, 0, SpringLayout.NORTH, dotPKLabel);

        layout.putConstraint(SpringLayout.WEST, monhocPKLabel, 0, SpringLayout.WEST, dotPKLabel);
        layout.putConstraint(SpringLayout.NORTH, monhocPKLabel, 50, SpringLayout.NORTH, dotPKLabel);

        layout.putConstraint(SpringLayout.WEST, monhocField, 100, SpringLayout.WEST, monhocPKLabel);
        layout.putConstraint(SpringLayout.NORTH, monhocField, 0, SpringLayout.NORTH, monhocPKLabel);

        layout.putConstraint(SpringLayout.WEST, cotdiemPKLabel, 0, SpringLayout.WEST, monhocPKLabel);
        layout.putConstraint(SpringLayout.NORTH, cotdiemPKLabel, 50, SpringLayout.NORTH, monhocPKLabel);

        layout.putConstraint(SpringLayout.WEST, cotdiemField, 100, SpringLayout.WEST, cotdiemPKLabel);
        layout.putConstraint(SpringLayout.NORTH, cotdiemField, 0, SpringLayout.NORTH, cotdiemPKLabel);

        layout.putConstraint(SpringLayout.WEST, trangthaiLabel, 0, SpringLayout.WEST, cotdiemPKLabel);
        layout.putConstraint(SpringLayout.NORTH, trangthaiLabel, 50, SpringLayout.NORTH, cotdiemPKLabel);

        layout.putConstraint(SpringLayout.WEST, trangthaiBox, 100, SpringLayout.WEST, trangthaiLabel);
        layout.putConstraint(SpringLayout.NORTH, trangthaiBox, 0, SpringLayout.NORTH, trangthaiLabel);

        layout.putConstraint(SpringLayout.WEST, diemTruocLabel, 450, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, diemTruocLabel, 0, SpringLayout.NORTH, mssvLabel);

        layout.putConstraint(SpringLayout.WEST, diemTruocField, 150, SpringLayout.WEST, diemTruocLabel);
        layout.putConstraint(SpringLayout.NORTH, diemTruocField, 0, SpringLayout.NORTH, diemTruocLabel);

        layout.putConstraint(SpringLayout.WEST, diemSauLabel, 0, SpringLayout.WEST, diemTruocLabel);
        layout.putConstraint(SpringLayout.NORTH, diemSauLabel, 50, SpringLayout.NORTH, diemTruocLabel);

        layout.putConstraint(SpringLayout.WEST, diemSauField, 150, SpringLayout.WEST, diemSauLabel);
        layout.putConstraint(SpringLayout.NORTH, diemSauField, 0, SpringLayout.NORTH, diemSauLabel);

        layout.putConstraint(SpringLayout.WEST, lydoPKLabel, 0, SpringLayout.WEST, diemSauLabel);
        layout.putConstraint(SpringLayout.NORTH, lydoPKLabel, 50, SpringLayout.NORTH, diemSauLabel);

        layout.putConstraint(SpringLayout.WEST, lydoArea, 150, SpringLayout.WEST, lydoPKLabel);
        layout.putConstraint(SpringLayout.NORTH, lydoArea, 0, SpringLayout.NORTH, lydoPKLabel);

        this.add(panel);
        this.pack();
        this.setSize(1000, 700);
        edtBtn.setEnabled(false);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void showPhuckhao(List<DonphuckhaoEntity> list) {
        int size = list.size();

        Object [][] lich = new Object[size][9];
        for (int i = 0; i < size; i++) {
            lich[i][0] = i + 1;
            lich[i][1] = list.get(i).getSinhvien();
            lich[i][2] = list.get(i).getMonhoc();
            lich[i][3] = cotdiem[list.get(i).getCotdiem()];
            lich[i][4] = list.get(i).getDiemtruocpk();
            lich[i][5] = list.get(i).getDiemsaupk();
            lich[i][6] = list.get(i).getLydo();
            lich[i][7] = list.get(i).getTrangthai();
            lich[i][8] = dotPK[list.get(i).getDotphuckhao()];

        }
        phuckhaoTable.setModel(new DefaultTableModel(lich, columnNames));
    }

    public void fillFromSelectedRow() {
        int row = phuckhaoTable.getSelectedRow();
        if (row < 0)
            return;
        mssvField.setText(phuckhaoTable.getModel().getValueAt(row, 1).toString());
        monhocField.setText(phuckhaoTable.getModel().getValueAt(row, 2).toString());
        cotdiemField.setText(phuckhaoTable.getModel().getValueAt(row, 3).toString());
        diemTruocField.setText(phuckhaoTable.getModel().getValueAt(row, 4).toString().trim());
        if (phuckhaoTable.getModel().getValueAt(row, 5) != null) {
            diemSauField.setText(phuckhaoTable.getModel().getValueAt(row, 5).toString().trim());
        }
        else {
            diemSauField.setText("");
        }
        lydoArea.setText(phuckhaoTable.getModel().getValueAt(row, 6).toString());
        trangthaiBox.setSelectedItem(phuckhaoTable.getModel().getValueAt(row, 7));
        dotPKField.setText(phuckhaoTable.getModel().getValueAt(row, 8).toString());
        edtBtn.setEnabled(true);
    }

    public void clearInfo() {
        mssvField.setText("");
        monhocField.setText("");
        cotdiemField.setText("");
        diemTruocField.setText("");
        diemSauField.setText("");
        lydoArea.setText("");
        trangthaiBox.setSelectedIndex(0);
        dotPKField.setText("");
        edtBtn.setEnabled(false);
    }

    public void addEditStudentListener(ActionListener listener) {
        edtBtn.addActionListener(listener);
    }

    public void addClearListener(ActionListener listener) {
        clearBtn.addActionListener(listener);
    }

    public void addListSelectionListener(ListSelectionListener listener) {
        phuckhaoTable.getSelectionModel().addListSelectionListener(listener);
    }

    public void actionPerformed(ActionEvent e) {
    }

    public DonphuckhaoEntity getInfo() {
        try {
            DonphuckhaoEntity donPK = new DonphuckhaoEntity();
            donPK.setSinhvien(mssvField.getText());
            donPK.setMonhoc(monhocField.getText());
            int index = 0;
            while (true) {
                if (cotdiemField.getText().equals(cotdiem[index])) {
                    donPK.setCotdiem(index);
                    break;
                }
                index++;
            }
            donPK.setDiemtruocpk(Double.parseDouble(diemTruocField.getText()));
            donPK.setLydo(lydoArea.getText());
            donPK.setDiemsaupk(Double.parseDouble(diemSauField.getText()));
            donPK.setTrangthai(trangthaiBox.getSelectedItem().toString());
            index = 0;
            while (true) {
                if (dotPKField.getText().equals(dotPK[index])) {
                    donPK.setDotphuckhao(index);
                    break;
                }
                index++;
            }
            return donPK;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }
}
