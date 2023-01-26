package org.openjfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ErrorView extends Application {

    @Override
    public void start(Stage window) throws Exception {
        HBox view = new HBox(new Label("Error, something goes wrong:("));
        Scene scene = new Scene(view);

        window.setScene(scene);
        window.show();
    }
}
