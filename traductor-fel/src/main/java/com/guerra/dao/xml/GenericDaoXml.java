package com.guerra.dao.xml;

import com.guerra.dao.GenericDao;
import com.guerra.model.GTDocumento;

public abstract class GenericDaoXml<T> implements GenericDao<T> {

    protected final GTDocumento gtDocumento;

    //constructor
    public GenericDaoXml(GTDocumento gtDocumento) {
        this.gtDocumento = gtDocumento;
    }

}
