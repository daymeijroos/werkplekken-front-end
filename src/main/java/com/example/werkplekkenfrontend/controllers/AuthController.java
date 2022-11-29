package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.daos.LoginDao;
import com.example.werkplekkenfrontend.daos.UserDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONObject;

import java.net.http.HttpResponse;

public class AuthController {
    private final LoginDao loginDao;
    private final UserDao userDao;

    public AuthController(LoginDao loginDao, UserDao userDao) {
        this.loginDao = loginDao;
        this.userDao = userDao;
    }

    private void setUserLoggedIn(String JWTToken) {
        Main.currentUser.setJWTtoken("Bearer " + JWTToken);
        try {
            Main.currentUser.setId(userDao.getCurrent().id);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void login(String email, String password) {
        try {
            Main.currentUser.setJWTtoken("");
            HttpResponse<String> response = loginDao.login(email, password);
            JSONObject objectJSON = new JSONObject(response.body());
            this.setUserLoggedIn(objectJSON.getString("jwt-token"));
            AdminViewController controller = (AdminViewController) Main.sceneController.showView("admin-view.fxml");
            controller.updateView();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void register(String firstName, String lastName, String email, String password) {
        try {
            Main.currentUser.setJWTtoken("");
            HttpResponse<String> response = loginDao.register(firstName, lastName, email, password);
            JSONObject objectJSON = new JSONObject(response.body());
            this.setUserLoggedIn(objectJSON.getString("jwt-token"));
            AdminViewController controller = (AdminViewController) Main.sceneController.showView("admin-view.fxml");
            controller.updateView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}