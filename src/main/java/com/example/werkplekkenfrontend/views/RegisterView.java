package com.example.werkplekkenfrontend.views;

import com.example.werkplekkenfrontend.controllers.RegisterController;
import com.example.werkplekkenfrontend.controllers.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterView  implements View<RegisterController>, ViewController {
    RegisterController registerController = new RegisterController(this);

    @FXML
    public TextField firstNameInput;

    @FXML
    public TextField lastNameInput;

    @FXML
    public TextField eMailInput;

    @FXML
    public PasswordField passwordInput;

    @FXML
    public void register() {
        registerController.register(
                firstNameInput.getText().substring(0, 1).toUpperCase() + lastNameInput.getText().substring(1),
                lastNameInput.getText().substring(0, 1).toUpperCase() + lastNameInput.getText().substring(1),
                eMailInput.getText(),
                passwordInput.getText()
        );
    }

    @FXML
    public void showLoginView() {
        registerController.showLoginView(eMailInput.getText());
    }

    @Override
    public RegisterController getController() {
        return registerController;
    }

    public void setMail(String mail) {
        this.eMailInput.setText(mail);
    }

    @Override
    public void updateView() {

    }
}