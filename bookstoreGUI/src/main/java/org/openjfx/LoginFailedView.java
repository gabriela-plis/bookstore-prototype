package org.openjfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class LoginFailedView extends Application {

    @Override
    public void start(Stage window) throws Exception {
        Scene scene = new Scene(new Label("Wrong ID or password, try again"));
        window.setScene(scene);
        window.show();
    }
}
