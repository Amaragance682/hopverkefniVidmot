module org.example.worm {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens org.example.worm to javafx.fxml;
    exports org.example.worm;
}