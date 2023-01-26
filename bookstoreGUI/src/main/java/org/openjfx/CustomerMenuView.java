package org.openjfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.BooksFacade;
import org.example.CustomerFacade;

public class CustomerMenuView extends Application implements ViewsNavigation {

    private final CustomerFacade customerFacade;
    private final BooksFacade booksFacade;
    public CustomerMenuView(CustomerFacade customerFacade, BooksFacade booksFacade) {
        this.customerFacade = customerFacade;
        this.booksFacade = booksFacade;
    }

    @Override
    public void start(Stage window) throws Exception {

        Button borrowBookBtn = new Button("Borrow a book");
        Button seeBorrowsBtn = new Button("See current borrows");
        Button returnBookBtn = new Button("Return a book");

        borrowBookBtn.setOnAction(event -> {
            BorrowBookView borrowBookView = new BorrowBookView(customerFacade, new CustomerMenuView(customerFacade, booksFacade));
            goToNextView(borrowBookView, window);
        });

        seeBorrowsBtn.setOnAction(event -> {
            CurrentBorrowsView currentBorrowsView = new CurrentBorrowsView(customerFacade, new CustomerMenuView(customerFacade, booksFacade));
            goToNextView(currentBorrowsView, window);
        });

        returnBookBtn.setOnAction(event -> {
            ReturnBookView returnBookView = new ReturnBookView(customerFacade, booksFacade, new CustomerMenuView(customerFacade, booksFacade));
            goToNextView(returnBookView, window);
        });

        Scene scene = new Scene(new VBox(new Label("Options"), borrowBookBtn, seeBorrowsBtn, returnBookBtn));

        window.setScene(scene);
        window.show();
    }

}
