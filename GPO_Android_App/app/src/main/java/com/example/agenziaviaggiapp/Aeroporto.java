package com.example.agenziaviaggiapp;

public class Aeroporto {

    private final int id;
    private String nome;
    private String luogo;

    public Aeroporto(int id, String nome, String luogo) {
        this.id = id;
        this.nome = nome;
        this.luogo = luogo;
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

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }
}
