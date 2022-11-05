package com.magenta.lab8_magenta.model.beans;

public class Objeto {

    private int idObjeto;
    private String nombreObjeto;
    private String efecto;
    private double peso;
    private int BorradoLogico;

    public int getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(int idObjeto) {
        this.idObjeto = idObjeto;
    }

    public String getNombreObjeto() {
        return nombreObjeto;
    }

    public void setNombreObjeto(String nombreObjeto) {
        this.nombreObjeto = nombreObjeto;
    }

    public String getEfecto() {
        return efecto;
    }

    public void setEfecto(String efecto) {
        this.efecto = efecto;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getBorradoLogico() {
        return BorradoLogico;
    }

    public void setBorradoLogico(int borradoLogico) {
        BorradoLogico = borradoLogico;
    }
}
