package hibernate.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoiMatKhauView extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JLabel oldLabel;
    private JLabel newLabel;
    private JLabel retypeLabel;
    private JPasswordField oldField;
    private JPasswordField newField;
    private JPasswordField retypeField;
    private JButton commitBtn;

    public DoiMatKhauView(){
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        oldLabel = new JLabel("Mật khẩu cũ");
        newLabel = new JLabel("Mật khẩu mới");
        retypeLabel = new JLabel("Nhập lại");
        oldField = new JPasswordField(20);
        newField = new JPasswordField(20);
        retypeField = new JPasswordField(20);
        commitBtn = new JButton();
        commitBtn.setText("Xác nhận");

        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();

        panel.setLayout(layout);
        panel.add(oldLabel);
        panel.add(newLabel);
        panel.add(retypeLabel);

        panel.add(oldField);
        panel.add(newField);
        panel.add(retypeField);

        panel.add(commitBtn);

        layout.putConstraint(SpringLayout.WEST, oldLabel, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, oldLabel, 80, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, newLabel, 0, SpringLayout.WEST, oldLabel);
        layout.putConstraint(SpringLayout.NORTH, newLabel, 40, SpringLayout.NORTH, oldLabel);

        layout.putConstraint(SpringLayout.WEST, retypeLabel, 0, SpringLayout.WEST, newLabel);
        layout.putConstraint(SpringLayout.NORTH, retypeLabel, 40, SpringLayout.NORTH, newLabel);

        layout.putConstraint(SpringLayout.WEST, oldField, 80, SpringLayout.WEST, oldLabel);
        layout.putConstraint(SpringLayout.NORTH, oldField, 0, SpringLayout.NORTH, oldLabel);

        layout.putConstraint(SpringLayout.WEST, newField, 80, SpringLayout.WEST, newLabel);
        layout.putConstraint(SpringLayout.NORTH, newField, 0, SpringLayout.NORTH, newLabel);

        layout.putConstraint(SpringLayout.WEST, retypeField, 80, SpringLayout.WEST, retypeLabel);
        layout.putConstraint(SpringLayout.NORTH, retypeField, 0, SpringLayout.NORTH, retypeLabel);

        layout.putConstraint(SpringLayout.WEST, commitBtn, 200, SpringLayout.WEST, retypeLabel);
        layout.putConstraint(SpringLayout.NORTH, commitBtn, 40, SpringLayout.NORTH, retypeLabel);


        this.add(panel);
        this.getRootPane().setDefaultButton(commitBtn);
        this.pack();
        this.setTitle("Đổi mật khẩu");
        this.setSize(400, 300);
        this.setResizable(false);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public String getOldField(){
        return String.copyValueOf(oldField.getPassword()).trim();
    }

    public String getNewField(){
        return String.copyValueOf(newField.getPassword()).trim();
    }

    public String getRetypeField(){
        return String.copyValueOf(retypeField.getPassword()).trim();
    }

    public void addCommitListener(ActionListener listener) {
        commitBtn.addActionListener(listener);
    }

    public void actionPerformed(ActionEvent e) {
    }

    public void clearInfo() {
        oldField.setText("");
        newField.setText("");
        retypeField.setText("");
    }
}
