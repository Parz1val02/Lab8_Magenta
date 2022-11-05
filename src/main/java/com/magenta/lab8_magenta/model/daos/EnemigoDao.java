package com.magenta.lab8_magenta.model.daos;


import com.magenta.lab8_magenta.model.beans.ClaseEnemigo;
import com.magenta.lab8_magenta.model.beans.Enemigo;
import com.magenta.lab8_magenta.model.beans.Genero;
import com.magenta.lab8_magenta.model.beans.Objeto;
import com.magenta.lab8_magenta.model.daos.BaseDao;

import java.sql.*;
import java.util.ArrayList;

public class EnemigoDao extends BaseDao {

    public ArrayList<Enemigo> obtenerListaEnemigos() {
        ArrayList<Enemigo> listaEnemigos = new ArrayList<>();
        try(Connection conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM magenta.enemigos e\n" +
                    "inner join clases_enemigos clase on e.idClaseEnemigo = clase.idClaseEnemigo\n" +
                    "left join genero g on e.idGenero = g.idGenero\n" +
                    "left join objetos o on e.idObjeto = o.idObjeto;")) {

            while(rs.next()){
                Enemigo enemigo = new Enemigo();


                enemigo.setIdEnemigo(rs.getInt(1));
                enemigo.setNombreEnemigo(rs.getString(2));
                enemigo.setAtaque(rs.getInt(3));
                enemigo.setExperienciaDerrotado(rs.getInt(4));
                enemigo.setProbDejarObjeto(rs.getFloat(5));

                Genero genero = new Genero();
                genero.setIdGenero(rs.getInt("g.idGenero"));
                genero.setInicial(rs.getString("inicial"));
                enemigo.setGenero(genero);

                Objeto objeto = new Objeto();
                objeto.setIdObjeto(rs.getInt("o.idObjeto"));
                objeto.setNombreObjeto(rs.getString("nombreObjeto"));
                objeto.setEfecto(rs.getString("efecto"));
                objeto.setPeso(rs.getString("peso"));
                enemigo.setObjeto(objeto);

                ClaseEnemigo claseEnemigo = new ClaseEnemigo();
                claseEnemigo.setIdClaseEnemigo(rs.getInt("clase.idClaseEnemigo"));
                claseEnemigo.setNombreClase(rs.getString("nombreClase"));
                enemigo.setClaseEnemigo(claseEnemigo);

                listaEnemigos.add(enemigo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaEnemigos;
    }




    public Enemigo obtenerEnemigo (int idEnemigo){

        Enemigo enemigo = null;


        try(Connection conn = getConnection();
            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM magenta.enemigos e\n" +
                    "inner join clases_enemigos clase on e.idClaseEnemigo = clase.idClaseEnemigo\n" +
                    "left join genero g on e.idGenero = g.idGenero\n" +
                    "left join objetos o on e.idObjeto = o.idObjeto\n" +
                    "where e.idEnemigo = ?\n" +
                    "order by e.idEnemigo;")){

            pstm.setInt(1,idEnemigo);


            try (ResultSet rs = pstm.executeQuery();){
                if(rs.next()){


                    enemigo = new Enemigo();
                    enemigo.setIdEnemigo(rs.getInt(1));
                    enemigo.setNombreEnemigo(rs.getString(2));
                    enemigo.setAtaque(rs.getInt(3));
                    enemigo.setExperienciaDerrotado(rs.getInt(4));
                    enemigo.setProbDejarObjeto(rs.getFloat(5));

                    Genero genero = new Genero();
                    genero.setIdGenero(rs.getInt("g.idGenero"));
                    genero.setInicial(rs.getString("inicial"));
                    enemigo.setGenero(genero);

                    Objeto objeto = new Objeto();
                    objeto.setIdObjeto(rs.getInt("o.idObjeto"));
                    objeto.setNombreObjeto(rs.getString("nombreObjeto"));
                    objeto.setEfecto(rs.getString("efecto"));
                    objeto.setPeso(rs.getString("peso"));
                    enemigo.setObjeto(objeto);

                    ClaseEnemigo claseEnemigo = new ClaseEnemigo();
                    claseEnemigo.setIdClaseEnemigo(rs.getInt("clase.idClaseEnemigo"));
                    claseEnemigo.setNombreClase(rs.getString("nombreClase"));
                    enemigo.setClaseEnemigo(claseEnemigo);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enemigo;
    }


    public void guardarEnemigo(Enemigo enemigo) {


        String sql = "INSERT INTO enemigos (nombreEnemigo, ataque, experienciaDerrotado, probabilidadDejarObjeto, idGenero,idObjeto, idClaseEnemigo, borradoLogico) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, enemigo.getNombreEnemigo());
            pstmt.setInt(2, enemigo.getAtaque());
            pstmt.setInt(3, enemigo.getExperienciaDerrotado());
            pstmt.setFloat(4, enemigo.getProbDejarObjeto());
            pstmt.setInt(5, enemigo.getGenero().getIdGenero());

            pstmt.setInt(7, enemigo.getClaseEnemigo().getIdClaseEnemigo());


            if(enemigo.getGenero().getIdGenero() == 0){
                pstmt.setNull(6,Types.INTEGER);
            }else{
                pstmt.setInt(6, enemigo.getGenero().getIdGenero());
            }
            pstmt.setInt(8, enemigo.getBorradoLogico());

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }




    public void actualizarEnemigo(Enemigo enemigo) {


        String sql = "UPDATE enemigos "
                + "SET nombreEnemigo = ?, "
                + "ataque = ?, "
                + "experienciaDerrotado = ?, "
                + "probabilidadDejarObjeto = ?, "
                + "idGenero = ?, "
                + "idObjeto = ?, "
                + "idClaseEnemigo = ?"
                + "WHERE idEnemigo = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, enemigo.getNombreEnemigo());
            pstmt.setInt(2, enemigo.getAtaque());
            pstmt.setInt(3, enemigo.getExperienciaDerrotado());
            pstmt.setFloat(4, enemigo.getProbDejarObjeto());
            pstmt.setInt(5, enemigo.getGenero().getIdGenero());
            pstmt.setInt(7, enemigo.getClaseEnemigo().getIdClaseEnemigo());

            if(enemigo.getGenero().getIdGenero() == 0){
                pstmt.setNull(6,Types.INTEGER);
            }else{
                pstmt.setInt(6, enemigo.getGenero().getIdGenero());
            }
            pstmt.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void borrarEnemigo(int idEnemigo) {    /*verificar dependencias para el borrado*/
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM enemigos WHERE idEnemigo = ?")) {

            pstmt.setInt(1, idEnemigo);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



    public ArrayList<Enemigo> buscarPorNombreEnemigo(String nombreEnemigo) {

        ArrayList<Enemigo> listaEnemigosPorNombre = new ArrayList<>();


        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(" SELECT * FROM magenta.enemigos e\n" +
                     "inner join clases_enemigos clase on e.idClaseEnemigo = clase.idClaseEnemigo\n" +
                     "left join genero g on e.idGenero = g.idGenero\n" +
                     "left join objetos o on e.idObjeto = o.idObjeto\n" +
                     "where e.nombreEnemigo = ? \n" +
                     "order by e.nombreEnemigo;")) {

            pstmt.setString(1, nombreEnemigo);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Enemigo enemigo = new Enemigo();


                    enemigo.setIdEnemigo(rs.getInt(1));
                    enemigo.setNombreEnemigo(rs.getString(2));
                    enemigo.setAtaque(rs.getInt(3));
                    enemigo.setExperienciaDerrotado(rs.getInt(4));
                    enemigo.setProbDejarObjeto(rs.getFloat(5));

                    Genero genero = new Genero();
                    genero.setIdGenero(rs.getInt("g.idGenero"));
                    genero.setInicial(rs.getString("inicial"));
                    enemigo.setGenero(genero);

                    Objeto objeto = new Objeto();
                    objeto.setIdObjeto(rs.getInt("o.idObjeto"));
                    objeto.setNombreObjeto(rs.getString("nombreObjeto"));
                    objeto.setEfecto(rs.getString("efecto"));
                    objeto.setPeso(rs.getString("peso"));
                    enemigo.setObjeto(objeto);

                    ClaseEnemigo claseEnemigo = new ClaseEnemigo();
                    claseEnemigo.setIdClaseEnemigo(rs.getInt("clase.idClaseEnemigo"));
                    claseEnemigo.setNombreClase(rs.getString("nombreClase"));
                    enemigo.setClaseEnemigo(claseEnemigo);

                    listaEnemigosPorNombre.add(enemigo);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaEnemigosPorNombre;
    }


}
