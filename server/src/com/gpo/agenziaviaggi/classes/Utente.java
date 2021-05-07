package com.gpo.agenziaviaggi.classes;

import java.util.ArrayList;

public class Utente {

    private int id;
    private String nome;
    private String cognome;
    private ArrayList<Viaggio> viaggi;

    public Utente(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }

    public Utente(int id, String nome, String cognome) {
        this(nome, cognome);
        this.id = id;
    }

    public Utente(int id, String nome, String cognome, ArrayList<Viaggio> prenotazioni) {
        this(id, nome, cognome);
        this.viaggi = prenotazioni;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public ArrayList<Viaggio> getViaggi() {
        return viaggi;
    }

    public void setViaggi(ArrayList<Viaggio> viaggi) {
        this.viaggi = viaggi;
    }
}
