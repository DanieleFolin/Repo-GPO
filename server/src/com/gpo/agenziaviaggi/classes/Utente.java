package com.gpo.agenziaviaggi.classes;

import java.util.ArrayList;

public class Utente {

    private int id;
    private String nome;
    private String cognome;
    private String username;
    private String password;
    private ArrayList<Viaggio> viaggi;

    public Utente(String nome, String cognome, String username, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
    }

    public Utente(int id, String nome, String cognome, String username, String password) {
        this(nome, cognome, username, password);
        this.id = id;
    }

    public Utente(int id, String nome, String cognome, String username, String password, ArrayList<Viaggio> viaggi) {
        this(id, nome, cognome, username, password);
        this.viaggi = viaggi;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
