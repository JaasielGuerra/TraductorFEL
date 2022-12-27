module com.guerra {
    requires javafx.controls;
    requires com.sun.xml.bind;
    requires java.xml.bind;
    requires jasperreports;
    requires jasperreports.fonts;
    requires java.sql;
    requires javafx.fxml;
    exports com.guerra;
    exports com.guerra.model;
    exports com.guerra.controller;
    opens com.guerra.model to java.xml.bind;
    opens com.guerra.controller to javafx.fxml;
}
