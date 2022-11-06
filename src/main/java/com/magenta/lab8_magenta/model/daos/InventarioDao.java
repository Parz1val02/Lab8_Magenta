package com.magenta.lab8_magenta.model.daos;

import com.magenta.lab8_magenta.model.beans.ClaseHeroes;
import com.magenta.lab8_magenta.model.beans.Genero;
import com.magenta.lab8_magenta.model.beans.Heroe;
import com.magenta.lab8_magenta.model.beans.Objeto;

import java.sql.*;
import java.util.ArrayList;

public class InventarioDao extends BaseDao{

    public ArrayList<Objeto> obtenerObjetos (int idHeroe){
        ArrayList<Objeto> Inventario = new ArrayList<>();

        String sql = "SELECT * FROM magenta.inventario where idHeroe= ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idHeroe);
            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    Objeto objeto = new Objeto();
                    ObjetoDao objDao = new ObjetoDao();
                    objeto = objDao.obtenerObjeto(rs.getInt(2));
                    Inventario.add(objeto);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Inventario;
    }

    public ArrayList<Integer> obtenerCantidadObjetos (int idHeroe){
        ArrayList<Integer> cantidadObjetos = new ArrayList<>();

        String sql = "SELECT * FROM magenta.inventario where idHeroe= ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idHeroe);
            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    int cantidad = rs.getInt(3);
                    cantidadObjetos.add(cantidad);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cantidadObjetos;
    }

    public int obtenerCantidad(int idHeroe, int idObjeto){
        int cantidad=0;
        String sql = "SELECT cantidadObjeto FROM magenta.inventario where idHeroe= ? and idObjeto= ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idHeroe);
            pstmt.setInt(2, idObjeto);
            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    cantidad = rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cantidad;
    }

    public void actualizarCantidad(int idHeroe, int idObjeto, int cantidad){
        String sql = "UPDATE inventario SET cantidadObjeto = ? where idHeroe = ? and idObjeto = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, cantidad);
            pstmt.setInt(2, idHeroe);
            pstmt.setInt(3, idObjeto);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public int maximoPeso(int ataque){
        return ataque*ataque;
    }




}
