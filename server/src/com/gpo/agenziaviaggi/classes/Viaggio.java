package com.gpo.agenziaviaggi.classes;

import java.util.Date;

public class Viaggio {

    private final int id;
    private String nome;
    private float prezzo;
    private float valutazione;
    private Date dataPartenza;
    private Date dataRitorno;

    public Viaggio(int id, String nome, float prezzo, Date dataPartenza, Date dataRitorno) {
        this.id = id;
        this.nome = nome;
        this.prezzo = prezzo;
        this.dataPartenza = dataPartenza;
        this.dataRitorno = dataRitorno;
    }

    public Viaggio(int id, String nome, float prezzo, float valutazione, Date dataPartenza, Date dataRitorno) {
        this(id, nome, prezzo, dataPartenza, dataRitorno);
        this.valutazione = valutazione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public float getValutazione() {
        return valutazione;
    }

    public void setValutazione(float valutazione) {
        this.valutazione = valutazione;
    }

    public Date getDataPartenza() {
        return dataPartenza;
    }

    public void setDataPartenza(Date dataPartenza) {
        this.dataPartenza = dataPartenza;
    }

    public Date getDataRitorno() {
        return dataRitorno;
    }

    public void setDataRitorno(Date dataRitorno) {
        this.dataRitorno = dataRitorno;
    }

}
