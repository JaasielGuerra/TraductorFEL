package com.guerra.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Item {
    private String BienOServicio;
    private Integer NumeroLinea;
    private Double Cantidad;
    private String UnidadMedida;
    private String Descripcion;
    private Double PrecioUnitario;
    private Double Precio;
    private Double Descuento;
    private Impuestos Impuestos;
    private Double Total;

    @XmlAttribute(name = "BienOServicio")
    public String getBienOServicio() {
        return BienOServicio;
    }

    public void setBienOServicio(String bienOServicio) {
        BienOServicio = bienOServicio;
    }

    @XmlAttribute(name = "NumeroLinea")
    public Integer getNumeroLinea() {
        return NumeroLinea;
    }

    public void setNumeroLinea(Integer numeroLinea) {
        NumeroLinea = numeroLinea;
    }

    @XmlElement(name = "Cantidad", namespace = "http://www.sat.gob.gt/dte/fel/0.2.0")
    public Double getCantidad() {
        return Cantidad;
    }

    public void setCantidad(Double cantidad) {
        Cantidad = cantidad;
    }

    @XmlElement(name = "UnidadMedida", namespace = "http://www.sat.gob.gt/dte/fel/0.2.0")
    public String getUnidadMedida() {
        return UnidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        UnidadMedida = unidadMedida;
    }

    @XmlElement(name = "Descripcion", namespace = "http://www.sat.gob.gt/dte/fel/0.2.0")
    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    @XmlElement(name = "PrecioUnitario", namespace = "http://www.sat.gob.gt/dte/fel/0.2.0")
    public Double getPrecioUnitario() {
        return PrecioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        PrecioUnitario = precioUnitario;
    }

    @XmlElement(name = "Precio", namespace = "http://www.sat.gob.gt/dte/fel/0.2.0")
    public Double getPrecio() {
        return Precio;
    }

    public void setPrecio(Double precio) {
        Precio = precio;
    }

    @XmlElement(name = "Descuento", namespace = "http://www.sat.gob.gt/dte/fel/0.2.0")
    public Double getDescuento() {
        return Descuento;
    }

    public void setDescuento(Double descuento) {
        Descuento = descuento;
    }

    @XmlElement(name = "Total", namespace = "http://www.sat.gob.gt/dte/fel/0.2.0")
    public Double getTotal() {
        return Total;
    }

    public void setTotal(Double total) {
        Total = total;
    }

    @XmlElement(name = "Impuestos", namespace = "http://www.sat.gob.gt/dte/fel/0.2.0")
    public Impuestos getImpuestos() {
        return Impuestos;
    }

    public void setImpuestos(Impuestos impuestos) {
        Impuestos = impuestos;
    }

}
