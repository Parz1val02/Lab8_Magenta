package com.magenta.lab8_magenta.model.daos;

import com.magenta.lab8_magenta.model.beans.ClaseEnemigo;
import com.magenta.lab8_magenta.model.beans.ClaseHeroes;
import com.magenta.lab8_magenta.model.beans.Heroe;

import java.sql.*;
import java.util.ArrayList;

public class ClasesHeroesDao extends BaseDao{

    public ClaseHeroes obtenerClaseHeroe (int idClaseHeroe){
        ClaseHeroes claseHeroes = new ClaseHeroes();
        try(Connection conn = getConnection();
            PreparedStatement pstm = conn.prepareStatement("select * from clases_heroes where idClase = ?")) {

            pstm.setInt(1,idClaseHeroe);

            ResultSet rs = pstm.executeQuery();

            if(rs.next()){
                claseHeroes = new ClaseHeroes();
                claseHeroes.setIdClase(rs.getInt(1));
                claseHeroes.setNombreClase(rs.getString(2));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return claseHeroes;
    }

    public ArrayList<ClaseHeroes> obtenerListaClases() {

        ArrayList<ClaseHeroes> listaClases= new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select * from clases_heroes")) {

            while (rs.next()) {
                ClaseHeroes claseHeroe = new ClaseHeroes();
                claseHeroe.setIdClase(rs.getInt(1));
                claseHeroe.setNombreClase(rs.getString(2));

                listaClases.add(claseHeroe);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return listaClases;
    }



}
