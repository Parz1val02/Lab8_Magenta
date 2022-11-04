package com.magenta.lab8_magenta.model.daos;

import com.magenta.lab8_magenta.model.beans.ClaseHeroes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
