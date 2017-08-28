package com.silverhetch.horae;

import com.silverhetch.horae.autoconnection.DeviceStatus;
import com.silverhetch.horae.autoconnection.DeviceStatusListener;
import com.silverhetch.horae.upnp.HoraeUPnPImpl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.fourthline.cling.UpnpServiceImpl;

import java.util.Locale;
import java.util.ResourceBundle;

public class Application extends javafx.application.Application implements DeviceStatusListener {
    private final Horae horae;
    private final Main main;

    public Application() {
        this.horae = new HoraeImpl(new HoraeUPnPImpl(new UpnpServiceImpl()), this);
        this.main = new Main();
    }

    @Override
    public void onStatusChanged(DeviceStatus deviceStatus) {
        main.onStatusChanged(deviceStatus);
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
        ResourceBundle bundle = ResourceBundle.getBundle("main", new Locale("zh"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"), bundle);
        loader.setController(main);
        Scene scene = new Scene(loader.load(), 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
