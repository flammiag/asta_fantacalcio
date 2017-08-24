/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.show.view.data;

import com.controller.page.RicercaGiocatoriController;
import com.primefaces.hibernate.bean.Giocatore;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author sviluppo3
 */
@ManagedBean
@ViewScoped
public class DataListGiocatori implements Serializable {

    private static final Logger logger = Logger.getLogger(DataListGiocatori.class.getName());
    private ArrayList<Giocatore> listGiocatori = new ArrayList<Giocatore>();

    @PostConstruct
    public void init() {
        try {
            setListGiocatori(leggiListaGiocatori());
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(RicercaGiocatoriController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Giocatore> leggiListaGiocatori() throws FileNotFoundException, IOException {
        ArrayList<Giocatore> listaGiocatori = new ArrayList<Giocatore>();
        CSVParser parser = new CSVParser(
                new FileReader("C:\\temp\\Lista.csv"),
                CSVFormat.DEFAULT.withHeader());
        for (CSVRecord record : parser) {
            Giocatore giocatore = new Giocatore();
            giocatore.setCodice(record.get("Codice"));
            giocatore.setSquadra(record.get("Club"));
            giocatore.setNome(record.get("Giocatore"));
            if ("P".equals(record.get("Ruolo1"))) {
                giocatore.setRuolo1("Portiere");
            }
            if ("D".equals(record.get("Ruolo1"))) {
                giocatore.setRuolo1("Difensore");
            }
            if ("C".equals(record.get("Ruolo1"))) {
                giocatore.setRuolo1("Centrocampista");
            }
            if ("A".equals(record.get("Ruolo1"))) {
                giocatore.setRuolo1("Attaccante");
            }
            giocatore.setRuolo2(record.get("Ruolo2"));
            listaGiocatori.add(giocatore);

            logger.info("<<< " + record.toString());

        }
        parser.close();

        return listaGiocatori;

    }

    public ArrayList<Giocatore> getListGiocatori() {
        return listGiocatori;
    }

    public void setListGiocatori(ArrayList<Giocatore> listGiocatori) {
        this.listGiocatori = listGiocatori;
    }

}
