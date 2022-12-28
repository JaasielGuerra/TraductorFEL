package com.guerra.dao.xml;

import com.guerra.dao.EmisorDao;
import com.guerra.model.Emisor;
import com.guerra.model.GTDocumento;

public class EmisorDaoXml extends GenericDaoXml<Emisor> implements EmisorDao {


    public EmisorDaoXml(GTDocumento gtDocumento) {
        super(gtDocumento);
    }

    @Override
    public Emisor read() {
        return gtDocumento
                .getSat()
                .getDte()
                .getDatosEmision()
                .getEmisor();

    }

}
