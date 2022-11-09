package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.daos.LoginDao;
import com.example.werkplekkenfrontend.daos.UserDao;
import org.json.JSONObject;

import java.util.Objects;

public class AuthController {
    private final LoginDao loginDao;
    private final UserDao userDao;

    public AuthController(LoginDao loginDao, UserDao userDao) {
        this.loginDao = loginDao;
        this.userDao = userDao;
    }

    private void setUserLoggedIn(String JWTToken) {
        Main.currentUser.setJWTtoken("Bearer " + JWTToken);
        Main.currentUser.setId(userDao.getCurrent().id);
    }

    public void login(String email, String password) throws Exception {
        Main.currentUser.setJWTtoken("");
        String response = loginDao.login(email, password);
        System.out.println(response);
        JSONObject objectJSON = new JSONObject(response);
        if (!Objects.equals(objectJSON.getInt("status"), 200)) throw new Exception(objectJSON.getString("message"));
        this.setUserLoggedIn(objectJSON.getString("jwt-token"));
        AdminViewController controller = (AdminViewController) Main.sceneController.showView("admin-view.fxml");
        controller.updateView();
    }

    public void register(String firstName, String lastName, String email, String password) throws Exception {
        Main.currentUser.setJWTtoken("");
        String response = loginDao.register(firstName, lastName, email, password);
        System.out.println(response);
        JSONObject objectJSON = new JSONObject(response);
        if (!Objects.equals(objectJSON.getInt("status"), 200)) throw new Exception(objectJSON.getString("message"));
        this.setUserLoggedIn(objectJSON.getString("jwt-token"));
    }
}
