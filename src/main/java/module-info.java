module com.example.bittybytes {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bittybytes to javafx.fxml;
    exports com.example.bittybytes;
    exports com.example.bittybytes.Controllers;
    opens com.example.bittybytes.Controllers to javafx.fxml;
}