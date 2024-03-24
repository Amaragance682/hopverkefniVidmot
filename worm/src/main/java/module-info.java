module org.example.worm {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.worm to javafx.fxml;
    exports org.example.worm;
}