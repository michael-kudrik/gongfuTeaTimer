module org.mjk.finalproject {
    requires javafx.controls;
    requires javafx.fxml;




    opens org.mjk.finalproject to javafx.fxml;
    exports org.mjk.finalproject;
}