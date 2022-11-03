package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;

public class AdminViewController implements ViewController{

    public void onBuildingsButtonClick(){
        ViewController controller = Main.sceneController.showView("admin-buildings-view.fxml");
        controller.updateView();
    }

    public void onRolesButtonClick(){

    }

    @Override
    public void updateView() {

    }
}
