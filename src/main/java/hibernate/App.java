package hibernate;

import hibernate.controller.LichPhucKhaoController;
import hibernate.controller.LoginController;

import hibernate.view.*;


public class App {
    public static void main(String[] args) {
        LoginController loginController = new LoginController();
        loginController.showLoginView();

    }
}
