package com.guerra.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;

public class DatosGenerales {

    private String CodigoMoneda;
    private Date FechaHoraEmision;
    private String Tipo;

    @XmlAttribute(name = "CodigoMoneda")
    public String getCodigoMoneda() {
        return CodigoMoneda;
    }
    

    public void setCodigoMoneda(String codigoMoneda) {
        CodigoMoneda = codigoMoneda;
    }

    @XmlAttribute(name = "FechaHoraEmision")
    public Date getFechaHoraEmision() {
        return FechaHoraEmision;
    }

    
    public void setFechaHoraEmision(Date fechaHoraEmision) {
        FechaHoraEmision = fechaHoraEmision;
    }

    @XmlAttribute(name = "Tipo")
    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

}
