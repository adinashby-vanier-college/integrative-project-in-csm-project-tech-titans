module org.example.motionsim {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.motionsim.Controller to javafx.fxml;
    exports org.example.motionsim;
    exports org.example.motionsim.Controller;
    exports org.example.motionsim.Model;
    opens org.example.motionsim.Model to javafx.fxml;
}