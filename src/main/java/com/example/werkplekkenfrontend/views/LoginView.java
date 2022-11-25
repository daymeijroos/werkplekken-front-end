package com.example.werkplekkenfrontend.views;

import com.example.werkplekkenfrontend.controllers.LoginController;
import com.example.werkplekkenfrontend.controllers.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginView implements View<LoginController>, ViewController {
    LoginController loginController = new LoginController(this);

    @FXML
    public TextField eMailInput;

    @FXML
    public PasswordField passwordInput;

    @FXML
    public void login() {
        loginController.login(eMailInput.getText(), passwordInput.getText());
    }

    @FXML
    public void showRegisterView() {
        loginController.showRegisterView(eMailInput.getText());
    }

    @Override
    public LoginController getController() {
        return loginController;
    }

    public void setMail(String mail) {
        this.eMailInput.setText(mail);
    }

    @Override
    public void updateView() {

    }
}
