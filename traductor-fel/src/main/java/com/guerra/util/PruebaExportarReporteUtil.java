package com.guerra.util;

import com.guerra.App;
import com.guerra.model.GTDocumento;
import com.guerra.model.Item;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public class PruebaExportarReporteUtil {

    public static void main(String[] args) throws JAXBException, JRException {


        String path = System.getProperty("user.dir");
        String pathResources = path + File.separator + "resources";

        JAXBContext jaxbContext = JAXBContext.newInstance(GTDocumento.class);

        GTDocumento fel = (GTDocumento) jaxbContext.createUnmarshaller()
                .unmarshal(new File("/home/jaasiel/Descargas/52EE7645-6D79-489E-9D4D-897641FD23BA.xml"));

        /**
         * Cargar plantilla jasper, compilarla, enviar parametros y exportar a PDF
         *
         */
        InputStream inputStream = App.class.getResourceAsStream("/plantilla_fel.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("TEXT_AFILIACION", fel.getSat().getDte().getDatosEmision().getEmisor().getAfiliacionIVA());
        parameters.put("TEXT_EMPRESA", fel.getSat().getDte().getDatosEmision().getEmisor().getNombreComercial());
        parameters.put("TEXT_NIT_EMISOR", fel.getSat().getDte().getDatosEmision().getEmisor().getNITEmisor());
        parameters.put("TEXT_EMISOR", fel.getSat().getDte().getDatosEmision().getEmisor().getNombreEmisor());
        parameters.put("TEXT_DIRECCION_EMPRESA", fel.getSat().getDte().getDatosEmision().getEmisor().getDireccionEmisor().getDireccion());

        parameters.put("IMG_LOGO", pathResources + File.separator + "5.png");
        parameters.put("IMG_ENCABEZADO", pathResources + File.separator + "0.jpg");
        parameters.put("IMG_FB_LOGO", pathResources + File.separator + "1.png");
        parameters.put("IMG_WHAT_LOGO", pathResources + File.separator + "2.png");
        parameters.put("IMG_TEL_LOGO", pathResources + File.separator + "4.png");
        parameters.put("IMG_MAIL_LOGO", pathResources + File.separator + "3.png");

        parameters.put("TEXT_LINK_FB", "https://www.facebook.com/AlfayOmegaGZ");
        parameters.put("TXT_TEL_WHAT", "7933-1972 / 5300-3474");
        parameters.put("TXT_CORREO", "alfayomegagualan@gmail.com");

        parameters.put("TEXT_LINK_QR", "https://felpub.c.sat.gob.gt/verificador-web/publico/vistas/verificacionDte.jsf?tipo=autorizacion&numero="
                + fel.getSat().getDte().getCertificacion().getNumeroAutorizacion().getText()
                + "&emisor="
                + fel.getSat().getDte().getDatosEmision().getEmisor().getNITEmisor()
                + "&receptor="
                + fel.getSat().getDte().getDatosEmision().getReceptor().getIDReceptor()
                + "&monto="
                + fel.getSat().getDte().getDatosEmision().getTotale().getGranTotal());

        parameters.put("TEXT_AUTORIZACION", fel.getSat().getDte().getCertificacion().getNumeroAutorizacion().getText());
        parameters.put("TEXT_SERIE", fel.getSat().getDte().getCertificacion().getNumeroAutorizacion().getSerie());
        parameters.put("TEXT_DTE", fel.getSat().getDte().getCertificacion().getNumeroAutorizacion().getNumero());

        parameters.put("TEXT_NIT_RECEPTOR", fel.getSat().getDte().getDatosEmision().getReceptor().getIDReceptor());
        parameters.put("TEXT_NOMBRE_RECEPTOR", fel.getSat().getDte().getDatosEmision().getReceptor().getNombreReceptor());
        parameters.put("DATE_FH_EMISION", fel.getSat().getDte().getDatosEmision().getDatosGenerales().getFechaHoraEmision());
        parameters.put("DATE_FH_CERTIFICACION", fel.getSat().getDte().getCertificacion().getFechaHoraCertificacion());
        parameters.put("TEXT_MONEDA", fel.getSat().getDte().getDatosEmision().getDatosGenerales().getCodigoMoneda());

        List<Item> items = fel.getSat().getDte().getDatosEmision().getItems().getItem();
        Double totalDescuento = items
                .stream()
                .mapToDouble(Item::getDescuento)
                .sum();

        parameters.put("DETALLES", new JRBeanCollectionDataSource(items));
        parameters.put("NUM_TOT_DESCUENTO", totalDescuento);
        parameters.put("NUM_GRAN_TOTAL", fel.getSat().getDte().getDatosEmision().getTotale().getGranTotal());

        //parameters.put("TEXT_NOMBRE_CORTO", fel.getSat().getDte().getDatosEmision().getTotale().getTotalImpuestos().getTotalImpuesto().get(0).getNombreCorto());
        //parameters.put("NUM_MONTO_IMPUESTO", fel.getSat().getDte().getDatosEmision().getTotale().getTotalImpuestos().getTotalImpuesto().get(0).getTotalMontoImpuesto());

        parameters.put("TEXT_CERTIFICADOR", fel.getSat().getDte().getCertificacion().getNombreCertificador());
        parameters.put("TEXT_NIT_CERTIFICADOR", fel.getSat().getDte().getCertificacion().getNITCertificador());

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
        JasperExportManager.exportReportToPdfFile(jasperPrint, "/home/jaasiel/Descargas/FEL.pdf");
    }
}
