package com.guerra.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class DatosEmision {

    private String id;
    private DatosGenerales DatosGenerales;
    private Emisor emisor;
    private Receptor receptor;
    private Frases Frases;
    private Items Items;
    private Totales Totale;

    @XmlElement(name = "Frases", namespace = "http://www.sat.gob.gt/dte/fel/0.2.0")
    public Frases getFrases() {
        return Frases;
    }

    public void setFrases(Frases frases) {
        Frases = frases;
    }

    @XmlAttribute(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name = "Emisor", namespace = "http://www.sat.gob.gt/dte/fel/0.2.0")
    public Emisor getEmisor() {
        return emisor;
    }

    public void setEmisor(Emisor emisor) {
        this.emisor = emisor;
    }

    @XmlElement(name = "Items", namespace = "http://www.sat.gob.gt/dte/fel/0.2.0")
    public Items getItems() {
        return Items;
    }

    public void setItems(Items items) {
        Items = items;
    }

    @XmlElement(name = "DatosGenerales", namespace = "http://www.sat.gob.gt/dte/fel/0.2.0")
    public DatosGenerales getDatosGenerales() {
        return DatosGenerales;
    }

    public void setDatosGenerales(DatosGenerales datosGenerales) {
        DatosGenerales = datosGenerales;
    }

    @XmlElement(name = "Receptor", namespace = "http://www.sat.gob.gt/dte/fel/0.2.0")
    public Receptor getReceptor() {
        return receptor;
    }

    public void setReceptor(Receptor receptor) {
        this.receptor = receptor;
    }

    @XmlElement(name = "Totales", namespace = "http://www.sat.gob.gt/dte/fel/0.2.0")
    public Totales getTotale() {
        return Totale;
    }

    public void setTotale(Totales totale) {
        Totale = totale;
    }

}
