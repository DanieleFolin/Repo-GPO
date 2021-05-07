package com.gpo.agenziaviaggi.classes;

import java.time.LocalDate;
import java.util.ArrayList;

public class Viaggio {

    private int id;
    private float prezzo;
    private float valutazione;
    private LocalDate dataPartenza;
    private LocalDate dataRitorno;
    private ArrayList<Volo> voli;

    public Viaggio(int id, float prezzo, LocalDate dataPartenza, LocalDate dataRitorno, ArrayList<Volo> voli) {
        this.id = id;
        this.prezzo = prezzo;
        this.dataPartenza = dataPartenza;
        this.dataRitorno = dataRitorno;
        this.voli = voli;
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

    public LocalDate getDataPartenza() {
        return dataPartenza;
    }

    public void setDataPartenza(LocalDate dataPartenza) {
        this.dataPartenza = dataPartenza;
    }

    public LocalDate getDataRitorno() {
        return dataRitorno;
    }

    public void setDataRitorno(LocalDate dataRitorno) {
        this.dataRitorno = dataRitorno;
    }

    public ArrayList<Volo> getVoli() {
        return voli;
    }

    public void setVoli(ArrayList<Volo> voli) {
        this.voli = voli;
    }
}
