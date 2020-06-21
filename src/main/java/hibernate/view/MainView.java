package hibernate.view;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame implements ActionListener {

    JTabbedPane pane;
    JButton logoutBtn = new JButton("Đăng xuất");
    public MainView(){
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pane = new JTabbedPane();
        this.add(pane, BorderLayout.CENTER);
        this.pack();
        this.setTitle("Portal HCMUS");
        this.setSize(1000, 700);
        this.setResizable(false);

    }

    public void addChangeListener(ChangeListener changeListener){
        pane.addChangeListener(changeListener);
    }


    public void addTab(String text, Container container){
        pane.addTab(text, container);
    }

    public void actionPerformed(ActionEvent e) {

    }
}
