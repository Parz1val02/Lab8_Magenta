package com.magenta.lab8_magenta.model.beans;

public class Objeto {

    private int idObjeto;
    private String nombreObjeto;
    private String efecto;
    private float peso;
    private boolean borradoLogico;

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

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public boolean isBorradoLogico() {
        return borradoLogico;
    }

    public void setBorradoLogico(boolean borradoLogico) {
        this.borradoLogico = borradoLogico;
    }
}
