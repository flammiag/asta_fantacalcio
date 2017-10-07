/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primefaces.hibernate.bean;

import java.io.Serializable;

/**
 *
 * @author sviluppo3
 */
public class Giocatore implements Serializable{
    
    private Integer idgiocatore;
    private String codice;
    private String nome;
    private String ruolo1;
    private String ruolo2;
    private String squadra;
    private String fantasquadra;

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRuolo1() {
        return ruolo1;
    }

    public void setRuolo1(String ruolo1) {
        this.ruolo1 = ruolo1;
    }

    public String getRuolo2() {
        return ruolo2;
    }

    public void setRuolo2(String ruolo2) {
        this.ruolo2 = ruolo2;
    }

    public String getSquadra() {
        return squadra;
    }

    public void setSquadra(String squadra) {
        this.squadra = squadra;
    }

    public String getFantasquadra() {
        return fantasquadra;
    }

    public void setFantasquadra(String fantasquadra) {
        this.fantasquadra = fantasquadra;
    }

    public Integer getIdgiocatore() {
        return idgiocatore;
    }

    public void setIdgiocatore(Integer idgiocatore) {
        this.idgiocatore = idgiocatore;
    }
    
    
    
    
}
