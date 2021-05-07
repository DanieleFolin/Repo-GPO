package com.gpo.agenziaviaggi.classes;

public class Volo {

    private int id;
    private int nPosti;
    private VoloDaLista voloDaLista;

    public Volo(int id, int nPosti, VoloDaLista voloDaLista) {
        this.id = id;
        this.nPosti = nPosti;
        this.voloDaLista = voloDaLista;
    }

    public int getId() {
        return id;
    }

    public int getnPosti() {
        return nPosti;
    }

    public void setnPosti(int nPosti) {
        this.nPosti = nPosti;
    }

    public VoloDaLista getVoloDaLista() {
        return voloDaLista;
    }

    public void setVoloDaLista(VoloDaLista voloDaLista) {
        this.voloDaLista = voloDaLista;
    }
}
