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
import org.example.BooksFacade;
import org.example.CustomerFacade;

public class CustomerLogInView extends Application implements ViewsNavigation {

    private final Application previousView;

    public CustomerLogInView(Application previousView) {
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
            CustomerFacade facade = new CustomerFacade();
            boolean userIsLogIn = facade.isLogIn(Integer.parseInt(IDField.getText()), passwordField.getText());

            if (userIsLogIn) {
                CustomerMenuView customerMenu = new CustomerMenuView(facade, new BooksFacade());
                goToNextView(customerMenu, window);

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
