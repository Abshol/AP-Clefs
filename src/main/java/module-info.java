module com.example.apcles {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.apcles to javafx.fxml;
    exports com.example.apcles;
}