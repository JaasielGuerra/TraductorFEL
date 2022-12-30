package com.guerra.tasks;

import com.guerra.service.FelScanService;
import com.guerra.util.ShowAlertsUtil;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.List;

@Log4j2
public class ScanInputDirectoryInBackground extends Task<List<String>> {

    private final FelScanService felScanService;
    private List<String> xmlFilesToExport;
    private final ListView<String> lstViewArchivosXml;
    private final Alert progressAlert;

    /**
     * @param felScanService     servicio para escanear el directorio de entrada
     * @param lstViewArchivosXml componente de la vista para mostrar los archivos xml encontrados
     * @param progressAlert      alerta de progreso
     */
    public ScanInputDirectoryInBackground(FelScanService felScanService, ListView<String> lstViewArchivosXml, Alert progressAlert) {
        this.felScanService = felScanService;
        this.lstViewArchivosXml = lstViewArchivosXml;
        this.progressAlert = progressAlert;
    }

    @Override
    protected List<String> call() throws IOException {
        return felScanService.scanAndListXml();
    }


    @Override
    protected void succeeded() {
        xmlFilesToExport = getValue();
        updateListView();
        progressAlert.close();
        printInfo();
    }

    private void updateListView() {
        lstViewArchivosXml.getItems().clear();
        lstViewArchivosXml.getItems().addAll(xmlFilesToExport);
    }

    private void printInfo() {
        log.info("Se han encontrado " + xmlFilesToExport.size() + " archivos xml");
        ShowAlertsUtil.showInfo("Archivos xml encontrados", "Operación exitosa", "Se han encontrado " + xmlFilesToExport.size() + " archivos xml");
    }

    @Override
    protected void failed() {
        log.fatal("Error al escanear el directorio", getException());
        progressAlert.close();
        ShowAlertsUtil.showError("Error al escanear el directorio", "Error, revise el log para más detalles", getException().toString());
    }
}
