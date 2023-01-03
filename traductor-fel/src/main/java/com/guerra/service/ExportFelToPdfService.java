package com.guerra.service;

import net.sf.jasperreports.engine.JRException;

import javax.xml.bind.JAXBException;

public interface ExportFelToPdfService {

    void exportXml(String fileName) throws JAXBException, JRException;

}
