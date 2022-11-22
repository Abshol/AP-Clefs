module com.example.apcles {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.apcles to javafx.fxml;
    exports com.example.apcles;
}