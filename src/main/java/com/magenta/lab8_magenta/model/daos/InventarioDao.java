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
                while(rs.next()){
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
                while(rs.next()){
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

    public void agregarObjetoInventario(int heroeId, int objetoId, int cantidad){
        String sql = "insert into inventario (idHeroe, idObjeto, cantidadObjeto) \n" +
                "values (?,?,?)";

        try (Connection conn = getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setInt(1,heroeId);
            pstm.setInt(2,objetoId);
            pstm.setInt(3,cantidad);

            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void borrarObjetoInventario(int heroeId, int objetoId){
        String sql = "delete from inventario where idHeroe = ? and idObjeto = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setInt(1,heroeId);
            pstm.setInt(2,objetoId);

            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public double verificarMaxPeso(ArrayList<Objeto> InventarioHeroe, ArrayList<Integer> cantidadObjetosInventario, Objeto objeto, int cantidad){
        double peso=0;
        for(int i=0; i<InventarioHeroe.size(); i++){
            peso += InventarioHeroe.get(i).getPeso()*cantidadObjetosInventario.get(i);
        }
        peso+=objeto.getPeso()*cantidad;

        return peso;

    }

}
