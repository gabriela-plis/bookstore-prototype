module bookstore.bookstoreGUI.main {

    requires javafx.controls;
    requires javafx.fxml;
    requires bookstore.bookstoreAPI.main;

    opens org.openjfx to javafx.fxml;
    exports org.openjfx;
}