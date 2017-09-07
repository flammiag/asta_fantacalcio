/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.page;

import com.primefaces.hibernate.bean.Giocatore;
import com.show.view.data.DataListGiocatori;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "ricercaGiocatoriController")
@SessionScoped
/**
 *
 * @author sviluppo3
 */
public class RicercaGiocatoriController implements Serializable {

    private static final Logger logger = Logger.getLogger(RicercaGiocatoriController.class);

    private ArrayList<Giocatore> risultatoRicerca;
    private String text1;
    private UploadedFile file;

    public RicercaGiocatoriController() {
    }

    public void ricercaGiocatore() throws IOException {
        DataListGiocatori dataList = new DataListGiocatori();
        risultatoRicerca = new ArrayList<Giocatore>();
        for (Giocatore element : dataList.leggiListaGiocatori()) {
            if (text1.toUpperCase().equalsIgnoreCase(element.getNome())) {
                
                risultatoRicerca.add(element);
            }
        }

    }
    public void upload() {
        if(file != null) {
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public ArrayList<Giocatore> getRisultatoRicerca() {
        return risultatoRicerca;
    }

    public void setRisultatoRicerca(ArrayList<Giocatore> risultatoRicerca) {
        this.risultatoRicerca = risultatoRicerca;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    
}
