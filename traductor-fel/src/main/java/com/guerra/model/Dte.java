package com.guerra.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Dte {

    private String id;
    private DatosEmision datosEmision;
    private Certificacion Certificacion;

    @XmlAttribute(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name = "DatosEmision", namespace = "http://www.sat.gob.gt/dte/fel/0.2.0")
    public DatosEmision getDatosEmision() {
        return datosEmision;
    }

    public void setDatosEmision(DatosEmision datosEmision) {
        this.datosEmision = datosEmision;
    }

    @XmlElement(name = "Certificacion", namespace = "http://www.sat.gob.gt/dte/fel/0.2.0")
    public Certificacion getCertificacion() {
        return Certificacion;
    }

    public void setCertificacion(Certificacion certificacion) {
        Certificacion = certificacion;
    }



}
