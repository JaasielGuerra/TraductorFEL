package com.guerra.model;

import javax.xml.bind.annotation.XmlAttribute;

public class Frase {
    private Integer CodigoEscenario;
    private Integer TipoFrase;

    @XmlAttribute(name = "CodigoEscenario")
    public Integer getCodigoEscenario() {
        return CodigoEscenario;
    }

    public void setCodigoEscenario(Integer codigoEscenario) {
        CodigoEscenario = codigoEscenario;
    }

    @XmlAttribute(name = "TipoFrase")
    public Integer getTipoFrase() {
        return TipoFrase;
    }

    public void setTipoFrase(Integer tipoFrase) {
        TipoFrase = tipoFrase;
    }

}
