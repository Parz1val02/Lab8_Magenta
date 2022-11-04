package com.magenta.lab8_magenta.model.daos;

import com.magenta.lab8_magenta.model.beans.Genero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class GeneroDao extends BaseDao{

    public Genero obtenerGenero (int idGenero){
        Genero genero = new Genero();
        try(Connection conn = getConnection();
            PreparedStatement pstm = conn.prepareStatement("select * from genero where idGenero = ?")){

            pstm.setInt(1,idGenero);

            ResultSet rs = pstm.executeQuery();

            if(rs.next()){
                genero = new Genero();

                genero.setIdGenero(rs.getInt(1));
                genero.setInicial(rs.getString(2));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return genero;
    }
    
    public ArrayList<Genero> obtenerListaGeneros() {

        ArrayList<Genero> listaGeneros = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select * from genero")) {

            while (rs.next()) {
                Genero genero = new Genero();
                genero.setIdGenero(rs.getInt(1));
                genero.setInicial(rs.getString(2));
                listaGeneros.add(genero);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return listaGeneros;
    }


}
