module org.example.motionsim {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.motionsim.Controller to javafx.fxml;

    opens org.example.motionsim to javafx.fxml;
    exports org.example.motionsim;
}