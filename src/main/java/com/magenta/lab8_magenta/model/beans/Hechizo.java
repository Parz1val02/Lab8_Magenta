package com.magenta.lab8_magenta.model.beans;

public class Hechizo {
    private int idHechizo;
    private String nombreHechizo;
    private int potenciaHechizo;
    private int presicionHechizo;
    private int nivelAprendizaje;
    private boolean desbloqueado;
    private Elemento elemento;
    private Hechizo hechizoBase;
    private boolean borradoLogico;

    public boolean isBorradoLogico() {
        return borradoLogico;
    }

    public void setBorradoLogico(boolean borradoLogico) {
        this.borradoLogico = borradoLogico;
    }


    public int getIdHechizo() {
        return idHechizo;
    }

    public void setIdHechizo(int idHechizo) {
        this.idHechizo = idHechizo;
    }

    public String getNombreHechizo() {
        return nombreHechizo;
    }

    public void setNombreHechizo(String nombreHechizo) {
        this.nombreHechizo = nombreHechizo;
    }

    public int getPotenciaHechizo() {
        return potenciaHechizo;
    }

    public void setPotenciaHechizo(int potenciaHechizo) {
        this.potenciaHechizo = potenciaHechizo;
    }

    public int getPresicionHechizo() {
        return presicionHechizo;
    }

    public void setPresicionHechizo(int presicionHechizo) {
        this.presicionHechizo = presicionHechizo;
    }

    public int getNivelAprendizaje() {
        return nivelAprendizaje;
    }

    public void setNivelAprendizaje(int nivelAprendizaje) {
        this.nivelAprendizaje = nivelAprendizaje;
    }

    public boolean isDesbloqueado() {
        return desbloqueado;
    }

    public void setDesbloqueado(boolean desbloqueado) {
        this.desbloqueado = desbloqueado;
    }

    public Elemento getElemento() {
        return elemento;
    }

    public void setElemento(Elemento elemento) {
        this.elemento = elemento;
    }

    public Hechizo getHechizoBase() {
        return hechizoBase;
    }

    public void setHechizoBase(Hechizo hechizoBase) {
        this.hechizoBase = hechizoBase;
    }

}
