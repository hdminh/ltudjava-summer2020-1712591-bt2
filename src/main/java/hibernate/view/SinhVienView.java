package hibernate.view;

import hibernate.controller.DoiMatKhauController;
import hibernate.controller.SinhVienPhucKhaoController;
import hibernate.controller.SinhVienXemDiemController;
import hibernate.entity.UserEntity;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
        this.add(pane, BorderLayout.CENTER);
        pane.add(logoutBtn);

        this.pack();
        this.setTitle("Portal HCMUS");
        this.setSize(1000, 700);
        this.setResizable(false);
    }

    public void addTab(String text, Container container){
        pane.addTab(text, container);
    }

    public void actionPerformed(ActionEvent e) {

    }
}
