package com.guerra.controller;

import com.guerra.Main;
import com.guerra.model.dto.ConfigFelDto;
import com.guerra.service.*;
import com.guerra.tasks.ExportToPdfInBackground;
import com.guerra.tasks.ScanInputDirectoryInBackground;
import com.guerra.util.AppProperties;
import com.guerra.util.ShowAlertsUtil;
import javafx.application.Platform;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
public class FELController {

    @FXML
    private Label lblTituloApp;
    @FXML
    private TextField txtSocialFacebook;
    @FXML
    private TextField txtContactTels;
    @FXML
    private TextField txtContactEmail;
    @FXML
    private TextField txtNameLogo;
    @FXML
    private ListView<String> lstViewArchivosXml;
    private Alert alertProgressExportToPdf;
    private ProgressIndicator progressBarExportToPdf;

    private ScanInputDirectoryInBackground scanInputDirectoryInBackground;
    private ExportToPdfInBackground exportToPdfInBackground;

    private ConfigFelService configFelService;
    private FelScanService felScanService;
    private ExportFelToPdfService exportFelToPdfService;

    public void initialize() {
        log.info("Aplicacion iniciada");
        addProjectVersionToTitle();
        initServices();
        loadConfigurationFel();
    }

    private void addProjectVersionToTitle() {
        Package mainPackage = Main.class.getPackage();
        String version = mainPackage.getImplementationVersion();
        String name = mainPackage.getImplementationTitle();
        lblTituloApp.setText(name + " v" + version);
    }

    private void initServices() {
        log.info("Inicializando servicios");
        configFelService = new ConfigFelServiceImpl(AppProperties.getPathToConfigFile());
        felScanService = new FelScanServiceImpl(AppProperties.getPathInputDirectory());
        exportFelToPdfService = new ExportFelToPdfServiceImpl(
                AppProperties.getPathOutputDirectory(),
                AppProperties.getPathResourceExternalDirectory(),
                configFelService,
                AppProperties.getUrlBaseVerificadorFel()
        );
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

            ConfigFelDto configFelDto = captureConfigFelFromView();
            configFelService.saveConfig(configFelDto);

            log.info("Configuración guardada");
            ShowAlertsUtil.showInfo("Configuración guardada", "Operación exitosa", "Se ha guardado la configuración de la FEL");

        } catch (IOException e) {

            log.fatal("Error al guardar la configuración", e);
            ShowAlertsUtil.showError("Error al guardar la configuración", "Error, revise el log para más detalles", e.getMessage());
        }

    }

    private ConfigFelDto captureConfigFelFromView() throws IOException {
        return ConfigFelDto.builder()
                .headerLogoName(txtNameLogo.getText())
                .footerSocialFacebook(txtSocialFacebook.getText())
                .footerContactTels(txtContactTels.getText())
                .footerContactEmail(txtContactEmail.getText())
                .build();
    }

    @FXML
    private void onActionBtnScanearDirectorio() {
        log.info("Boton scan presionado");

        inicializeScanInputDirectoryInBackground();
        invokeScanInputDirectoryInBackground();
    }

    private void inicializeScanInputDirectoryInBackground() {
        Alert progress = ShowAlertsUtil.showProgress("Escaneando directorio", "Espere mientras se escanea el directorio de entrada");
        scanInputDirectoryInBackground = new ScanInputDirectoryInBackground(felScanService, lstViewArchivosXml, progress);
    }

    private void invokeScanInputDirectoryInBackground() {
        Thread threadScanDirectory = new Thread(scanInputDirectoryInBackground);
        threadScanDirectory.start();
    }

    @FXML
    private void onActionBtnExportar() {

        log.info("Boton exportar presionado");

        validateValueScanInputDirectoryInBackground();

        log.debug("archivos a exportar: " + scanInputDirectoryInBackground.getValue());

        inicializeExportToPdfInBackground();

        inicializedProgressBarExportToPdf();

        inicializedAlertProgressExportToPdf();
        showAlertProgressExportToPdf();

        invokeExportToPdfInBackground();
    }

    private void validateValueScanInputDirectoryInBackground() {
        if (nullValueScanInputDirectoryInBackground()) {
            ShowAlertsUtil.showWarning("Escanear directorio", "Por favor escanear el directorio de entrada antes de exportar", null);
            throw new RuntimeException("No se ha escaneado el directorio de entrada");
        }

        if (isEmptyValueScanInputDirectoryInBackground()) {
            ShowAlertsUtil.showWarning("No hay archivos", "Por favor verifique que existan archivos xml en el directorio de entrada", null);
            throw new RuntimeException("No se han encontrado archivos xml en el directorio de entrada");
        }
    }

    private boolean nullValueScanInputDirectoryInBackground() {
        return scanInputDirectoryInBackground == null || scanInputDirectoryInBackground.getValue() == null;
    }

    private boolean isEmptyValueScanInputDirectoryInBackground() {
        return scanInputDirectoryInBackground.getValue().isEmpty();
    }

    private void inicializeExportToPdfInBackground() {
        exportToPdfInBackground = new ExportToPdfInBackground(exportFelToPdfService, scanInputDirectoryInBackground.getValue());
        exportToPdfInBackground.setOnFailed(this::printFailedExportToPdf);
        exportToPdfInBackground.setOnSucceeded(this::changeHeaderAlertProgressExportToPdf);
    }

    private void inicializedProgressBarExportToPdf() {
        progressBarExportToPdf = new ProgressIndicator();
        progressBarExportToPdf.setPrefSize(100, 100);
        progressBarExportToPdf.progressProperty().bind(exportToPdfInBackground.progressProperty());
    }

    private void inicializedAlertProgressExportToPdf() {
        alertProgressExportToPdf = new Alert(Alert.AlertType.INFORMATION);
        alertProgressExportToPdf.titleProperty().bind(exportToPdfInBackground.messageProperty());
        alertProgressExportToPdf.setHeaderText("Espere mientras se exportan los archivos a PDF");
        alertProgressExportToPdf.getDialogPane().setContent(progressBarExportToPdf);
    }

    private void showAlertProgressExportToPdf() {
        alertProgressExportToPdf.show();
    }

    private void invokeExportToPdfInBackground() {
        Thread threadExportToPdfInBackground = new Thread(exportToPdfInBackground);
        threadExportToPdfInBackground.start();
    }

    private void printFailedExportToPdf(WorkerStateEvent event) {
        closeAlertProgressExportToPdf();
        log.error("Error al exportar a pdf", event.getSource().getException());
        ShowAlertsUtil.showError("Ocurrieron errores al exportar a pdf", "Durante el proceso de exportación, ocurrieron los siguientes errores", event.getSource().getException().toString());
    }

    private void closeAlertProgressExportToPdf() {
        alertProgressExportToPdf.close();
    }

    private void changeHeaderAlertProgressExportToPdf(WorkerStateEvent event) {
        log.info("Exportación a pdf finalizada");
        alertProgressExportToPdf.setHeaderText("¡Se han exportado los archivos a PDF!");
    }

    @FXML
    private void onActionBtnCerrar() {
        log.info("Boton cerrar presionado");

        Alert alert = ShowAlertsUtil.showConfirmation("Cerrar aplicación", "¿Está seguro que desea cerrar la aplicación?");
        if (alert.getResult() == ButtonType.OK) {
            Platform.exit();
        }
    }

}
