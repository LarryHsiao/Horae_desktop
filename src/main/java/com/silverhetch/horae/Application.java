package com.silverhetch.horae;

import com.silverhetch.horae.socket.MessageListener;
import com.silverhetch.horae.socket.SocketConnectionImpl;
import com.silverhetch.horae.socket.SocketDevice;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {
    private final SocketDevice socketDevice;

    public Application() {
        this.socketDevice = new SocketConnectionImpl().server(8912, new MessageListener() {
            @Override
            public void onReceive(String message) {
                socketDevice.sendMessage("This is response of " + message);
            }
        });
    }

    @Override
    public void init() throws Exception {
        super.init();
        socketDevice.launch();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        socketDevice.shutdown();
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
