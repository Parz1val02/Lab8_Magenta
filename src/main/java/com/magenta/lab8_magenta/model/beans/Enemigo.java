package com.magenta.lab8_magenta.model.beans;

public class Enemigo {
    private int idEnemigo;
    private String nombreEnemigo;
    private int ataque;
    private int experienciaDerrotado;
    private double probDejarObjeto;
    private Genero genero;
    private Objeto objeto;
    private ClaseEnemigo claseEnemigo;
    private int BorradoLogico;

    public int getIdEnemigo() {
        return idEnemigo;
    }

    public void setIdEnemigo(int idEnemigo) {
        this.idEnemigo = idEnemigo;
    }

    public String getNombreEnemigo() {
        return nombreEnemigo;
    }

    public void setNombreEnemigo(String nombreEnemigo) {
        this.nombreEnemigo = nombreEnemigo;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getExperienciaDerrotado() {
        return experienciaDerrotado;
    }

    public void setExperienciaDerrotado(int experienciaDerrotado) {
        this.experienciaDerrotado = experienciaDerrotado;
    }

    public double getProbDejarObjeto() {
        return probDejarObjeto;
    }

    public void setProbDejarObjeto(double probDejarObjeto) {
        this.probDejarObjeto = probDejarObjeto;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Objeto getObjeto() {
        return objeto;
    }

    public void setObjeto(Objeto objeto) {
        this.objeto = objeto;
    }

    public ClaseEnemigo getClaseEnemigo() {
        return claseEnemigo;
    }

    public void setClaseEnemigo(ClaseEnemigo claseEnemigo) {
        this.claseEnemigo = claseEnemigo;
    }

    public int getBorradoLogico() {
        return BorradoLogico;
    }

    public void setBorradoLogico(int borradoLogico) {
        BorradoLogico = borradoLogico;
    }
}
