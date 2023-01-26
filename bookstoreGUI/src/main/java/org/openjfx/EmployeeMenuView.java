package org.openjfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.BooksFacade;

public class EmployeeMenuView extends Application implements ViewsNavigation {

    private final BooksFacade booksFacade;

    public EmployeeMenuView (BooksFacade booksFacade) {
        this.booksFacade = booksFacade;
    }

    @Override
    public void start(Stage window) throws Exception {

        Button addBookBtn = new Button("Add a book");
        Button removeBookBtn = new Button("Remove a book");

        addBookBtn.setOnAction(event -> {
            AddBookView addBookView = new AddBookView(booksFacade, new EmployeeMenuView(booksFacade));
            goToNextView(addBookView, window);
        });

        removeBookBtn.setOnAction(event -> {
            RemoveBookView removeBookView = new RemoveBookView(booksFacade, new EmployeeMenuView(booksFacade));
            goToNextView(removeBookView, window);
        });

        Scene scene = new Scene(new VBox(new Label("Options"), addBookBtn, removeBookBtn));

        window.setScene(scene);
        window.show();
    }
}
