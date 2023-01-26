package org.openjfx;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public interface ViewsNavigation {

    default Button getGoBackBtn (Application previousView, Stage window) {
        Button goBackBtn = new Button("Go to previous menu");

        goBackBtn.setOnAction(event -> {
            goToBackView(previousView, window);
        });

        return goBackBtn;
    }

    default void goToNextView(Application view, Stage window) {
        goToAnotherView(view, window);
    }

    default void goToBackView(Application view, Stage window) {
        goToAnotherView(view, window);
    }

    private void goToAnotherView(Application view, Stage window) {
        try {
            view.start(window);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
