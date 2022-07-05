package com.guerra.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Items {

    private List<Item> Item;

    @XmlElement(name = "Item", namespace = "http://www.sat.gob.gt/dte/fel/0.2.0")
    public List<Item> getItem() {
        return Item;
    }

    public void setItem(List<Item> item) {
        Item = item;
    }

}
