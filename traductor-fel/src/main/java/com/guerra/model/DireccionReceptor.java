package com.guerra.model;

import javax.xml.bind.annotation.XmlElement;

public class DireccionReceptor {

    private String Direccion;
    private String CodigoPostal;
    private String Municipio;
    private String Departamento;
    private String Pais;

    @XmlElement(name = "Direccion", namespace = "http://www.sat.gob.gt/dte/fel/0.2.0")
    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    @XmlElement(name = "CodigoPostal", namespace = "http://www.sat.gob.gt/dte/fel/0.2.0")
    public String getCodigoPostal() {
        return CodigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        CodigoPostal = codigoPostal;
    }

    @XmlElement(name = "Municipio", namespace = "http://www.sat.gob.gt/dte/fel/0.2.0")
    public String getMunicipio() {
        return Municipio;
    }

    public void setMunicipio(String municipio) {
        Municipio = municipio;
    }

    @XmlElement(name = "Departamento", namespace = "http://www.sat.gob.gt/dte/fel/0.2.0")
    public String getDepartamento() {
        return Departamento;
    }

    public void setDepartamento(String departamento) {
        Departamento = departamento;
    }

    @XmlElement(name = "Pais", namespace = "http://www.sat.gob.gt/dte/fel/0.2.0")
    public String getPais() {
        return Pais;
    }

    public void setPais(String pais) {
        Pais = pais;
    }

}
