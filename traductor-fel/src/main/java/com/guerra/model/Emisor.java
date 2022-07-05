package com.guerra.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Emisor {
    private String AfiliacionIVA;
    private Integer CodigoEstablecimiento;
    private String CorreoEmisor;
    private String NITEmisor;
    private String NombreComercial;
    private String NombreEmisor;
    private DireccionEmisor DireccionEmisor;

    @XmlAttribute(name = "AfiliacionIVA")
    public String getAfiliacionIVA() {
        return AfiliacionIVA;
    }

    public void setAfiliacionIVA(String afiliacionIVA) {
        AfiliacionIVA = afiliacionIVA;
    }

    @XmlAttribute(name = "CodigoEstablecimiento")
    public Integer getCodigoEstablecimiento() {
        return CodigoEstablecimiento;
    }

    public void setCodigoEstablecimiento(Integer codigoEstablecimiento) {
        CodigoEstablecimiento = codigoEstablecimiento;
    }

    @XmlAttribute(name = "CorreoEmisor")
    public String getCorreoEmisor() {
        return CorreoEmisor;
    }

    public void setCorreoEmisor(String correoEmisor) {
        CorreoEmisor = correoEmisor;
    }

    @XmlAttribute(name = "NITEmisor")
    public String getNITEmisor() {
        return NITEmisor;
    }

    public void setNITEmisor(String nITEmisor) {
        NITEmisor = nITEmisor;
    }

    @XmlAttribute(name = "NombreComercial")
    public String getNombreComercial() {
        return NombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        NombreComercial = nombreComercial;
    }

    @XmlAttribute(name = "NombreEmisor")
    public String getNombreEmisor() {
        return NombreEmisor;
    }

    public void setNombreEmisor(String nombreEmisor) {
        NombreEmisor = nombreEmisor;
    }

    @XmlElement(name = "DireccionEmisor", namespace = "http://www.sat.gob.gt/dte/fel/0.2.0")
    public DireccionEmisor getDireccionEmisor() {
        return DireccionEmisor;
    }

    public void setDireccionEmisor(DireccionEmisor direccionEmisor) {
        DireccionEmisor = direccionEmisor;
    }

}
