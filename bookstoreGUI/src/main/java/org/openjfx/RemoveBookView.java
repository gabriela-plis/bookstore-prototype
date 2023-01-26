package org.openjfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.BooksFacade;

import java.util.List;

public class RemoveBookView extends Application implements ViewsNavigation {

    private final BooksFacade booksFacade;
    private final Application previousView;

    public RemoveBookView (BooksFacade booksFacade, Application previousView) {
        this.booksFacade = booksFacade;
        this.previousView = previousView;
    }

    @Override
    public void start(Stage window) throws Exception {
        List<String> booksToRemove = booksFacade.getAvailableBooksToRemove();

        ListView<RadioButton> booksToRemoveListView = new ListView<>();
        ToggleGroup tg = new ToggleGroup();

        for (String title : booksToRemove) {
            RadioButton toSelect = new RadioButton(title);
            toSelect.setToggleGroup(tg);
            booksToRemoveListView.getItems().add(toSelect);
        }

        Label feedback = new Label("");

        Button removeBtn = new Button("Remove");

        removeBtn.setOnAction(event -> {

            try {
                booksFacade.remove(getStringRepresentationSelectedBook(tg.getSelectedToggle()));
                feedback.setText("The book has been removed");

            } catch (IllegalArgumentException e) {
                ErrorView errorView = new ErrorView();
                goToNextView(errorView, window);
            }

        });

        Button goBackBtn = getGoBackBtn(previousView, window);

        Scene scene = new Scene(new VBox(new Label("Available books to remove: "), booksToRemoveListView, removeBtn, feedback, goBackBtn));

        window.setScene(scene);
        window.show();

    }

    private String getStringRepresentationSelectedBook (Toggle selected) {
        String[] splittedStringObj = selected.toString().split("'");
        return splittedStringObj[1];
    }
}
