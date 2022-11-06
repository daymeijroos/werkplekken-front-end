package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.daos.LoginDao;
import com.example.werkplekkenfrontend.daos.UserDao;
import com.example.werkplekkenfrontend.models.User;
import org.json.JSONObject;

public class LoginController {
    private String JWT = "";
    private User userLoggedIn = null;

    private final LoginDao loginDao;
    private final UserDao userDao;

    public LoginController(LoginDao loginDao, UserDao userDao) {
        this.loginDao = loginDao;
        this.userDao = userDao;
    }

    private void setUserLoggedIn() {
        userLoggedIn = userDao.getCurrent();
    }

    public void login(String email, String password) {
        String response = loginDao.login(email, password);
        JSONObject objectJSON = new JSONObject(response);
        JWT = "Bearer " + objectJSON.getString("jwt-token");
        this.setUserLoggedIn();
    }

    public void register(String firstName, String lastName, String email, String password) {
        String response = loginDao.register(firstName, lastName, email, password);

        System.out.println(response);
        JSONObject objectJSON = new JSONObject(response);
        JWT = "Bearer " + objectJSON.getString("jwt-token");
        this.setUserLoggedIn();
    }

    public String getJWT() {
        return this.JWT;
    }

    public User getUserLoggedIn() {
        return this.userLoggedIn;
    }
}
