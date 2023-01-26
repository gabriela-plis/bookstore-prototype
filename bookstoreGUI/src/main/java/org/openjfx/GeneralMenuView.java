package org.openjfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.Customer;
import org.example.DBObject;
import org.example.Employee;
import org.example.GeneralFacade;

public class GeneralMenuView extends Application implements ViewsNavigation {

    String[] sensitiveData = {"",""};

    @Override
    public void start(Stage window) throws Exception {

        BorderPane view = new BorderPane();

        VBox title = new VBox(new Label("BOOKSTORE"));

        view.setTop(title);

        VBox buttonsSection = createMainMenu(window);
        view.setCenter(buttonsSection);

        Scene scene = new Scene(view);

        window.setScene(scene);
    }

    private VBox createMainMenu (Stage window) {
        Button customerLogInBtn = new Button("Log In as Customer");
        Button employeeLogInBtn = new Button("Log In as Employee");
        Button registerBtn = new Button("Register as Customer");

        customerLogInBtn.setOnAction(event -> {
            CustomerLogInView customerLogIn = new CustomerLogInView(new GeneralMenuView());
            goToNextView(customerLogIn, window);

        });

        employeeLogInBtn.setOnAction(event -> {
            EmployeeLogInView employeeLogIn = new EmployeeLogInView(new GeneralMenuView());
            goToNextView(employeeLogIn, window);
        });

        registerBtn.setOnAction(event -> {
            RegistrationView registrationView = new RegistrationView(new GeneralMenuView());
            goToNextView(registrationView, window);

        });

        return new VBox(customerLogInBtn, registerBtn, employeeLogInBtn);

    }

//    private <T extends DBObject> Scene createLogInMenu (Class<T> type, Stage window) {
//
//        TextField IDField = new TextField();
//        HBox IDArea = new HBox(new Label("ID: "), IDField);
//
//        PasswordField passwordField = new PasswordField();
//        HBox passwordArea = new HBox(new Label("Password: "), passwordField);
//
//        Button logInBtn = new Button("Log In");
//        logInBtn.setOnAction(event -> {
//            GeneralFacade<T> facade = new GeneralFacade<>(type);
//            facade.isLogIn(Integer.parseInt(IDField.getText()), passwordField.getText());
//
//            T person = (T) facade.getPerson();
//
//            if (person.getClass().equals(Customer.class)) {
//                CustomerMenuView customerMenuView = new CustomerMenuView();
//                try {
//                    customerMenuView.start(window);
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//            } else if (person.getClass().equals(Employee.class)) {
//                EmployeeMenuView employeeMenuView = new EmployeeMenuView();
//                try {
//                    employeeMenuView.start(window);
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//            } else {
//                window.setScene(new Scene(new Label("Wrong ID or password")));
//            }
//
//        });
//
//        return new Scene(new VBox(IDArea, passwordArea, logInBtn));
//
//    }


    public String[] getSensitiveData () {
        return sensitiveData;
    }

}
