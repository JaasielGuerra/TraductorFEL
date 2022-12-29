package com.guerra.controller;

import com.guerra.model.dto.ConfigFelDto;
import com.guerra.service.ConfigFelService;
import com.guerra.service.ConfigFelServiceImpl;
import com.guerra.util.AppProperties;
import com.guerra.util.ShowAlertsUtil;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
public class FELController {

    @FXML
    private TextField txtSocialFacebook;
    @FXML
    private TextField txtContactTels;
    @FXML
    private TextField txtContactEmail;
    @FXML
    private TextField txtNameLogo;
    @FXML
    private ListView<String> lstArchivosXml;

    private ConfigFelService configFelService;

    public void initialize() {
        log.info("Aplicacion iniciada");
        initServices();
        loadConfigurationFel();
    }

    private void initServices() {
        configFelService = new ConfigFelServiceImpl(AppProperties.getPathToConfigFile());
    }

    private void loadConfigurationFel() {
        try {
            ConfigFelDto configFelDto = configFelService.readConfig();
            txtSocialFacebook.setText(configFelDto.getFooterSocialFacebook());
            txtContactTels.setText(configFelDto.getFooterContactTels());
            txtContactEmail.setText(configFelDto.getFooterContactEmail());
            txtNameLogo.setText(configFelDto.getHeaderLogoName());
            log.debug("Configuración fel leída: " + configFelDto);
        } catch (IOException e) {
            log.fatal("Error al leer el archivo de configuración fel", e);
            ShowAlertsUtil.showError("Error", "Error al leer el archivo de configuración fel", e.getMessage());
            System.exit(1);
        }
    }

    @FXML
    private void onActionBtnGuardarConfig() {

        log.info("Boton guardar configuracion presionado");

        try {

            configFelService.saveConfig(captureConfigFel());

            log.info("Configuración guardada");
            ShowAlertsUtil.showInfo("Configuración guardada", "Operación exitosa", "Se ha guardado la configuración de la FEL");

        } catch (IOException e) {

            log.fatal("Error al guardar la configuración", e);
            ShowAlertsUtil.showError("Error al guardar la configuración", "Error, revise el log para más detalles", e.getMessage());
        }

    }

    private ConfigFelDto captureConfigFel() throws IOException {
        return ConfigFelDto.builder()
                .headerLogoName(txtNameLogo.getText())
                .footerSocialFacebook(txtSocialFacebook.getText())
                .footerContactTels(txtContactTels.getText())
                .footerContactEmail(txtContactEmail.getText())
                .build();
    }


}
