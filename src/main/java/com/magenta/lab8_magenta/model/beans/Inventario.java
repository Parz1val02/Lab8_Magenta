package com.magenta.lab8_magenta.model.beans;

public class Inventario {

    private Heroe heroe;
    private Objeto objeto;
    private int cantObjeto;


    public Heroe getHeroe() {
        return heroe;
    }

    public void setHeroe(Heroe heroe) {
        this.heroe = heroe;
    }

    public Objeto getObjeto() {
        return objeto;
    }

    public void setObjeto(Objeto objeto) {
        this.objeto = objeto;
    }

    public int getCantObjeto() {
        return cantObjeto;
    }

    public void setCantObjeto(int cantObjeto) {
        this.cantObjeto = cantObjeto;
    }
}
