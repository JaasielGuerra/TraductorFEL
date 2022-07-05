package com.guerra.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Receptor
 */
public class Receptor {

    private String CorreoReceptor;
    private String IDReceptor;
    private String NombreReceptor;
    private DireccionReceptor DireccionReceptor;

    @XmlAttribute(name = "CorreoReceptor")
    public String getCorreoReceptor() {
        return CorreoReceptor;
    }

    public void setCorreoReceptor(String correoReceptor) {
        CorreoReceptor = correoReceptor;
    }

    @XmlAttribute(name = "IDReceptor")
    public String getIDReceptor() {
        return IDReceptor;
    }

    public void setIDReceptor(String iDReceptor) {
        IDReceptor = iDReceptor;
    }

    @XmlAttribute(name = "NombreReceptor")
    public String getNombreReceptor() {
        return NombreReceptor;
    }

    public void setNombreReceptor(String nombreReceptor) {
        NombreReceptor = nombreReceptor;
    }

    @XmlElement(name = "DireccionReceptor", namespace = "http://www.sat.gob.gt/dte/fel/0.2.0")
    public DireccionReceptor getDireccionReceptor() {
        return DireccionReceptor;
    }

    public void setDireccionReceptor(DireccionReceptor direccionReceptor) {
        DireccionReceptor = direccionReceptor;
    }

}