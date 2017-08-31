package com.silverhetch.horae;

import com.silverhetch.horae.autoconnection.DeviceStatus;
import com.silverhetch.horae.autoconnection.DeviceStatusListener;
import com.silverhetch.horae.upnp.HoraeUPnPImpl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.fourthline.cling.UpnpServiceImpl;

import java.awt.*;
import java.net.MalformedURLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Application extends javafx.application.Application implements DeviceStatusListener {
    private final Horae horae;
    private final Main main;

    public Application() {
        this.horae = new HoraeImpl(new HoraeUPnPImpl(new UpnpServiceImpl()), new LogImpl(), this, new MessageHandle() {
            @Override
            public int messageType() {
                return 8912;
            }

            @Override
            public void onReceive(String s) {
                try {
                    displayTray(s);
                } catch (AWTException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });
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

    public void displayTray(String message) throws AWTException, java.net.MalformedURLException {
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getToolkit().createImage(getClass().getResource("icon.png"));
        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        //Let the system resizes the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);
        trayIcon.displayMessage("Hello, World", message, TrayIcon.MessageType.INFO);
    }
}
