package hibernate.view;


import hibernate.dao.UserDao;
import hibernate.entity.UserEntity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class LoginView extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JTextField usernameField;
    private JButton loginBtn;

    UserDao userDao = new UserDao();
    public LoginView(){
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        usernameLabel = new JLabel("Username");
        passwordLabel = new JLabel("Password");
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginBtn = new JButton();

        loginBtn.setText("Login");
        loginBtn.addActionListener(this);

        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();

        panel.setSize(400, 300);
        panel.setLayout(layout);
        panel.add(usernameLabel);
        panel.add(passwordLabel);
        panel.add(usernameField);
        panel.add(passwordField);
        panel.add(loginBtn);

        layout.putConstraint(SpringLayout.WEST, usernameLabel, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, usernameLabel, 80, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, passwordLabel, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, passwordLabel, 105, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, usernameField, 80, SpringLayout.WEST, usernameLabel);
        layout.putConstraint(SpringLayout.NORTH, usernameField, 80, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, passwordField, 80, SpringLayout.WEST, passwordLabel);
        layout.putConstraint(SpringLayout.NORTH, passwordField, 105, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, loginBtn, 80, SpringLayout.WEST, passwordLabel);
        layout.putConstraint(SpringLayout.NORTH, loginBtn, 130, SpringLayout.NORTH, panel);

        this.add(panel);
        this.pack();
        this.setTitle("Login");
        this.setSize(400, 300);
        this.setResizable(false);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public UserEntity getUser() {
        return new UserEntity(usernameField.getText().trim(), String.copyValueOf(passwordField.getPassword()).trim());
    }

    public void addLoginListener(ActionListener listener) {
        loginBtn.addActionListener(listener);
    }


    public void actionPerformed(ActionEvent e) {

    }
}
