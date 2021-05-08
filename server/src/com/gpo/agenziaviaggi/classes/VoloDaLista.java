package com.gpo.agenziaviaggi.classes;

import java.util.Date;

public class VoloDaLista {

    private final int id;
    private CompagniaAerea compagniaAerea;
    private Aeroporto aeroportoDiPartenza;
    private Aeroporto aeroportoDiArrivo;
    private Date dataOraPartenza;
    private Date dataOraArrivo;
    private final int postiTotali;
    private int postiDisponibili;

    public VoloDaLista(int id, CompagniaAerea compagniaAerea, Aeroporto aeroportoDiPartenza, Aeroporto aeroportoDiArrivo, Date dataOraPartenza, Date dataOraArrivo, int postiTotali) {
        this.id = id;
        this.compagniaAerea = compagniaAerea;
        this.aeroportoDiPartenza = aeroportoDiPartenza;
        this.aeroportoDiArrivo = aeroportoDiArrivo;
        this.dataOraPartenza = dataOraPartenza;
        this.dataOraArrivo = dataOraArrivo;
        this.postiTotali = postiTotali;
        this.postiDisponibili = postiTotali;
    }

    public VoloDaLista(int id, Date dataOraPartenza, Date dataOraArrivo, int postiTotali, int postiDisponibili) {
        this.id = id;
        this.dataOraPartenza = dataOraPartenza;
        this.dataOraArrivo = dataOraArrivo;
        this.postiTotali = postiTotali;
        this.postiDisponibili = postiDisponibili;
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

    public Date getDataOraPartenza() {
        return dataOraPartenza;
    }

    public void setDataOraPartenza(Date dataOraPartenza) {
        this.dataOraPartenza = dataOraPartenza;
    }

    public Date getDataOraArrivo() {
        return dataOraArrivo;
    }

    public void setDataOraArrivo(Date dataOraArrivo) {
        this.dataOraArrivo = dataOraArrivo;
    }

    public int getPostiTotali() {
        return postiTotali;
    }

    public int getPostiDisponibili() {
        return postiDisponibili;
    }

    public void setCompagniaAerea(CompagniaAerea compagniaAerea) {
        this.compagniaAerea = compagniaAerea;
    }

    public void setAeroportoDiPartenza(Aeroporto aeroportoDiPartenza) {
        this.aeroportoDiPartenza = aeroportoDiPartenza;
    }

    public void setAeroportoDiArrivo(Aeroporto aeroportoDiArrivo) {
        this.aeroportoDiArrivo = aeroportoDiArrivo;
    }

    public void setPostiDisponibili(int postiDisponibili) {
        this.postiDisponibili = postiDisponibili;
    }
}
