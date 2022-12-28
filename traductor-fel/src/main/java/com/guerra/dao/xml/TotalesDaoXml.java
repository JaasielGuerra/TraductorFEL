package com.guerra.dao.xml;

import com.guerra.dao.TotalesDao;
import com.guerra.model.GTDocumento;
import com.guerra.model.Totales;

public class TotalesDaoXml extends GenericDaoXml<Totales> implements TotalesDao {

    public TotalesDaoXml(GTDocumento gtDocumento) {
        super(gtDocumento);
    }

    @Override
    public Totales read() {
        return gtDocumento
                .getSat()
                .getDte()
                .getDatosEmision()
                .getTotale();
    }


}
