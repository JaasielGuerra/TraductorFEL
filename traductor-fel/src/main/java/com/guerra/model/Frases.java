package com.guerra.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Frases {

    private List<Frase> Frase;

    @XmlElement(name = "Frase", namespace = "http://www.sat.gob.gt/dte/fel/0.2.0")
    public List<Frase> getFrase() {
        return Frase;
    }

    public void setFrase(List<Frase> frase) {
        Frase = frase;
    }

}
