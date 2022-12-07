module com.example.apcles {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires de.mkammerer.argon2.nolibs;


    opens com.example.apcles to javafx.fxml;
    exports com.example.apcles;
    exports Class;
    opens Class to javafx.fxml;
}