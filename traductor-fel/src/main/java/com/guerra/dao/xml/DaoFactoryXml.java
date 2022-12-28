package com.guerra.dao.xml;

import com.guerra.dao.*;
import com.guerra.model.GTDocumento;
import com.guerra.util.AppProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;


public class DaoFactoryXml extends DaoFactory {

    private static final Logger log = LogManager.getLogger(DaoFactoryXml.class);

    private final GTDocumento fel; // fuente de datos

    public DaoFactoryXml(String fileName) throws JAXBException {

        boolean existeArchivo = fileExists(buildPathToFile(fileName));
        if (!existeArchivo) {
            log.fatal("No existe el archivo " + fileName);
            throw new JAXBException("No existe el archivo " + fileName);
        }

        JAXBContext jaxbContext = JAXBContext.newInstance(GTDocumento.class);
        fel = (GTDocumento) jaxbContext.createUnmarshaller().unmarshal(new File(buildPathToFile(fileName)));
        log.info("Se cargo el archivo " + fileName);
    }


    private boolean fileExists(String path) {
        File file = new File(path);
        return file.exists();
    }


    private String buildPathToFile(String fileName) {
        return AppProperties.getPathInputDirectory() + File.separator + fileName;
    }

    @Override
    public EmisorDao getEmisorDao() {
        return new EmisorDaoXml(fel);
    }

    @Override
    public ReceptorDao getReceptorDao() {
        return new ReceptorDaoXml(fel);
    }

    @Override
    public CertificacionDao getCertificacionDao() {
        return new CertificacionDaoXml(fel);
    }

    @Override
    public ItemsDao getItemsDao() {
        return new ItemsDaoXml(fel);
    }

    @Override
    public TotalesDao getTotalesDao() {
        return new TotalesDaoXml(fel);
    }


}


