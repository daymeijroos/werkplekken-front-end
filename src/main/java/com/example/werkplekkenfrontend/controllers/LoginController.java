package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.daos.LoginDao;
import com.example.werkplekkenfrontend.daos.UserDao;
import com.example.werkplekkenfrontend.models.User;
import org.json.JSONObject;

public class LoginController {
    private String JWT = "";
    private User userLoggedIn = null;

    private void setUserLoggedIn() {
        UserDao dao = new UserDao();
        userLoggedIn = dao.getCurrent();
    }

    public void login(String email, String password) {
        JWT = "";
        LoginDao dao = new LoginDao();
        String response = dao.login(email, password);
        if (response == null) return;
        JSONObject objectJSON = new JSONObject(response);
        JWT = "Bearer " + objectJSON.getString("jwt-token");
        setUserLoggedIn();
    }

    public void register(String firstName, String lastName, String email, String password) {
        JWT = "";
        LoginDao dao = new LoginDao();
        String response = dao.register(firstName, lastName, email, password);
        if (response == null) return;
        System.out.println(response);
        JSONObject objectJSON = new JSONObject(response);
        JWT = "Bearer " + objectJSON.getString("jwt-token");
        setUserLoggedIn();
    }

    public String getJWT() {
        return this.JWT;
    }

    public User getUserLoggedIn() {
        return this.userLoggedIn;
    }
}
