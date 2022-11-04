package com.magenta.lab8_magenta.model.beans;

public class Heroe {

    private int idHeroe;
    private String nombre;
    private int edad;
    private float puntosExperiencia;
    private int nivelInicial;
    private int ataque;
    private Heroe pareja;
    private Genero genero;
    private ClaseHeroes claseHeroes;

    public int getIdHeroe() {
        return idHeroe;
    }

    public void setIdHeroe(int idHeroe) {
        this.idHeroe = idHeroe;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public float getPuntosExperiencia() {
        return puntosExperiencia;
    }

    public void setPuntosExperiencia(float puntosExperiencia) {
        this.puntosExperiencia = puntosExperiencia;
    }

    public int getNivelInicial() {
        return nivelInicial;
    }

    public void setNivelInicial(int nivelInicial) {
        this.nivelInicial = nivelInicial;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public Heroe getPareja() {
        return pareja;
    }

    public void setPareja(Heroe pareja) {
        this.pareja = pareja;
    }


    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public ClaseHeroes getClaseHeroes() {
        return claseHeroes;
    }

    public void setClaseHeroes(ClaseHeroes claseHeroes) {
        this.claseHeroes = claseHeroes;
    }
}
