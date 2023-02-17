module com.example.bittybytes {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bittybytes to javafx.fxml;
    exports com.example.bittybytes;
    exports com.example.bittybytes.Menu;
    opens com.example.bittybytes.Menu to javafx.fxml;
    exports com.example.bittybytes.Snake;
    opens com.example.bittybytes.Snake to javafx.fxml;
    exports com.example.bittybytes.Runner;
    opens com.example.bittybytes.Runner to javafx.fxml;
}