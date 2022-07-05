package com.guerra.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;


public class Sat {
    
    private String ClaseDocumento;
    private Dte dte;
    
    @XmlAttribute(name = "ClaseDocumento")
    public String getClaseDocumento() {
        return ClaseDocumento;
    }
    
    public void setClaseDocumento(String claseDocumento) {
        ClaseDocumento = claseDocumento;
    }
    
    @XmlElement(name = "DTE", namespace = "http://www.sat.gob.gt/dte/fel/0.2.0")
    public Dte getDte() {
        return dte;
    }

    public void setDte(Dte dte) {
        this.dte = dte;
    }

    
}
