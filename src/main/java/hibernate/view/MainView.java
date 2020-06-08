package hibernate.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MainView extends JFrame implements ActionListener {

    public MainView(){
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        LoginView loginView = new LoginView();
        JTabbedPane pane = new JTabbedPane();
//        pane.addTab("Login1", panel);
//        pane.addTab("Login2", panel1);
        this.add(pane);
        this.pack();
        this.setTitle("Portal HCMUS");
        this.setSize(1280, 720);
        this.setResizable(false);
    }

    public void actionPerformed(ActionEvent e) {

    }
}
