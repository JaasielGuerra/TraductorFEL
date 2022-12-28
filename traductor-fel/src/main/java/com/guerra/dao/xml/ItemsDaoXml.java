package com.guerra.dao.xml;

import com.guerra.dao.ItemsDao;
import com.guerra.model.GTDocumento;
import com.guerra.model.Items;

public class ItemsDaoXml extends GenericDaoXml<Items> implements ItemsDao {


    public ItemsDaoXml(GTDocumento gtDocumento) {
        super(gtDocumento);
    }

    @Override
    public Items read() {
        return gtDocumento
                .getSat()
                .getDte()
                .getDatosEmision()
                .getItems();
    }
}
