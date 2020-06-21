package hibernate.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SinhVienView extends JFrame implements ActionListener {
    JTabbedPane pane;
    JButton logoutBtn;

    public SinhVienView(){
        initComponents();
    }

    private void initComponents() {
        pane = new JTabbedPane();
        logoutBtn = new JButton("Đăng xuất");
        this.add(pane, BorderLayout.CENTER);
        this.pack();
        this.setTitle("Portal HCMUS");
        this.setSize(1000, 700);
        this.setResizable(false);
    }

    public void addLogout(){
        pane.add(new JPanel(), 3);
        pane.setTabComponentAt(3, logoutBtn);
    }

    public void addTab(String text, Container container){
        pane.addTab(text, container);
    }

    public void actionPerformed(ActionEvent e) {
    }

    public void addLogoutListener(ActionListener listener) {
        logoutBtn.addActionListener(listener);
    }
}
