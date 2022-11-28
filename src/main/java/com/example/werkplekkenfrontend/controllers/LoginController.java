package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.views.LoginView;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginController implements ViewController {
    LoginView loginView;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
    }

    public AuthController authController;



    public void setAuthController(AuthController authController) {
        this.authController = authController;
    }

    private boolean checkEmailAddress(String mail) {
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(mail);
        return mat.matches();
    }

    public void login(String mail, String password) {
        if (Objects.equals(mail, "") || !checkEmailAddress(mail)) {
            Main.sceneController.showError("Voer een geldige e-mail in");
            return;
        }
        if (Objects.equals(password, "")) {
            Main.sceneController.showError("Voer een wachtwoord in");
            return;
        }

        try {
            authController.login(mail, password);
        } catch (Exception e) {
            Main.sceneController.showError("Er ging iets mis aan onze kant!");
        }
    }

    RegisterController registerController;
    public void showRegisterView(String mail) {
        registerController = (RegisterController) Main.sceneController.showView("register-view.fxml");
        registerController.setAuthController(authController);
        registerController.updateFields(mail);
    }

    @Override
    public void updateView() {

    }
}