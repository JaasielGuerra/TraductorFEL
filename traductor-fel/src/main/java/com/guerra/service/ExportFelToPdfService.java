package com.guerra.service;

import net.sf.jasperreports.engine.JRException;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface ExportFelToPdfService {

    void exportXml(String fileName) throws JAXBException, JRException, IOException;

}
