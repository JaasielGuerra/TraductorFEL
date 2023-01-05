package com.guerra.service;

import com.guerra.util.AppProperties;
import net.sf.jasperreports.engine.JRException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ExportFelToPdfServiceImplTest {

    ExportFelToPdfService exportFelToPdfService;

    @BeforeEach
    void setUp() {

        ConfigFelService configFelService = new ConfigFelServiceImpl(AppProperties.getPathToConfigFile());

        exportFelToPdfService = new ExportFelToPdfServiceImpl(
                AppProperties.getPathOutputDirectory(),
                AppProperties.getPathResourceExternalDirectory(),
                configFelService,
                AppProperties.getUrlBaseVerificadorFel());
    }

    @Test
    void exportFelXmlToPdf() throws JRException, JAXBException, IOException {
        exportFelToPdfService.exportXml("FE49EB4D-5186-4C50-82FE-DCFB11AF5DAF.xml");
        assertTrue(true, "Se exporto el archivo FEL a PDF");
    }
}