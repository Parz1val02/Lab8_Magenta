package com.magenta.lab8_magenta.model.daos;


import com.magenta.lab8_magenta.model.beans.Objeto;
import com.magenta.lab8_magenta.model.daos.BaseDao;

import java.sql.*;
import java.util.ArrayList;

public class ObjetoDao  extends BaseDao {

    public ArrayList<Objeto> obtenerListaObjetos() {

        ArrayList<Objeto> listaObjetos= new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select * from objetos order by idObjeto")) {

            while (rs.next()) {
                if (!rs.getBoolean(5)) {
                    Objeto objeto = new Objeto();
                    objeto.setIdObjeto(rs.getInt(1));
                    objeto.setNombreObjeto(rs.getString(2));
                    objeto.setEfecto(rs.getString(3));
                    objeto.setPeso(rs.getFloat(4));
                    objeto.setUsadoPorHeroe(rs.getBoolean(6));
                    listaObjetos.add(objeto);
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return listaObjetos;
    }

    public Objeto obtenerObjeto (int idObjeto){

        String sql = "select * from objetos where idObjeto = ?";
        Objeto objeto = new Objeto();

        try(Connection conn = getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql)){

            pstm.setInt(1,idObjeto);

            ResultSet rs = pstm.executeQuery();

            if(rs.next()){
                objeto = new Objeto();
                objeto.setIdObjeto(rs.getInt(1));
                objeto.setNombreObjeto(rs.getString(2));
                objeto.setEfecto(rs.getString(3));


                objeto.setPeso(rs.getFloat(4));


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return objeto;

    }

    public void agregarObjeto (Objeto objeto) {

        String sql = "insert into objetos (nombreObjeto,efecto,peso,borradoLogico,usadoPorHeroe) \n" +
                "values (?,?,?,?,?)";

        try (Connection conn = getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, objeto.getNombreObjeto());
            pstm.setString(2, objeto.getEfecto());
            pstm.setDouble(3, objeto.getPeso());
            pstm.setBoolean(4, objeto.isBorradoLogico());
            pstm.setBoolean(5, false);
            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void eliminarObjeto (int idObjeto){
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("UPDATE objetos SET borradoLogico = 1  WHERE idObjeto = ?")) {

            pstmt.setInt(1, idObjeto);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void actualizarObjeto (Objeto objeto){

        String sql = "update objetos set nombreObjeto=?, efecto=?, peso=?, usadoPorHeroe=? where idObjeto=?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){

                pstmt.setString(1, objeto.getNombreObjeto());
                pstmt.setString(2, objeto.getEfecto());
                pstmt.setDouble(3, objeto.getPeso());
                pstmt.setInt(5, objeto.getIdObjeto());
                pstmt.setBoolean(4, objeto.isUsadoPorHeroe());
                pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public boolean isUsadoPorHeroe(int idObjeto) {

        String sql = "select usadoPorHeroe from objetos where idObjeto = ?";
        boolean usado = false;

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1,idObjeto);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                usado = rs.getBoolean(1);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return usado;
    }

}