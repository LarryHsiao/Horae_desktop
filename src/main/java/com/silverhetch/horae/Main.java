package com.silverhetch.horae;

import com.silverhetch.horae.autoconnection.DeviceStatus;
import com.silverhetch.horae.autoconnection.DeviceStatusListener;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Main implements Initializable, DeviceStatusListener {

    @FXML
    private Label deviceRoleIndicator;
    private ResourceBundle bundle;

    @Override
    public void initialize(URL  location, ResourceBundle resources) {
        this.bundle = resources;
    }

    @Override
    public void onStatusChanged(DeviceStatus deviceStatus) {
        Platform.runLater(() -> deviceRoleIndicator.setText(deviceStatus.isMaster() ? bundle.getString("app.master") : bundle.getString("app.client")));
    }
}
