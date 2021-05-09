package com.example.agenziaviaggiapp;

public class CompagniaAerea {

    private final int id;
    private String nome;
    private String nazione;
    private int tipoDiCompagnia;

    public CompagniaAerea(int id, String nome, int tipoDiCompagnia) {
        this.id = id;
        this.nome = nome;
        this.tipoDiCompagnia = tipoDiCompagnia;
    }

    public CompagniaAerea(int id, String nome, String nazione, int tipoDiCompagnia) {
        this(id, nome, tipoDiCompagnia);
        this.nazione = nazione;
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

    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    public int getTipoDiCompagnia() {
        return tipoDiCompagnia;
    }

    public void setTipoDiCompagnia(int tipoDiCompagnia) {
        this.tipoDiCompagnia = tipoDiCompagnia;
    }
}