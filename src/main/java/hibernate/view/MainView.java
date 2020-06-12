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
        JTabbedPane pane = new JTabbedPane();
        //pane.add(qlsvView);
        this.add(pane);
        this.pack();
        this.setTitle("Portal HCMUS");
        this.setSize(800, 420);
        this.setResizable(false);
    }

    public void actionPerformed(ActionEvent e) {

    }
}
