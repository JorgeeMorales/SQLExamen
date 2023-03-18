module com.example.sqlexamen {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.sqlexamen to javafx.fxml;
    exports com.example.sqlexamen;
}