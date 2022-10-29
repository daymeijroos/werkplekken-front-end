package com.example.werkplekkenfrontend.models;

import com.example.werkplekkenfrontend.controllers.AdminBuildingsViewController;

public class AdminBuildingElement {

    AdminBuildingsViewController parentView;
    Building building;

    public AdminBuildingElement(AdminBuildingsViewController parentView, Building building) {
        this.parentView = parentView;
        this.building = building;
    }

    public void OpenFloorsView() {
        // Calls AdminFloorsViewController
    }

    public void OpenEditView() {

    }

    public void Delete() {

    }
}
