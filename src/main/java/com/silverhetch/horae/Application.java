package com.silverhetch.horae;

import com.silverhetch.horae.upnp.HoraeUPnPImpl;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.fourthline.cling.UpnpServiceImpl;

public class Application extends javafx.application.Application implements DeviceStatusListener{
    private final Horae horae;

    public Application() {
        this.horae = new HoraeImpl(new HoraeUPnPImpl(new UpnpServiceImpl()),this);
    }

    @Override
    public void onStatusChanged(DeviceStatus deviceStatus) {

    }

    @Override
    public void init() throws Exception {
        super.init();
        horae.launch();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        horae.shutdown();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(new StackPane(), 300, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
