package com.guerra.dao.xml;

import com.guerra.dao.GenericDao;
import com.guerra.dao.ReceptorDao;
import com.guerra.model.GTDocumento;
import com.guerra.model.Receptor;

public class ReceptorDaoXml extends GenericDaoXml<Receptor> implements ReceptorDao {

    public ReceptorDaoXml(GTDocumento gtDocumento) {
        super(gtDocumento);
    }

    @Override
    public Receptor read() {
        return gtDocumento
                .getSat()
                .getDte()
                .getDatosEmision()
                .getReceptor();
    }


}
