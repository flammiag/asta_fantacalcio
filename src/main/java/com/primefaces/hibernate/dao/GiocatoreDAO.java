package com.primefaces.hibernate.dao;

import com.primefaces.hibernate.Idao.IGiocatoreDAO;
import com.primefaces.hibernate.bean.Giocatore;
import com.primefaces.hibernate.exception.UnableToSaveException;

import org.apache.log4j.Logger;
import com.primefaces.hibernate.generic.GenericDaoImpl;
import com.primefaces.hibernate.util.HibernateUtil;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import org.hibernate.Session;
 
public class GiocatoreDAO extends GenericDaoImpl<Giocatore, Integer> implements IGiocatoreDAO {

    private static final Logger LOG = Logger.getLogger(GiocatoreDAO.class);

 
    @Override
    public void create(Giocatore giocatore){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(giocatore);
            session.getTransaction().commit();
        } catch (UnableToSaveException e) {
            LOG.error("giocatore - () failed, " + e.getMessage(), e);
        } finally {
            session.flush();
            session.close();
        }
    }

}
