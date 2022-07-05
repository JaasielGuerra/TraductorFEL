package com.guerra.model;

import javax.xml.bind.annotation.XmlElement;

public class Totales {

    private TotalImpuestos TotalImpuestos;
    private Double GranTotal;

    @XmlElement(name="TotalImpuestos", namespace="http://www.sat.gob.gt/dte/fel/0.2.0")
    public TotalImpuestos getTotalImpuestos() {
        return TotalImpuestos;
    }

    public void setTotalImpuestos(TotalImpuestos TotalImpuestos) {
        this.TotalImpuestos = TotalImpuestos;
    }

    @XmlElement(name = "GranTotal", namespace = "http://www.sat.gob.gt/dte/fel/0.2.0")
    public Double getGranTotal() {
        return GranTotal;
    }

    public void setGranTotal(Double granTotal) {
        GranTotal = granTotal;
    }

}
