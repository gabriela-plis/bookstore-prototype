package org.openjfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.*;

public class EmployeeLogInView extends Application implements ViewsNavigation {

    private final Application previousView;

    public EmployeeLogInView (Application previousView) {
        this.previousView = previousView;
    }

    @Override
    public void start(Stage window) throws Exception {
        TextField IDField = new TextField();
        HBox IDArea = new HBox(new Label("ID: "), IDField);

        PasswordField passwordField = new PasswordField();
        HBox passwordArea = new HBox(new Label("Password: "), passwordField);

        Button logInBtn = new Button("Log In");
        logInBtn.setOnAction(event -> {
            EmployeeFacade facade = new EmployeeFacade();
            boolean userIsLogIn = facade.isLogIn(Integer.parseInt(IDField.getText()), passwordField.getText());

            if (userIsLogIn) {
                EmployeeMenuView employeeMenu = new EmployeeMenuView(new BooksFacade());
                goToNextView(employeeMenu, window);

            } else {
                LoginFailedView failedView = new LoginFailedView();
                goToNextView(failedView, window);

            }
        });

        Button goBackBtn = getGoBackBtn(previousView, window);

        Scene scene = new Scene(new VBox(IDArea, passwordArea, logInBtn, goBackBtn));

        window.setScene(scene);
        window.show();
    }

}
