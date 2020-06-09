package hibernate;

import hibernate.controller.QuanLySinhVienController;
import hibernate.view.QuanLySinhVienView;


public class App {
    public static void main(String[] args) {

//        LoginView loginView = new LoginView();
//        loginView.setVisible(true);
        QuanLySinhVienView qlsvView = new QuanLySinhVienView();
        QuanLySinhVienController qlsv = new QuanLySinhVienController(qlsvView);
        qlsv.showStudentView();
    }
}
