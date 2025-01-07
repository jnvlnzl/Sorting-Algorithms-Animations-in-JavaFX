module com.example.bubblesort {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bubblesort to javafx.fxml;
    exports com.example.bubblesort;
}