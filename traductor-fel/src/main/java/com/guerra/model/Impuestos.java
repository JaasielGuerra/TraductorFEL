package com.guerra.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Impuestos {
    private List<Impuesto> Impuesto;

    @XmlElement(name = "Impuesto", namespace = "http://www.sat.gob.gt/dte/fel/0.2.0")
    public List<Impuesto> getImpuesto() {
        return Impuesto;
    }

    public void setImpuesto(List<Impuesto> impuesto) {
        Impuesto = impuesto;
    }

}
