package com.guerra.service;

import com.guerra.App;
import com.guerra.dao.*;
import com.guerra.dao.xml.DaoFactoryXml;
import com.guerra.model.Item;
import com.guerra.model.TotalImpuestos;
import com.guerra.model.dto.ConfigFelDto;
import lombok.extern.log4j.Log4j2;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;


@Log4j2
public class ExportFelToPdfServiceImpl implements ExportFelToPdfService {

    private final String FEL_PDF_EXPORT_PATH;
    private final String PATH_TO_RESOURCES;
    private final ConfigFelDto configFelDto;
    private final String URL_VERIFICADOR_FEL;

    private EmisorDao emisorDao;
    private CertificacionDao certificacionDao;
    private ReceptorDao receptorDao;
    private TotalesDao totalesDao;
    private DatosGeneralesDao datosGeneralesDao;
    private ItemsDao itemsDao;

    private static final String NAME_OF_JASPER_FILE = "plantilla_fel.jrxml";
    private HashMap<String, Object> parameters;
    InputStream inputStream;
    JasperReport jasperReport;
    JasperPrint jasperPrint;


    public ExportFelToPdfServiceImpl(String felPdfExportPath, String pathToResources, ConfigFelDto configFelDto, String urlVerificadorFel) {
        FEL_PDF_EXPORT_PATH = felPdfExportPath;
        PATH_TO_RESOURCES = pathToResources;
        this.configFelDto = configFelDto;
        URL_VERIFICADOR_FEL = urlVerificadorFel;
    }


    @Override
    public void exportXml(String fileName) throws JAXBException, JRException {

        log.info("=========== [ Exportando archivo FEL a PDF ] ===========");

        loadDaosFromSource(fileName);

        configureParameters();

        loadTemplateFromResources();

        compileTemplate();

        fillTemplateWithData();

        JasperExportManager.exportReportToPdfFile(jasperPrint, FEL_PDF_EXPORT_PATH + File.separator + buildFileNameWithReceptorAndDateTime());
        log.info("=========== [ Archivo FEL exportado a PDF ] ===========");

    }


    private void loadDaosFromSource(String fileName) throws JAXBException {

        log.info("Cargando fuente de datos archivo: " + fileName);
        DaoFactory daoFactoryXml = new DaoFactoryXml(fileName);

        emisorDao = daoFactoryXml.getEmisorDao();
        certificacionDao = daoFactoryXml.getCertificacionDao();
        receptorDao = daoFactoryXml.getReceptorDao();
        totalesDao = daoFactoryXml.getTotalesDao();
        datosGeneralesDao = daoFactoryXml.getDatosGeneralesDao();
        itemsDao = daoFactoryXml.getItemsDao();
    }

    private void configureParameters() {
        log.info("configurando parametros...");
        parameters = new HashMap<>();

        parameters.put("TEXT_AFILIACION", emisorDao.read().getAfiliacionIVA());
        parameters.put("TEXT_EMPRESA", emisorDao.read().getNombreComercial());
        parameters.put("TEXT_NIT_EMISOR", emisorDao.read().getNITEmisor());
        parameters.put("TEXT_EMISOR", emisorDao.read().getNombreEmisor());
        parameters.put("TEXT_DIRECCION_EMPRESA", emisorDao.read().getDireccionEmisor().getDireccion());

        parameters.put("IMG_LOGO", PATH_TO_RESOURCES + File.separator + configFelDto.getHeaderLogoName());
        parameters.put("IMG_ENCABEZADO", PATH_TO_RESOURCES + File.separator + "0.jpg");
        parameters.put("IMG_FB_LOGO", PATH_TO_RESOURCES + File.separator + "1.png");
        parameters.put("IMG_WHAT_LOGO", PATH_TO_RESOURCES + File.separator + "2.png");
        parameters.put("IMG_MAIL_LOGO", PATH_TO_RESOURCES + File.separator + "3.png");
        parameters.put("IMG_TEL_LOGO", PATH_TO_RESOURCES + File.separator + "4.png");
        parameters.put("TEXT_LINK_FB", configFelDto.getFooterSocialFacebook());
        parameters.put("TXT_TEL_WHAT", configFelDto.getFooterContactTels());
        parameters.put("TXT_CORREO", configFelDto.getFooterContactEmail());

        String urlVerificador = URL_VERIFICADOR_FEL +
                "?" +
                "tipo=autorizacion" +
                "&" +
                "numero=" + certificacionDao.read().getNumeroAutorizacion() +
                "&" +
                "emisor=" + emisorDao.read().getNITEmisor() +
                "&" +
                "receptor=" + receptorDao.read().getIDReceptor() +
                "&" +
                "monto=" + totalesDao.read().getGranTotal();
        parameters.put("TEXT_LINK_QR", urlVerificador);

        parameters.put("TEXT_AUTORIZACION", certificacionDao.read().getNumeroAutorizacion().getText());
        parameters.put("TEXT_SERIE", certificacionDao.read().getNumeroAutorizacion().getSerie());
        parameters.put("TEXT_DTE", certificacionDao.read().getNumeroAutorizacion().getNumero());

        parameters.put("TEXT_NIT_RECEPTOR", receptorDao.read().getIDReceptor());
        parameters.put("TEXT_NOMBRE_RECEPTOR", receptorDao.read().getNombreReceptor());
        parameters.put("DATE_FH_EMISION", datosGeneralesDao.read().getFechaHoraEmision());
        parameters.put("DATE_FH_CERTIFICACION", certificacionDao.read().getFechaHoraCertificacion());
        parameters.put("TEXT_MONEDA", datosGeneralesDao.read().getCodigoMoneda());

        List<Item> items = itemsDao.read().getItem();
        Double totalDescuento = items
                .stream()
                .mapToDouble(Item::getDescuento)
                .sum();

        parameters.put("DETALLES", new JRBeanCollectionDataSource(items));
        parameters.put("NUM_TOT_DESCUENTO", totalDescuento);
        parameters.put("NUM_GRAN_TOTAL", totalesDao.read().getGranTotal());

        TotalImpuestos totalImpuestos = totalesDao.read().getTotalImpuestos();
        if (totalImpuestos != null) {
            parameters.put("TEXT_NOMBRE_CORTO", totalImpuestos.getTotalImpuesto().get(0).getNombreCorto());
            parameters.put("NUM_MONTO_IMPUESTO", totalImpuestos.getTotalImpuesto().get(0).getTotalMontoImpuesto());
        }

        parameters.put("TEXT_CERTIFICADOR", certificacionDao.read().getNombreCertificador());
        parameters.put("TEXT_NIT_CERTIFICADOR", certificacionDao.read().getNITCertificador());
    }

    private void loadTemplateFromResources() throws JRException {
        log.info("cargando plantilla de reporte...");
        inputStream = App.class.getResourceAsStream("/" + NAME_OF_JASPER_FILE);
    }

    private void compileTemplate() throws JRException {
        jasperReport = JasperCompileManager.compileReport(inputStream);
    }

    private void fillTemplateWithData() throws JRException {
        log.info("generando reporte...");
        jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
    }

    private String buildFileNameWithReceptorAndDateTime() {
        String receptor = receptorDao.read().getNombreReceptor();
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss"));
        return dateTime + " " + receptor + ".pdf";
    }
}
