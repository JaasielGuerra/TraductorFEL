package com.guerra.tasks;

import com.guerra.exception.ExportFelException;
import com.guerra.service.ExportFelToPdfService;
import javafx.concurrent.Task;
import net.sf.jasperreports.engine.JRException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public class ExportToPdfInBackground extends Task<Void> {

    private final ExportFelToPdfService exportFelToPdfService;
    private final List<String> xmlNamesFilesToExport;

    public ExportToPdfInBackground(ExportFelToPdfService exportFelToPdfService, List<String> xmlNamesFilesToExport) {

        this.exportFelToPdfService = exportFelToPdfService;
        this.xmlNamesFilesToExport = xmlNamesFilesToExport;

    }

    @Override
    protected Void call() throws ExportFelException {

        var exception = new ExportFelException();

        final int numberOfFiles = getNumberOfFilesToExport();

        for (int i = 1; i <= numberOfFiles; i++) {

            updateMessage("Exportando archivo " + i + " de " + numberOfFiles);
            String xmlNameFileToExport = getXmlNameFileToExport(i);

            try {
                exportFelToPdfService.exportXml(xmlNameFileToExport);

            } catch (JRException | JAXBException | IOException e) {
                exception.addException(new ExportFelException("Error al exportar archivo " + xmlNameFileToExport, e));
            }

            updateProgress(i, numberOfFiles);
        }

        updateMessage("Archivos exportados con éxito");

        if (exception.hasExceptions()) {
            throw exception.build();
        }

        return null;
    }

    private int getNumberOfFilesToExport() {
        return xmlNamesFilesToExport.size();
    }

    private String getXmlNameFileToExport(int i) {
        return xmlNamesFilesToExport.get(i - 1);
    }

}
