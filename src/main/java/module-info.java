module com.example.smartstudytool {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.smartstudytool to javafx.fxml;
    exports com.example.smartstudytool;
}