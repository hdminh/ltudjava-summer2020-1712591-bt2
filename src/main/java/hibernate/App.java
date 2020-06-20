package hibernate;

import hibernate.controller.LichPhucKhaoController;
import hibernate.controller.LoginController;

import hibernate.view.*;


public class App {
    public static void main(String[] args) {
//        LoginView loginView = new LoginView();
//        LoginController loginController = new LoginController(loginView);
//        loginController.showLoginView();
        LichPhucKhaoController con = new LichPhucKhaoController();
        con.showlichView();

    }
}
