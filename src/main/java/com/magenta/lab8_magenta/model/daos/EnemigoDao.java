package com.magenta.lab8_magenta.model.daos;


import com.magenta.lab8_magenta.model.beans.ClaseEnemigo;
import com.magenta.lab8_magenta.model.beans.Enemigo;
import com.magenta.lab8_magenta.model.beans.Genero;
import com.magenta.lab8_magenta.model.beans.Objeto;


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
                    "left join objetos o on e.idObjeto = o.idObjeto where e.borradoLogico=0 order by e.idEnemigo;")) {

            while(rs.next()){
                Enemigo enemigo = new Enemigo();

                enemigo.setIdEnemigo(rs.getInt(1));
                enemigo.setNombreEnemigo(rs.getString(2));
                enemigo.setAtaque(rs.getInt(3));
                enemigo.setExperienciaDerrotado(rs.getInt(4));
                enemigo.setProbDejarObjeto(rs.getDouble(5));

                Genero genero = new Genero();
                genero.setIdGenero(rs.getInt("g.idGenero"));
                genero.setInicial(rs.getString("inicial"));
                enemigo.setGenero(genero);

                Objeto objeto = new Objeto();
                objeto.setIdObjeto(rs.getInt("o.idObjeto"));
                objeto.setNombreObjeto(rs.getString("nombreObjeto"));
                objeto.setEfecto(rs.getString("efecto"));
                objeto.setPeso(rs.getFloat("peso"));
                enemigo.setObjeto(objeto);

                ClaseEnemigo claseEnemigo = new ClaseEnemigo();
                claseEnemigo.setIdClaseEnemigo(rs.getInt("clase.idClaseEnemigo"));
                claseEnemigo.setNombreClase(rs.getString("nombreClase"));
                enemigo.setClaseEnemigo(claseEnemigo);
                enemigo.setBorradoLogico(rs.getInt("borradoLogico"));

                listaEnemigos.add(enemigo);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaEnemigos;
    }




    public Enemigo obtenerEnemigo (int idEnemigo){

        Enemigo enemigo = new Enemigo();;


        try(Connection conn = getConnection();
            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM magenta.enemigos e\n" +
                    "inner join clases_enemigos clase on e.idClaseEnemigo = clase.idClaseEnemigo\n" +
                    "left join genero g on e.idGenero = g.idGenero\n" +
                    "left join objetos o on e.idObjeto = o.idObjeto\n" +
                    "where e.idEnemigo = ? and e.borradoLogico=0\n")){

            pstm.setInt(1,idEnemigo);
            try (ResultSet rs = pstm.executeQuery();){
                if(rs.next()){
                    enemigo.setIdEnemigo(rs.getInt(1));
                    enemigo.setNombreEnemigo(rs.getString(2));
                    enemigo.setAtaque(rs.getInt(3));
                    enemigo.setExperienciaDerrotado(rs.getInt(4));
                    enemigo.setProbDejarObjeto(rs.getDouble(5));

                    Genero genero = new Genero();
                    genero.setIdGenero(rs.getInt("g.idGenero"));
                    genero.setInicial(rs.getString("inicial"));
                    enemigo.setGenero(genero);

                    Objeto objeto = new Objeto();
                    objeto.setIdObjeto(rs.getInt("o.idObjeto"));
                    objeto.setNombreObjeto(rs.getString("nombreObjeto"));
                    objeto.setEfecto(rs.getString("efecto"));
                    objeto.setPeso(rs.getFloat("peso"));
                    enemigo.setObjeto(objeto);

                    ClaseEnemigo claseEnemigo = new ClaseEnemigo();
                    claseEnemigo.setIdClaseEnemigo(rs.getInt("clase.idClaseEnemigo"));
                    claseEnemigo.setNombreClase(rs.getString("nombreClase"));
                    enemigo.setClaseEnemigo(claseEnemigo);
                    enemigo.setBorradoLogico(rs.getInt("borradoLogico"));

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
            pstmt.setDouble(4, enemigo.getProbDejarObjeto());
            pstmt.setInt(6, enemigo.getObjeto().getIdObjeto());

            pstmt.setInt(7, enemigo.getClaseEnemigo().getIdClaseEnemigo());


            if(enemigo.getGenero().getIdGenero() == 0){
                pstmt.setNull(5,Types.INTEGER);
            }else{
                pstmt.setInt(5, enemigo.getGenero().getIdGenero());
            }
            pstmt.setInt(8, enemigo.getBorradoLogico());

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }




    public void actualizarEnemigo(Enemigo enemigo) {


        String sql = "UPDATE enemigos SET nombreEnemigo = ?, \n" +
                "ataque = ?, \n" +
                "experienciaDerrotado =?,\n" +
                "probabilidadDejarObjeto = ?,\n" +
                "idGenero = ?, \n" +
                "idObjeto = ?, \n" +
                "idClaseEnemigo = ?\n" +
                "WHERE idEnemigo = ?;";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, enemigo.getNombreEnemigo());
            pstmt.setInt(2, enemigo.getAtaque());
            pstmt.setInt(3, enemigo.getExperienciaDerrotado());
            pstmt.setDouble(4, enemigo.getProbDejarObjeto());
            pstmt.setInt(6, enemigo.getObjeto().getIdObjeto());
            pstmt.setInt(7, enemigo.getClaseEnemigo().getIdClaseEnemigo());
            pstmt.setInt(8, enemigo.getIdEnemigo());
            if(enemigo.getGenero().getIdGenero() == 0){
                pstmt.setNull(5,Types.INTEGER);
            }else{
                pstmt.setInt(5, enemigo.getGenero().getIdGenero());
            }

            pstmt.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void borrarEnemigo(int idEnemigo) {    /*verificar dependencias para el borrado*/
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("UPDATE enemigos SET borradoLogico = 1 WHERE idEnemigo = ?")) {

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
                     "where e.nombreEnemigo = ? and e.borradoLogico=0\n" +
                     "order by e.nombreEnemigo;")) {

            pstmt.setString(1, nombreEnemigo);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Enemigo enemigo = new Enemigo();

                    enemigo.setIdEnemigo(rs.getInt(1));
                    enemigo.setNombreEnemigo(rs.getString(2));
                    enemigo.setAtaque(rs.getInt(3));
                    enemigo.setExperienciaDerrotado(rs.getInt(4));
                    enemigo.setProbDejarObjeto(rs.getDouble(5));

                    Genero genero = new Genero();
                    genero.setIdGenero(rs.getInt("g.idGenero"));
                    genero.setInicial(rs.getString("inicial"));
                    enemigo.setGenero(genero);

                    Objeto objeto = new Objeto();
                    objeto.setIdObjeto(rs.getInt("o.idObjeto"));
                    objeto.setNombreObjeto(rs.getString("nombreObjeto"));
                    objeto.setEfecto(rs.getString("efecto"));
                    objeto.setPeso(rs.getFloat("peso"));
                    enemigo.setObjeto(objeto);

                    ClaseEnemigo claseEnemigo = new ClaseEnemigo();
                    claseEnemigo.setIdClaseEnemigo(rs.getInt("clase.idClaseEnemigo"));
                    claseEnemigo.setNombreClase(rs.getString("nombreClase"));
                    enemigo.setClaseEnemigo(claseEnemigo);
                    enemigo.setBorradoLogico(rs.getInt("borradoLogico"));

                    listaEnemigosPorNombre.add(enemigo);

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaEnemigosPorNombre;
    }


    public String objetoMasComun() {

        // ObjetoMasComun objetoMasComun = new ObjetoMasComun();
        String nombreObjetoMasComun = null;

        try(Connection conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT o.idObjeto, nombreObjeto, count(e.idEnemigo) FROM objetos o \n" +
                    "inner join magenta.enemigos e on e.idObjeto = o.idObjeto where o.borradoLogico=0 and e.borradoLogico=0\n" +
                    "group by o.nombreObjeto\n" +
                    "order by count(e.idEnemigo) DESC;")) {

            int i = 0;
            while(rs.next() && i<1){
                nombreObjetoMasComun=rs.getString(2);
                i++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nombreObjetoMasComun;
    }



    //estadisticas

    public String claseEnemigoMasComun() {

        //ClaseMasComun claseMasComun = new ClaseMasComun();
        String claseMasComun = null;

        try(Connection conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT clase.idClaseEnemigo, nombreClase, count(e.idEnemigo) FROM magenta.enemigos e\n" +
                    "inner join clases_enemigos clase on e.idClaseEnemigo = clase.idClaseEnemigo where e.borradoLogico=0\n" +
                    "group by clase.nombreClase\n" +
                    "order by  count(e.idEnemigo) DESC;")) {

            int i = 0;
            while(rs.next() && i<1){
                claseMasComun = rs.getString(2);
                i++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return claseMasComun;
    }




    public float enemigosSinGenero() {

        float porcentajeSinGenero = 0.0f;
        int nroSinGenero=0;
        int totalEnemigos=0;
        boolean valida = false;
        try(Connection conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT count(e.idEnemigo) FROM magenta.enemigos e \n" +
                    "where e.idGenero is Null and e.borradoLogico=0;")) {

            while(rs.next() ){
                nroSinGenero = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try(Connection conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT count(idEnemigo) FROM magenta.enemigos where borradoLogico=0;")) {

            while(rs.next() ){
                totalEnemigos = rs.getInt(1);
            }
            valida = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(valida) {
            porcentajeSinGenero = ((float)nroSinGenero/totalEnemigos)*100;
        }

        return porcentajeSinGenero;
    }

}
