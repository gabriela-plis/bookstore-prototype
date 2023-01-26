package org.openjfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.BooksFacade;
import org.example.CustomerFacade;

import java.util.List;

public class ReturnBookView extends Application implements ViewsNavigation {

    private final Application previousView;
    private final CustomerFacade customerFacade;
    private final BooksFacade booksFacade;

    public ReturnBookView (CustomerFacade customerFacade, BooksFacade booksFacade, Application previousView) {
        this.customerFacade = customerFacade;
        this.booksFacade = booksFacade;
        this.previousView = previousView;
    }

    @Override
    public void start(Stage window) throws Exception {
        List<String> booksToReturn = customerFacade.getBorrowedBooksTitles();

        ListView<RadioButton> booksToReturnListView = new ListView<>();
        ToggleGroup tg = new ToggleGroup();

        for (String title : booksToReturn) {
            RadioButton toSelect = new RadioButton(title);
            toSelect.setToggleGroup(tg);
            booksToReturnListView.getItems().add(toSelect);
        }

        Label feedback = new Label("");

        Button returnBtn = new Button("Return");

        returnBtn.setOnAction( event -> {
            String selectedBook = getStringRepresentationSelectedBook(tg.getSelectedToggle());

            try {
                //update method can throw exception if the given book doesn't exist
                booksFacade.updateBookAfterReturn(selectedBook);
                customerFacade.returnBook(selectedBook);

                feedback.setText("The book has been returned");

            } catch (IllegalArgumentException e) {

                ErrorView errorView = new ErrorView();
                goToNextView(errorView, window);
            }

        });

        Button goBackBtn = getGoBackBtn(previousView, window);

        Scene scene = new Scene(new VBox(new Label("List of available books: "), booksToReturnListView, returnBtn, feedback, goBackBtn));

        window.setScene(scene);
        window.show();
    }

    private String getStringRepresentationSelectedBook (Toggle selected) {
        String[] splittedStringObj = selected.toString().split("'");
        return splittedStringObj[1];
    }
}
