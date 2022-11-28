package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.daos.LoginDao;
import com.example.werkplekkenfrontend.daos.UserDao;
import com.example.werkplekkenfrontend.services.HttpService;
import com.example.werkplekkenfrontend.views.LoginView;
import com.example.werkplekkenfrontend.views.RegisterView;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterController implements ViewController {
    RegisterView registerView;

    public RegisterController(RegisterView registerView) {
        this.registerView = registerView;
    }

    public AuthController authController = new AuthController(
            new LoginDao(new HttpService(), new ObjectMapper()),
            new UserDao(new HttpService(), new ObjectMapper())
    );

    public void setAuthController(AuthController authController) {
        this.authController = authController;
    }

    public void updateFields(String mail) {
        registerView.setMail(mail);
    }


    private boolean isEmailInvalid(String mail) {
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(mail);
        return !mat.matches();
    }

    public void register(String firstName, String lastName, String mail, String password) {
        if (Objects.equals(firstName, "")) {
            Main.sceneController.showError("Voer een voornaam in");
            return;
        }
        if (Objects.equals(lastName, "")) {
            Main.sceneController.showError("Voer een achternaam in");
            return;
        }
        if (Objects.equals(mail, "") || isEmailInvalid(mail)) {
            Main.sceneController.showError("Voer een geldige e-mail in");
            return;
        }
        if (Objects.equals(mail, "") || isEmailInvalid(mail)) {
            Main.sceneController.showError("Voer een geldige e-mail in");
            return;
        }
        if (Objects.equals(password, "")) {
            Main.sceneController.showError("Voer een wachtwoord in");
            return;
        }
        try {
            authController.register(firstName, lastName, mail, password);
        } catch (Exception e) {
            Main.sceneController.showError("Er ging iets mis aan onze kant!");
        }
    }

    LoginView loginView;
    public void showLoginView(String mail) {
        loginView = (LoginView) Main.sceneController.showView("login-view.fxml");
        loginView.getController().setAuthController(authController);
        loginView.setMail(mail);
    }

    @Override
    public void updateView() {

    }
}