package com.guerra.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.logging.Logger;

public class FELController {

    private static final Logger logger = Logger.getLogger(FELController.class.getName());

    @FXML
    public Button btnCerrar;
    @FXML
    public Button btnGuardarConfig;
    @FXML
    public Button btnExportar;
    @FXML
    public TextField txtLinkFb;
    @FXML
    public TextField txtTels;
    @FXML
    public TextField txtCorreo;
    @FXML
    public TextField txtLogo;
    @FXML
    private ListView<String> lstArchivosXml;


    public void initialize() {
        logger.info("Aplicacion iniciada");
    }


}
