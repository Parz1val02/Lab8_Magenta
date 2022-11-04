package com.magenta.lab8_magenta.model.daos;

import com.magenta.lab8_magenta.model.beans.ClaseHeroes;
import com.magenta.lab8_magenta.model.beans.Genero;
import com.magenta.lab8_magenta.model.beans.Heroe;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HeroesDao extends BaseDao{

    public ArrayList<Heroe> obtenerHeroes (){
        ArrayList<Heroe> listaHeroes = new ArrayList<>();
        try(Connection conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from heroes")) {

            while(rs.next()){
                Heroe heroe = new Heroe();
                Heroe pareja = new Heroe();
                Genero genero = new Genero();
                ClaseHeroes claseHeroes = new ClaseHeroes();

                heroe.setIdHeroe(rs.getInt(1));
                heroe.setNombre(rs.getString(2));
                heroe.setEdad(rs.getInt(3));
                heroe.setPuntosExperiencia(rs.getInt(4));
                heroe.setNivelInicial(rs.getInt(5));
                heroe.setAtaque(rs.getInt(6));
                pareja.setIdHeroe(rs.getInt(7));
                heroe.setPareja(pareja);
                genero.setIdGenero(rs.getInt(8));
                heroe.setGenero(genero);
                claseHeroes.setIdClase(rs.getInt(9));
                heroe.setClaseHeroes(claseHeroes);
                listaHeroes.add(heroe);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaHeroes;
    }



}
