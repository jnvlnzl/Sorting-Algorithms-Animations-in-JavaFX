module com.example.selectionsort {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.selectionsort to javafx.fxml;
    exports com.example.selectionsort;
}