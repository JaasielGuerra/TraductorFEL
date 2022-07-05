package com.guerra.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

public class Certificacion {

    private String NITCertificador;
    private String NombreCertificador;
    private NumeroAutorizacion NumeroAutorizacion;
    private Date FechaHoraCertificacion;

    @XmlElement(name = "NITCertificador", namespace = "http://www.sat.gob.gt/dte/fel/0.2.0")
    public String getNITCertificador() {
        return NITCertificador;
    }

    public void setNITCertificador(String nITCertificador) {
        NITCertificador = nITCertificador;
    }

    @XmlElement(name = "NombreCertificador", namespace = "http://www.sat.gob.gt/dte/fel/0.2.0")
    public String getNombreCertificador() {
        return NombreCertificador;
    }

    public void setNombreCertificador(String nombreCertificador) {
        NombreCertificador = nombreCertificador;
    }

    @XmlElement(name = "NumeroAutorizacion", namespace = "http://www.sat.gob.gt/dte/fel/0.2.0")
    public NumeroAutorizacion getNumeroAutorizacion() {
        return NumeroAutorizacion;
    }

    public void setNumeroAutorizacion(NumeroAutorizacion numeroAutorizacion) {
        NumeroAutorizacion = numeroAutorizacion;
    }

    @XmlElement(name = "FechaHoraCertificacion", namespace = "http://www.sat.gob.gt/dte/fel/0.2.0")
    public Date getFechaHoraCertificacion() {
        return FechaHoraCertificacion;
    }

    public void setFechaHoraCertificacion(Date fechaHoraCertificacion) {
        FechaHoraCertificacion = fechaHoraCertificacion;
    }

}
