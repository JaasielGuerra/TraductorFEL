package com.guerra.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GTDocumento", namespace = "http://www.sat.gob.gt/dte/fel/0.2.0")
public class GTDocumento {

    private Sat sat;

    @XmlElement(name = "SAT", namespace="http://www.sat.gob.gt/dte/fel/0.2.0")
    public Sat getSat() {
        return sat;
    }

    public void setSat(Sat sat) {
        this.sat = sat;
    }

}
