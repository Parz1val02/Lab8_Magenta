package com.magenta.lab8_magenta.model.beans;

public class Objeto {

    private int idObjeto;
    private String nombreObjeto;
    private String efecto;

    private double peso;

    private boolean borradoLogico;

    private boolean usadoPorHeroe;

    public boolean isUsadoPorHeroe() {
        return usadoPorHeroe;
    }

    public void setUsadoPorHeroe(boolean usadoPorHeroe) {
        this.usadoPorHeroe = usadoPorHeroe;
    }

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




    public void setPeso(double peso) {
        this.peso = peso;
    }

    public boolean isBorradoLogico() {
        return borradoLogico;
    }

    public void setBorradoLogico(boolean borradoLogico) {
        this.borradoLogico = borradoLogico;}


    public double getPeso() {
        return peso;
    }



}