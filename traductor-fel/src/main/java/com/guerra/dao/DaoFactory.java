package com.guerra.dao;

public abstract class DaoFactory {
    public static DaoFactory daoFactory = null;

    public static DaoFactory getDaoFactory() {
        assert daoFactory != null;
        return daoFactory;
    }

    public static void setDaoFactory(DaoFactory daoFactory) {
        DaoFactory.daoFactory = daoFactory;
    }

    public abstract EmisorDao getEmisorDao();
    public abstract ReceptorDao getReceptorDao();
    public abstract CertificacionDao getCertificacionDao();
    public abstract ItemsDao getItemsDao();
    public abstract TotalesDao getTotalesDao();


}
