package hibernate;

import hibernate.controller.QuanLySinhVienController;
import hibernate.controller.ThoiKhoaBieuController;
import hibernate.view.QuanLySinhVienView;
import hibernate.view.ThoiKhoaBieuView;


public class App {
    public static void main(String[] args) {

//        LoginView loginView = new LoginView();
//        loginView.setVisible(true);
//        QuanLySinhVienView qlsvView = new QuanLySinhVienView();
//        QuanLySinhVienController qlsv = new QuanLySinhVienController(qlsvView);
//        qlsv.showStudentView();
        ThoiKhoaBieuView tkb = new ThoiKhoaBieuView();
        ThoiKhoaBieuController tkbController = new ThoiKhoaBieuController(tkb);
        tkbController.showTkbView();
    }
}
