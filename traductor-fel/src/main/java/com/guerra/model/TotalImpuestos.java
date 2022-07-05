package com.guerra.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class TotalImpuestos {

    private List<TotalImpuesto> totalImpuesto;

    @XmlElement(name = "TotalImpuesto", namespace = "http://www.sat.gob.gt/dte/fel/0.2.0")
    public List<TotalImpuesto> getTotalImpuesto() {
        return totalImpuesto;
    }

    public void setTotalImpuesto(List<TotalImpuesto> totalImpuesto) {
        this.totalImpuesto = totalImpuesto;
    }

}
