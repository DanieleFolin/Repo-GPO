package com.example.agenziaviaggiapp;

class Prenotazione {

    private Viaggio viaggio;
    private float prezzo;
    private Valutazione valutazione;

    public Prenotazione(Viaggio nuovoViaggio, float prezzo){
        this.viaggio = nuovoViaggio;
        this.prezzo = prezzo;
    }

    public Viaggio getViaggio() {
        return viaggio;
    }

    public void setViaggio(Viaggio viaggio) {
        this.viaggio = viaggio;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public Valutazione getValutazione() {
        return valutazione;
    }

    public void setValutazione(Valutazione valutazione) {
        this.valutazione = valutazione;
    }
}
