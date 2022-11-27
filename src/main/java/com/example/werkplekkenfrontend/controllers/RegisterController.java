package com.example.werkplekkenfrontend.controllers;
import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.daos.LoginDao;
import com.example.werkplekkenfrontend.daos.UserDao;
import com.example.werkplekkenfrontend.elements.MessageElement;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterController implements ViewController {
    public AuthController authController = new AuthController(
            new LoginDao(new HttpService(), new ObjectMapper()),
            new UserDao(new HttpService(), new ObjectMapper())
            );

    public LoginController loginController;

    public void setAuthController(AuthController authController) {
        this.authController = authController;
    }

    public void updateFields(String email) {
        this.eMailInput.setText(email);
    }

    @FXML
    TextField firstNameInput;

    @FXML
    TextField lastNameInput;

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
    public void register() {
        String firstName = firstNameInput.getText().substring(0, 1).toUpperCase() + lastNameInput.getText().substring(1);
        String lastName = lastNameInput.getText().substring(0, 1).toUpperCase() + lastNameInput.getText().substring(1);
        String mail = eMailInput.getText();
        String password = passwordInput.getText();

        if (Objects.equals(firstName, "")) {
            Main.sceneController.showPopup(new MessageElement("Voer een voornaam in", "Okay").getPopup());
            return;
        }
        if (Objects.equals(lastName, "")) {
            Main.sceneController.showPopup(new MessageElement("Voer een achternaam in", "Okay").getPopup());
            return;
        }
        if (Objects.equals(mail, "") || !checkEmailAddress(mail)) {
            Main.sceneController.showPopup(new MessageElement("Voer een geldige e-mail in", "Okay").getPopup());
            return;
        }
        if (Objects.equals(password, "")) {
            Main.sceneController.showPopup(new MessageElement("Voer een wachtwoord in", "Okay").getPopup());
            return;
        }
        try {
            authController.register(firstName, lastName, mail, password);
        } catch (Exception e) {
            Main.sceneController.showPopup(new MessageElement(e.getMessage(), "Okay").getPopup());
            return;
        }
        System.out.println(Main.currentUser.getJWTtoken());
    }

    @FXML
    public void showLoginView() {
        loginController = (LoginController) Main.sceneController.showView("login-view.fxml");
        loginController.setAuthController(authController);
        loginController.updateFields(this.eMailInput.getText());
    }

    @Override
    public void updateView() {

    }
}
