package org.openjfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.BooksFacade;

public class AddBookView extends Application implements ViewsNavigation {

    private final BooksFacade booksFacade;
    private final Application previousView;

    public AddBookView (BooksFacade booksFacade, Application previousView) {
        this.booksFacade = booksFacade;
        this.previousView = previousView;
    }

    @Override
    public void start(Stage window) throws Exception {

        TextField titleFiled = new TextField();
        HBox titleArea = new HBox(new Label("Title: "), titleFiled);

        TextField authorField = new TextField();
        HBox authorArea = new HBox(new Label("Author: "), authorField);

        TextField seriesField = new TextField();
        HBox seriesArea = new HBox(new Label("Series: "), seriesField);

        TextField publishYearFiled = new TextField();
        HBox publishYearArea = new HBox(new Label("Publish year: "), publishYearFiled);

        TextField canBeBorrowField = new TextField();
        HBox canBeBorrowArea = new HBox(new Label("Can be borrow? (true/false) :"), canBeBorrowField);

        TextField amountField = new TextField();
        HBox amountArea = new HBox(new Label("Amount: "), amountField);

        Label feedback = new Label("");

        Button addBtn = new Button("Add");

        addBtn.setOnAction(event -> {
            booksFacade.add(titleFiled.getText(), authorField.getText(), seriesField.getText(), publishYearFiled.getText(), Boolean.parseBoolean(canBeBorrowField.getText()), Integer.parseInt(amountField.getText()));
            feedback.setText("The book has been added");
        });

        Button goBackBtn = new Button("Go to previous menu");

        goBackBtn.setOnAction(event -> {
            goToBackView(previousView, window);
        });

        Scene scene = new Scene(new VBox(titleArea, authorArea, seriesArea, publishYearArea, canBeBorrowArea, amountArea, addBtn, feedback, goBackBtn));

        window.setScene(scene);
        window.show();
    }

}
