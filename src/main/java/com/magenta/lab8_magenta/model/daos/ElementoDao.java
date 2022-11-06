package com.magenta.lab8_magenta.model.daos;

import com.magenta.lab8_magenta.model.beans.Elemento;

import java.sql.*;
import java.util.ArrayList;

public class ElementoDao extends BaseDao{

    public ArrayList<Elemento> listarElementos (){
        ArrayList<Elemento> listaElementos = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("select * from elementos")) {

            while(rs.next()){
                Elemento elemento = new Elemento();
                elemento.setIdElemento(rs.getInt(1));
                elemento.setNombreElemento(rs.getString(2));

                listaElementos.add(elemento);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaElementos;
    }

    public Elemento obtenerElemento (int idElemento){
        String sql = "select * from elementos where idElemento = ?";
        Elemento elemento = new Elemento();

        try(Connection conn = getConnection();
            PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setInt(1,idElemento);

            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                elemento = new Elemento();
                elemento.setIdElemento(rs.getInt(1));
                elemento.setNombreElemento(rs.getString(2));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return elemento;
    }



}
