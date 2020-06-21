package hibernate.view;

import hibernate.entity.DonphuckhaoEntity;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SinhVienPhucKhaoView extends JFrame implements ActionListener {
    private final String [] cotdiem = {"Giữa kỳ", "Cuối kỳ", "Điểm khác", "Điểm tổng"};
    private final String [] trangthai = {"Chưa xem", "Đã cập nhật điểm", "Không cập nhật điểm"};
    private String [] dotPK;
    private String [] monhoc;

    private static final long serialVersionUID = 1L;
    private JButton addBtn;
    private JButton clearBtn;
    private JScrollPane jScrollPaneTable;
    private JTable phuckhaoTable;

    private JLabel diemTruocLabel;
    private JLabel dotPKLabel;
    private JLabel monhocPKLabel;
    private JLabel lydoPKLabel;
    private JLabel cotdiemPKLabel;
    private JLabel mssvLabel;
    private JLabel trangthaiLabel;

    private JComboBox dotPKBox;
    private JComboBox monhocBox;

    public void setMssvField(String text) {
        this.mssvField.setText(text);
    }

    private JComboBox cotdiemBox;

    private JTextField diemTruocField;
    private JTextField mssvField;

    private JTextArea lydoArea;

    private String [] columnNames = new String [] {
            "STT", "MSSV","Họ tên", "Môn học", "Cột điểm", "Điểm mong muốn", "Điểm sau phúc khảo", "Lý do", "Trạng thái", "Đợt"};
    private Object data = new Object [][] {};

    public SinhVienPhucKhaoView() {
    }

    public void setDotPK(String[] dotPK) {
        this.dotPK = dotPK;
    }

    public void setMonhoc(String[] monhoc) {
        this.monhoc = monhoc;
    }

    public void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // khởi tạo các phím chức năng
        addBtn = new JButton("Thêm");
        clearBtn = new JButton("Nhập lại");
        addBtn.setPreferredSize(new Dimension(100, 30));
        clearBtn.setPreferredSize(new Dimension(100, 30));
        jScrollPaneTable = new JScrollPane();
        phuckhaoTable = new JTable();

        dotPKLabel = new JLabel("Đợt phúc khảo");
        monhocPKLabel = new JLabel("Môn học");
        lydoPKLabel = new JLabel("Lý do");
        cotdiemPKLabel = new JLabel("Cột điểm");
        diemTruocLabel = new JLabel("Điểm mong muốn");
        mssvLabel = new JLabel("MSSV");
        trangthaiLabel = new JLabel("Trạng thái");

        cotdiemBox = new JComboBox(cotdiem);
        dotPKBox = new JComboBox(dotPK);
        monhocBox = new JComboBox(monhoc);
        dotPKBox.setPreferredSize(new Dimension(200, 20));
        monhocBox.setPreferredSize(new Dimension(200, 20));
        cotdiemBox.setPreferredSize(new Dimension(200, 20));

        diemTruocField = new JTextField(20);
        mssvField = new JTextField(20);
        mssvField.setEditable(false);
        lydoArea = new JTextArea(5, 20);
        phuckhaoTable.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        jScrollPaneTable.setViewportView(phuckhaoTable);
        jScrollPaneTable.setPreferredSize(new Dimension(940, 330));

        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setSize(1000, 700);
        panel.setLayout(layout);
        panel.add(jScrollPaneTable);

        panel.add(addBtn);
        panel.add(clearBtn);

        panel.add(dotPKLabel);
        panel.add(monhocPKLabel);
        panel.add(lydoPKLabel);
        panel.add(cotdiemPKLabel);
        panel.add(diemTruocLabel);
        panel.add(mssvLabel);

        panel.add(dotPKBox);
        panel.add(monhocBox);
        panel.add(cotdiemBox);

        panel.add(lydoArea);

        panel.add(mssvField);
        panel.add(diemTruocField);

        layout.putConstraint(SpringLayout.WEST, jScrollPaneTable, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneTable, 300, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, addBtn, 850, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addBtn, 50, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.NORTH, clearBtn, 50, SpringLayout.NORTH, addBtn);
        layout.putConstraint(SpringLayout.WEST, clearBtn, 0, SpringLayout.WEST, addBtn);

        layout.putConstraint(SpringLayout.WEST, mssvLabel, 50, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, mssvLabel, 50, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, mssvField, 100, SpringLayout.WEST, mssvLabel);
        layout.putConstraint(SpringLayout.NORTH, mssvField, 0, SpringLayout.NORTH, mssvLabel);

        layout.putConstraint(SpringLayout.WEST, dotPKLabel, 0, SpringLayout.WEST, mssvLabel);
        layout.putConstraint(SpringLayout.NORTH, dotPKLabel, 50, SpringLayout.NORTH, mssvLabel);

        layout.putConstraint(SpringLayout.WEST, dotPKBox, 100, SpringLayout.WEST, dotPKLabel);
        layout.putConstraint(SpringLayout.NORTH, dotPKBox, 0, SpringLayout.NORTH, dotPKLabel);

        layout.putConstraint(SpringLayout.WEST, monhocPKLabel, 0, SpringLayout.WEST, dotPKLabel);
        layout.putConstraint(SpringLayout.NORTH, monhocPKLabel, 50, SpringLayout.NORTH, dotPKLabel);

        layout.putConstraint(SpringLayout.WEST, monhocBox, 100, SpringLayout.WEST, monhocPKLabel);
        layout.putConstraint(SpringLayout.NORTH, monhocBox, 0, SpringLayout.NORTH, monhocPKLabel);

        layout.putConstraint(SpringLayout.WEST, cotdiemPKLabel, 450, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, cotdiemPKLabel, 0, SpringLayout.NORTH, mssvLabel);

        layout.putConstraint(SpringLayout.WEST, cotdiemBox, 120, SpringLayout.WEST, cotdiemPKLabel);
        layout.putConstraint(SpringLayout.NORTH, cotdiemBox, 0, SpringLayout.NORTH, cotdiemPKLabel);

        layout.putConstraint(SpringLayout.WEST, diemTruocLabel, 0, SpringLayout.WEST, cotdiemPKLabel);
        layout.putConstraint(SpringLayout.NORTH, diemTruocLabel, 50, SpringLayout.NORTH, cotdiemPKLabel);

        layout.putConstraint(SpringLayout.WEST, diemTruocField, 120, SpringLayout.WEST, diemTruocLabel);
        layout.putConstraint(SpringLayout.NORTH, diemTruocField, 0, SpringLayout.NORTH, diemTruocLabel);

        layout.putConstraint(SpringLayout.WEST, lydoPKLabel, 0, SpringLayout.WEST, diemTruocLabel);
        layout.putConstraint(SpringLayout.NORTH, lydoPKLabel, 50, SpringLayout.NORTH, diemTruocLabel);

        layout.putConstraint(SpringLayout.WEST, lydoArea, 120, SpringLayout.WEST, lydoPKLabel);
        layout.putConstraint(SpringLayout.NORTH, lydoArea, 0, SpringLayout.NORTH, lydoPKLabel);

        this.add(panel);
        this.pack();
        this.setSize(1000, 700);
        addBtn.setEnabled(true);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void showPhuckhao(List<DonphuckhaoEntity> list) {
        int size = list.size();

        Object [][] lich = new Object[size][10];
        for (int i = 0; i < size; i++) {
            lich[i][0] = i + 1;
            lich[i][1] = list.get(i).getSinhvien();
            lich[i][2] = list.get(i).getHoten();
            lich[i][3] = list.get(i).getMonhoc();
            lich[i][4] = cotdiem[list.get(i).getCotdiem()];
            lich[i][5] = list.get(i).getDiemtruocpk();
            lich[i][6] = list.get(i).getDiemsaupk();
            lich[i][7] = list.get(i).getLydo();
            lich[i][8] = list.get(i).getTrangthai();
            lich[i][9] = dotPK[list.get(i).getDotphuckhao()];

        }
        phuckhaoTable.setModel(new DefaultTableModel(lich, columnNames));
    }

    public void fillFromSelectedRow() {
        int row = phuckhaoTable.getSelectedRow();
        if (row < 0)
            return;
        mssvField.setText(phuckhaoTable.getModel().getValueAt(row, 1).toString());
        monhocBox.setSelectedItem(phuckhaoTable.getModel().getValueAt(row, 2).toString());
        cotdiemBox.setSelectedItem(phuckhaoTable.getModel().getValueAt(row, 3).toString());
        diemTruocField.setText(phuckhaoTable.getModel().getValueAt(row, 4).toString().trim());
        lydoArea.setText(phuckhaoTable.getModel().getValueAt(row, 6).toString());
        dotPKBox.setSelectedItem(phuckhaoTable.getModel().getValueAt(row, 8).toString());
        addBtn.setEnabled(false);
    }

    public void clearInfo() {
        diemTruocField.setText("");
        if (dotPKBox.getItemCount() > 0) {
            dotPKBox.setSelectedIndex(0);
        }
        if (monhocBox.getItemCount() > 0) {
            monhocBox.setSelectedIndex(0);
        }
        if (cotdiemBox.getItemCount() > 0) {
            cotdiemBox.setSelectedIndex(0);
        }
        lydoArea.setText("");
        addBtn.setEnabled(true);
    }

    public void addAddListener(ActionListener listener) {
        addBtn.addActionListener(listener);
    }

    public void addClearListener(ActionListener listener) {
        clearBtn.addActionListener(listener);
    }

    public void addListSelectionListener(ListSelectionListener listener) {
        phuckhaoTable.getSelectionModel().addListSelectionListener(listener);
    }

    public void actionPerformed(ActionEvent e) {
    }

    public DonphuckhaoEntity getInfoFromSelectedRow() {
        try {
            DonphuckhaoEntity donPK = new DonphuckhaoEntity();
            donPK.setSinhvien(mssvField.getText());
            donPK.setMonhoc(monhocBox.getSelectedItem().toString());
            donPK.setCotdiem(cotdiemBox.getSelectedIndex());
            donPK.setDiemtruocpk(Double.parseDouble(diemTruocField.getText()));
            donPK.setTrangthai(trangthai[0]);
            donPK.setDotphuckhao(dotPKBox.getSelectedIndex());
            donPK.setLydo(lydoArea.getText());

            return donPK;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }
}
