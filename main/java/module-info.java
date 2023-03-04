module com.example.softwaremethodologyproject03 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.softwaremethodologyproject03 to javafx.fxml;
    exports com.example.softwaremethodologyproject03;
}