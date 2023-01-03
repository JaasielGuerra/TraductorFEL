package com.guerra.dao.xml;

import com.guerra.dao.DatosGeneralesDao;
import com.guerra.model.DatosGenerales;
import com.guerra.model.GTDocumento;

public class DatosGeneralesDaoXml extends GenericDaoXml<DatosGenerales> implements DatosGeneralesDao {

    public DatosGeneralesDaoXml(GTDocumento gtDocumento) {
        super(gtDocumento);
    }

    @Override
    public DatosGenerales read() {
        return gtDocumento
                .getSat()
                .getDte()
                .getDatosEmision()
                .getDatosGenerales();
    }
}
