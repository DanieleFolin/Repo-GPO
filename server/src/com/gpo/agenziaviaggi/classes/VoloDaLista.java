package com.gpo.agenziaviaggi.classes;

import java.time.LocalDateTime;

public class VoloDaLista {

    private int id;
    private CompagniaAerea compagniaAerea;
    private Aeroporto aeroportoDiPartenza;
    private Aeroporto aeroportoDiArrivo;
    private LocalDateTime dataOraPartenza;
    private LocalDateTime dataOraArrivo;
    private int postiTotali;
    private int postiDisponibili;

    public VoloDaLista(int id, CompagniaAerea compagniaAerea, Aeroporto aeroportoDiPartenza, Aeroporto aeroportoDiArrivo, LocalDateTime dataOraPartenza, LocalDateTime dataOraArrivo, int postiTotali) {
        this.id = id;
        this.compagniaAerea = compagniaAerea;
        this.aeroportoDiPartenza = aeroportoDiPartenza;
        this.aeroportoDiArrivo = aeroportoDiArrivo;
        this.dataOraPartenza = dataOraPartenza;
        this.dataOraArrivo = dataOraArrivo;
        this.postiTotali = postiTotali;
        this.postiDisponibili = postiTotali;
    }

    public int getId() {
        return id;
    }

    public CompagniaAerea getCompagniaAerea() {
        return compagniaAerea;
    }

    public Aeroporto getAeroportoDiPartenza() {
        return aeroportoDiPartenza;
    }

    public Aeroporto getAeroportoDiArrivo() {
        return aeroportoDiArrivo;
    }

    public LocalDateTime getDataOraPartenza() {
        return dataOraPartenza;
    }

    public void setDataOraPartenza(LocalDateTime dataOraPartenza) {
        this.dataOraPartenza = dataOraPartenza;
    }

    public LocalDateTime getDataOraArrivo() {
        return dataOraArrivo;
    }

    public void setDataOraArrivo(LocalDateTime dataOraArrivo) {
        this.dataOraArrivo = dataOraArrivo;
    }

    public int getPostiTotali() {
        return postiTotali;
    }

    public int getPostiDisponibili() {
        return postiDisponibili;
    }

    public void setPostiDisponibili(int postiDisponibili) {
        this.postiDisponibili = postiDisponibili;
    }
}
