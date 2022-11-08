package com.magenta.lab8_magenta.model.daos;

import com.magenta.lab8_magenta.model.beans.ClaseEnemigo;
import com.magenta.lab8_magenta.model.beans.DebFort;
import com.magenta.lab8_magenta.model.beans.Elemento;
import com.magenta.lab8_magenta.model.beans.Enemigo;

import java.sql.*;
import java.util.ArrayList;

public class DebFortDao extends BaseDao {

    /*public ArrayList<DebFort> listarDebFortPorClase (int idClaseEnemigo) {
        ArrayList<DebFort> listaElementos = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM magenta.deb_fort \n" +
                     "inner join clases_enemigos clase on deb_fort.idClaseEnemigo = clase.idClaseEnemigo\n" +
                     "left join elementos element on element.idElemento = deb_fort.idElemento where clase.idClaseEnemigo=? order by clase.idClaseEnemigo")) {

            while(rs.next()){
                DebFort debfort = new DebFort();
                debfort.setPorcentajeDanio(rs.getDouble(3));

                ClaseEnemigo claseEnemigo = new ClaseEnemigo();
                claseEnemigo.setIdClaseEnemigo(rs.getInt("clase.idClaseEnemigo"));
                claseEnemigo.setNombreClase(rs.getString("clase.nombreClase"));
                debfort.setClaseEnemigo(claseEnemigo);

                Elemento elemento = new Elemento();
                elemento.setIdElemento(rs.getInt("element.idElemento"));
                elemento.setNombreElemento(rs.getString("element.nombreElemento"));
                debfort.setElemento(elemento);

                listaElementos.add(debfort);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaElementos;
    }*/


    /*
    public DebFort obtenerDebFortPorElemento (int idElemento){
        String sql = "SELECT * FROM magenta.deb_fort \n" +
                "inner join clases_enemigos clase on deb_fort.idClaseEnemigo = clase.idClaseEnemigo\n" +
                "left join elementos element on element.idElemento = deb_fort.idElemento where element.idElemento = ? order by element.idElemento";

        DebFort debfort = new DebFort();

        try(Connection conn = getConnection();
            PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setInt(1,idElemento);

            ResultSet rs = stm.executeQuery();
            while(rs.next()){

                debfort.setPorcentajeDanio(rs.getDouble(3));

                ClaseEnemigo claseEnemigo = new ClaseEnemigo();
                claseEnemigo.setIdClaseEnemigo(rs.getInt("clase.idClaseEnemigo"));
                claseEnemigo.setNombreClase(rs.getString("clase.nombreClase"));
                debfort.setClaseEnemigo(claseEnemigo);

                Elemento elemento = new Elemento();
                elemento.setIdElemento(rs.getInt("element.idElemento"));
                elemento.setNombreElemento(rs.getString("element.nombreElemento"));
                debfort.setElemento(elemento);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return debfort;
    }*/

    public ArrayList<DebFort> obtenerDebFortPorClase(int idClaseEnemigo) {
        String sql = "SELECT * FROM magenta.deb_fort \n" +
                "inner join clases_enemigos clase on deb_fort.idClaseEnemigo = clase.idClaseEnemigo\n" +
                "left join elementos element on element.idElemento = deb_fort.idElemento where clase.idClaseEnemigo=? order by element.idElemento";

        ArrayList<DebFort> listaDebFortPorClase = new ArrayList<>();


        try (Connection conn = getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, idClaseEnemigo);

            try (ResultSet rs = pstm.executeQuery();) {

                while (rs.next()) {
                    DebFort debfort = new DebFort();
                    debfort.setPorcentajeDanio(rs.getDouble(3));

                    ClaseEnemigo claseEnemigo = new ClaseEnemigo();
                    claseEnemigo.setIdClaseEnemigo(rs.getInt("clase.idClaseEnemigo"));
                    claseEnemigo.setNombreClase(rs.getString("clase.nombreClase"));
                    debfort.setClaseEnemigo(claseEnemigo);

                    Elemento elemento = new Elemento();
                    elemento.setIdElemento(rs.getInt("element.idElemento"));
                    elemento.setNombreElemento(rs.getString("element.nombreElemento"));
                    debfort.setElemento(elemento);

                    listaDebFortPorClase.add(debfort);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaDebFortPorClase;
    }



    public DebFort obtenerDebFortPorClaseYElemento(int idClaseEnemigo, int idElemento) {
        String sql = "SELECT * FROM magenta.deb_fort \n" +
                "inner join clases_enemigos clase on deb_fort.idClaseEnemigo = clase.idClaseEnemigo\n" +
                "left join elementos element on element.idElemento = deb_fort.idElemento where clase.idClaseEnemigo=? and element.idElemento=? order by element.idElemento";

        DebFort debfort = new DebFort();

        try (Connection conn = getConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setInt(1, idClaseEnemigo);
            stm.setInt(2, idElemento);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {

                debfort.setPorcentajeDanio(rs.getDouble(3));

                ClaseEnemigo claseEnemigo = new ClaseEnemigo();
                claseEnemigo.setIdClaseEnemigo(rs.getInt("clase.idClaseEnemigo"));
                claseEnemigo.setNombreClase(rs.getString("clase.nombreClase"));
                debfort.setClaseEnemigo(claseEnemigo);

                Elemento elemento = new Elemento();
                elemento.setIdElemento(rs.getInt("element.idElemento"));
                elemento.setNombreElemento(rs.getString("element.nombreElemento"));
                debfort.setElemento(elemento);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return debfort;
    }



    public void actualizarPorcentajeDanio(DebFort debFort) {


        String sql = "UPDATE deb_fort SET porcentajeDanio = ?" +
                "WHERE idClaseEnemigo = ? and idElemento = ?;";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, debFort.getPorcentajeDanio());
            pstmt.setInt(2, debFort.getClaseEnemigo().getIdClaseEnemigo());
            pstmt.setInt(3, debFort.getElemento().getIdElemento());



            pstmt.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
