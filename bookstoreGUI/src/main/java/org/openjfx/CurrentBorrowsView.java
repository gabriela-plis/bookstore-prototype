package org.openjfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.CustomerFacade;

import java.util.List;

public class CurrentBorrowsView extends Application implements ViewsNavigation {

    private final CustomerFacade customerFacade;
    private final Application previousView;

    public CurrentBorrowsView (CustomerFacade facade, Application previousView) {
        this.customerFacade = facade;
        this.previousView = previousView;
    }
    @Override
    public void start(Stage window) throws Exception {
        List<String> borrowedBooks = customerFacade.getBorrowedBooksTitles();

        ListView<String> bookListView = new ListView<>();
        for (String title : borrowedBooks) {
            bookListView.getItems().add(title);
        }

        Button goBackBtn = getGoBackBtn(previousView, window);

        Scene scene = new Scene(new VBox(new Label("Your current borrows: "), bookListView, goBackBtn));

        window.setScene(scene);
        window.show();
    }
}
