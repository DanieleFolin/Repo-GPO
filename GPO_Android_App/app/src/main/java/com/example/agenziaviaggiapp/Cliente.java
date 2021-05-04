package com.example.agenziaviaggiapp;

import java.util.ArrayList;

public class Cliente {

    private String username;
    private String password;
    private ArrayList<Prenotazione> prenotazioni;

    public Cliente(){
        prenotazioni = new ArrayList<>();
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

    public void addPrenotazione(Prenotazione nuovaPrenotazione){ this.prenotazioni.add(nuovaPrenotazione); }

    public Prenotazione getPrenotazione(int index){ return this.prenotazioni.get(index); }
}
