package com.guerra.dao.xml;

import com.guerra.dao.CertificacionDao;
import com.guerra.model.Certificacion;
import com.guerra.model.GTDocumento;

public class CertificacionDaoXml extends GenericDaoXml<Certificacion> implements CertificacionDao {

    public CertificacionDaoXml(GTDocumento gtDocumento) {
        super(gtDocumento);
    }

    @Override
    public Certificacion read() {
        return gtDocumento
                .getSat()
                .getDte()
                .getCertificacion();
    }
}
