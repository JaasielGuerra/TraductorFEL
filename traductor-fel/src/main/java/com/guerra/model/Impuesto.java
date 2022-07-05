package com.guerra.model;

import javax.xml.bind.annotation.XmlElement;

public class Impuesto {
    private String NombreCorto;
    private Integer CodigoUnidadGravable;
    private Double MontoGravable;
    private Double MontoImpuesto;

    @XmlElement(name = "NombreCorto", namespace = "http://www.sat.gob.gt/dte/fel/0.2.0")
    public String getNombreCorto() {
        return NombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        NombreCorto = nombreCorto;
    }

    @XmlElement(name = "CodigoUnidadGravable", namespace = "http://www.sat.gob.gt/dte/fel/0.2.0")
    public Integer getCodigoUnidadGravable() {
        return CodigoUnidadGravable;
    }

    public void setCodigoUnidadGravable(Integer codigoUnidadGravable) {
        CodigoUnidadGravable = codigoUnidadGravable;
    }

    @XmlElement(name = "MontoGravable", namespace = "http://www.sat.gob.gt/dte/fel/0.2.0")
    public Double getMontoGravable() {
        return MontoGravable;
    }

    public void setMontoGravable(Double montoGravable) {
        MontoGravable = montoGravable;
    }

    @XmlElement(name = "MontoImpuesto", namespace = "http://www.sat.gob.gt/dte/fel/0.2.0")
    public Double getMontoImpuesto() {
        return MontoImpuesto;
    }

    public void setMontoImpuesto(Double montoImpuesto) {
        MontoImpuesto = montoImpuesto;
    }

}
