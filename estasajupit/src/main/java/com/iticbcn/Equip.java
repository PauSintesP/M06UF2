package com.iticbcn;
import java.util.List;

public class Equip {
    private int idEquip;
    private String nomEquip;
    private String ciutat;
    private int idLliga;
    private List<Jugador> jugadors;
    private Classificacio classificacio;

    // Getters y Setters
    public int getIdEquip() {
        return idEquip;
    }

    public void setIdEquip(int idEquip) {
        this.idEquip = idEquip;
    }

    public String getNomEquip() {
        return nomEquip;
    }

    public void setNomEquip(String nomEquip) {
        this.nomEquip = nomEquip;
    }

    public String getCiutat() {
        return ciutat;
    }

    public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
    }

    public int getIdLliga() {
        return idLliga;
    }

    public void setIdLliga(int idLliga) {
        this.idLliga = idLliga;
    }

    public List<Jugador> getJugadors() {
        return jugadors;
    }

    public void setJugadors(List<Jugador> jugadors) {
        this.jugadors = jugadors;
    }

    public Classificacio getClassificacio() {
        return classificacio;
    }

    public void setClassificacio(Classificacio classificacio) {
        this.classificacio = classificacio;
    }
}
