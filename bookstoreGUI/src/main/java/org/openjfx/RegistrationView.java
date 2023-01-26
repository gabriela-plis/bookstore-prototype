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

public class RegistrationView extends Application implements ViewsNavigation {

    private final Application previousView;

    public RegistrationView(Application previousView) {
        this.previousView = previousView;
    }

    @Override
    public void start(Stage window) throws Exception {
        TextField firstNameField = new TextField();
        HBox firstNameArea = new HBox(new Label("First name: "), firstNameField);

        TextField lastNameField = new TextField();
        HBox lastNameArea = new HBox(new Label("Last name: "), lastNameField);

        TextField phoneFiled = new TextField();
        HBox phoneArea = new HBox(new Label("Phone (123456789 convention): "), phoneFiled);

        PasswordField passwordField = new PasswordField();
        HBox passwordArea = new HBox(new Label("Password: "), passwordField);

        TextField emailField = new TextField();
        HBox emailArea = new HBox(new Label("Email: "), emailField);

        Button registerBtn = new Button("Register");

        registerBtn.setOnAction(event -> {
            CustomerFacade facade = new CustomerFacade();
            facade.register(firstNameField.getText(), lastNameField.getText(), phoneFiled.getText(), passwordField.getText(), emailField.getText());

            if (facade.wasRegisterSuccessful()) {
                CustomerMenuView customerMenu = new CustomerMenuView(facade, new BooksFacade());
                goToNextView(customerMenu, window);

            } else {
                ErrorView errorView = new ErrorView();
                goToNextView(errorView, window);

            }
        });

        Button goBackBtn = getGoBackBtn(previousView, window);

        Scene scene = new Scene(new VBox(firstNameArea, lastNameArea, phoneArea, passwordArea, emailArea, registerBtn, goBackBtn));

        window.setScene(scene);
        window.show();

    }
}
