package com.show.view.data;

import com.primefaces.hibernate.bean.Giocatore;
import com.primefaces.hibernate.dao.GiocatoreDAO;
import com.primefaces.hibernate.exception.UnableToSaveException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;

@ManagedBean
public class CaricaListaGiocatoriView {

    private static final Logger logger = Logger.getLogger(CaricaListaGiocatoriView.class.getName());
    private String destination = "C:\\temp\\";

    public void handleFileUpload(FileUploadEvent event) throws UnableToSaveException, NoSuchAlgorithmException {
        ArrayList<Giocatore> listaGiocatori = new ArrayList<>();
        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
            DataListGiocatori dataListGiocatori = new DataListGiocatori();
            listaGiocatori = dataListGiocatori.leggiListaGiocatori(destination + event.getFile().getFileName());
            
            
        } catch (IOException e) {
            logger.log(Level.WARNING, "Errore di caricamento", e.toString());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Errore","File non caricato");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        
        GiocatoreDAO giocatoreDao = new GiocatoreDAO();
        for(Giocatore giocatore : listaGiocatori  )
            giocatoreDao.create(giocatore);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Successo!", event.getFile().getFileName() + " è stato caricato.");
        FacesContext.getCurrentInstance().addMessage(FacesMessage.SEVERITY_INFO.toString(), message);
    }

    public void copyFile(String fileName, InputStream in) {
        try {

            // write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File(destination + fileName));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();

            logger.log(Level.INFO, "File Ccreato");
        } catch (IOException e) {
            logger.log(Level.WARNING, "Errore nella creazione del file: ", e.toString());
        }
    }

}
