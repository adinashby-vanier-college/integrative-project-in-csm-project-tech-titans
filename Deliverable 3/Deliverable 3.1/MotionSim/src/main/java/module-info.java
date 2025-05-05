module org.example.motionsim {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.motionsim.Controller to javafx.fxml;
   // opens org.example.motionsim.Logic to javafx.graphics
    opens org.example.motionsim.Logic to javafx.fxml;
    exports org.example.motionsim;
    exports org.example.motionsim.Logic;
}