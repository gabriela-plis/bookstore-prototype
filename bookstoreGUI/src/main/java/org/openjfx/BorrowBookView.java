package org.openjfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.BooksFacade;
import org.example.CustomerFacade;
import org.example.Person;

import java.util.List;

public class BorrowBookView extends Application implements ViewsNavigation {

    private final CustomerFacade customerFacade;
    private final BooksFacade booksFacade;
    private final Application previousView;

    public BorrowBookView (CustomerFacade facade, Application previousView) {
        this.customerFacade = facade;
        this.booksFacade = new BooksFacade();
        this.previousView = previousView;
    }

    @Override
    public void start(Stage window) throws Exception {
        List<String> booksToBorrow = booksFacade.getAvailableBooksToBorrow(customerFacade);

        ListView<RadioButton> booksToBorrowListView = new ListView<>();
        ToggleGroup tg = new ToggleGroup();

        for (String title : booksToBorrow) {
            RadioButton toSelect = new RadioButton(title);
            toSelect.setToggleGroup(tg);
            booksToBorrowListView.getItems().add(toSelect);
        }

        Label feedback = new Label("");

        Button borrowBtn = new Button("Borrow");

        borrowBtn.setOnAction( event -> {
            String selectedBook = getStringRepresentationSelectedBook(tg.getSelectedToggle());

            try {
                //update method can throw exception if the given book doesn't exist
                booksFacade.updateBookAfterBorrow(selectedBook);
                customerFacade.borrowBook(selectedBook);

                feedback.setText("You borrowed this book");

            } catch (IllegalArgumentException e) {

                ErrorView errorView = new ErrorView();
                goToNextView(errorView, window);
            }

        });

        Button goBackBtn = new Button("Go to previous menu");

        goBackBtn.setOnAction(event -> {
            goToBackView(previousView, window);
        });

        Scene scene = new Scene(new VBox(new Label("List of available books: "), booksToBorrowListView, borrowBtn, feedback, goBackBtn));

        window.setScene(scene);
        window.show();
    }

    private String getStringRepresentationSelectedBook (Toggle selected) {
        String[] splittedStringObj = selected.toString().split("'");
        return splittedStringObj[1];
    }
}
