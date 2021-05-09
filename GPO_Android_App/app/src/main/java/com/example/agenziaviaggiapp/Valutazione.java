package com.example.agenziaviaggiapp;

public class Valutazione {

    private Utente utenteValutatore;
    private String descrizione;
    private int voto;

    public Valutazione(Utente utente, String descrizione, int voto){
        this.utenteValutatore = utente;
        this.descrizione = descrizione;
        this.voto = voto;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getVoto() {
        return voto;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }
}
