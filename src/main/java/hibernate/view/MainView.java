package hibernate.view;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;

public class MainView extends JFrame implements ActionListener {

    JTabbedPane pane;
    JButton logoutBtn;
    public MainView(){
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pane = new JTabbedPane();
        logoutBtn = new JButton("Đăng xuất");
        this.add(pane, BorderLayout.CENTER);
        this.pack();
        this.setTitle("Portal HCMUS");
        this.setSize(1000, 700);
        this.setResizable(false);
    }

    public void addLogout(){
        pane.add(new JPanel(), 7);
        pane.setTabComponentAt(7, logoutBtn);
    }

    public void addChangeListener(ChangeListener changeListener){
        pane.addChangeListener(changeListener);
    }

    public void addTab(String text, Container container){
        pane.addTab(text, container);
    }

    public void actionPerformed(ActionEvent e) {
    }

    public void addLogoutListener(ActionListener listener){
        logoutBtn.addActionListener(listener);
    }
}
