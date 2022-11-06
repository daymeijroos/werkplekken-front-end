package com.example.werkplekkenfrontend.controllers;
import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.elements.MessageElement;
import javafx.scene.control.PasswordField;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginController implements ViewController {
    public AuthController authController;

    public RegisterController registerController;

    public void setAuthController(AuthController authController) {
        this.authController = authController;
    }

    public void updateFields(String email) {
        this.eMailInput.setText(email);
    }

    @FXML
    TextField eMailInput;

    @FXML
    PasswordField passwordInput;

    private boolean checkEmailAddress(String email) {
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email);
        return mat.matches();
    }

    @FXML
    public void login() {
        String mail = eMailInput.getText();
        String password = passwordInput.getText();
        if (Objects.equals(mail, "") || !checkEmailAddress(mail)) {
            Main.sceneController.showPopup(new MessageElement("Voer een geldige e-mail in", "Okay").getPopup());
            return;
        }
        if (Objects.equals(password, "")) {
            Main.sceneController.showPopup(new MessageElement("Voer een wachtwoord in", "Okay").getPopup());
            return;
        }

        try {
            authController.login(mail, password);
        } catch (Exception e) {
            Main.sceneController.showPopup(new MessageElement(e.getMessage(), "Okay").getPopup());
            return;
        }
        System.out.println(Main.currentUser.getJWTtoken());
    }

    @FXML
    public void showRegisterView() {
        registerController = (RegisterController) Main.sceneController.showView("register-view.fxml");
        registerController.setAuthController(authController);
        registerController.updateFields(this.eMailInput.getText());
    }

    @Override
    public void updateView() {

    }
}
