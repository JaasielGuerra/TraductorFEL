module com.guerra {
    requires javafx.controls;
    requires com.sun.xml.bind;
    requires java.xml.bind;
    requires jasperreports;
    requires jasperreports.fonts;
    requires java.sql;
    exports com.guerra;
    exports com.guerra.model;
    opens com.guerra.model to java.xml.bind;
}
