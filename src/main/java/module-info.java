module org.mjk.finalproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;




    opens org.mjk.finalproject to javafx.fxml;
    exports org.mjk.finalproject;
}