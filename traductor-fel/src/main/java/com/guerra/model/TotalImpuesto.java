package com.guerra.model;

import javax.xml.bind.annotation.XmlAttribute;

public class TotalImpuesto {

    private String NombreCorto;
    private Double TotalMontoImpuesto;

    @XmlAttribute(name = "NombreCorto")
    public String getNombreCorto() {
        return NombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        NombreCorto = nombreCorto;
    }

    @XmlAttribute(name = "TotalMontoImpuesto")
    public Double getTotalMontoImpuesto() {
        return TotalMontoImpuesto;
    }

    public void setTotalMontoImpuesto(Double totalMontoImpuesto) {
        TotalMontoImpuesto = totalMontoImpuesto;
    }

}
