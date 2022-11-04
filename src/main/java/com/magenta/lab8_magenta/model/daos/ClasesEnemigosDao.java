package com.magenta.lab8_magenta.model.daos;

import com.magenta.lab8_magenta.model.beans.ClaseEnemigo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClasesEnemigosDao extends BaseDao{

    public ClaseEnemigo obtenerClaseEnemigo (int idClaseEnemigo){
        ClaseEnemigo claseEnemigo = new ClaseEnemigo();

        try(Connection conn = getConnection();
            PreparedStatement pstm = conn.prepareStatement("select * from clases_enemigos where idClaseEnemigo = ?")){

            pstm.setInt(1,idClaseEnemigo);

            ResultSet rs = pstm.executeQuery();

            if(rs.next()){
                claseEnemigo = new ClaseEnemigo();
                claseEnemigo.setIdClaseEnemigo(rs.getInt(1));
                claseEnemigo.setNombreClase(rs.getString(2));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return claseEnemigo;
    }


}
