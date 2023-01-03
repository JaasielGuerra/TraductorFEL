module com.guerra {
    requires javafx.controls;
    requires com.sun.xml.bind;
    requires java.xml.bind;
    requires jasperreports;
    requires jasperreports.fonts;
    requires java.sql;
    requires javafx.fxml;
    requires org.apache.logging.log4j;
    requires lombok;
    exports com.guerra;
    exports com.guerra.model;
    exports com.guerra.controller;
    exports com.guerra.service;
    exports com.guerra.model.dto;
    exports com.guerra.exception;
    exports com.guerra.dao;
    opens com.guerra.model to java.xml.bind;
    opens com.guerra.controller to javafx.fxml;
}
