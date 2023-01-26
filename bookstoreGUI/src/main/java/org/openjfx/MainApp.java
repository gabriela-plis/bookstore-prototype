package org.openjfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage window) throws Exception {

        HBox view = new HBox(new Label("Welcome in bookstore!"));
        Button goNext = new Button("go -> ");
        view.getChildren().add(goNext);
        Scene scene = new Scene(view);

        GeneralMenuView generalMenu = new GeneralMenuView();

        goNext.setOnAction(event -> {
            try {
                generalMenu.start(window);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        window.setScene(scene);
        window.show();
    }
}
