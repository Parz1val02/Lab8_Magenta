package com.magenta.lab8_magenta.model.daos;

import com.magenta.lab8_magenta.model.beans.ClaseHeroes;
import com.magenta.lab8_magenta.model.beans.Enemigo;
import com.magenta.lab8_magenta.model.beans.Genero;
import com.magenta.lab8_magenta.model.beans.Heroe;

import java.sql.*;
import java.util.ArrayList;

public class HeroesDao extends BaseDao{

    public ArrayList<Heroe> obtenerListaHeroes (){
        ArrayList<Heroe> listaHeroes = new ArrayList<>();
        try(Connection conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from heroes")) {

            while(rs.next()){
                Heroe heroe = new Heroe();
                Heroe pareja = new Heroe();
                Genero genero = new Genero();
                ClaseHeroes claseHeroes = new ClaseHeroes();

                heroe.setIdHeroe(rs.getInt(1));
                heroe.setNombre(rs.getString(2));
                heroe.setEdad(rs.getInt(3));
                heroe.setPuntosExperiencia(rs.getFloat(4));
                heroe.setNivelInicial(rs.getInt(5));
                heroe.setAtaque(rs.getInt(6));
                pareja.setIdHeroe(rs.getInt(7));
                heroe.setPareja(pareja);
                genero.setIdGenero(rs.getInt(8));
                heroe.setGenero(genero);
                claseHeroes.setIdClase(rs.getInt(9));
                heroe.setClaseHeroes(claseHeroes);
                heroe.setBorradoLogico(rs.getInt(10));
                listaHeroes.add(heroe);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaHeroes;
    }

    public Heroe obtenerHeroe (int idHeroe){
        Heroe heroe = new Heroe();
        try(Connection conn = getConnection();
            PreparedStatement pstm = conn.prepareStatement("select * from heroes where idHeroe = ?")){

            pstm.setInt(1,idHeroe);

            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                Heroe pareja = new Heroe();
                Genero genero = new Genero();
                ClaseHeroes claseHeroes = new ClaseHeroes();

                heroe.setIdHeroe(rs.getInt(1));
                heroe.setNombre(rs.getString(2));
                heroe.setEdad(rs.getInt(3));
                heroe.setPuntosExperiencia(rs.getFloat(4));
                heroe.setNivelInicial(rs.getInt(5));
                heroe.setAtaque(rs.getInt(6));
                pareja.setIdHeroe(rs.getInt(7));
                heroe.setPareja(pareja);
                genero.setIdGenero(rs.getInt(8));
                heroe.setGenero(genero);
                claseHeroes.setIdClase(rs.getInt(9));
                heroe.setClaseHeroes(claseHeroes);
                heroe.setBorradoLogico(rs.getInt(10));

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return heroe;
    }


    public void guardarHeroe(Heroe heroe) {


        String sql = "INSERT INTO enemigos (nombreHeroe, edad, puntosExperiencia, nivelInicial, ataque, idPareja, idGenero, idClase, borradoLogico)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, heroe.getNombre());
            pstmt.setInt(2, heroe.getEdad());
            pstmt.setDouble(3, heroe.getPuntosExperiencia());
            pstmt.setInt(4, heroe.getNivelInicial());
            pstmt.setInt(5, heroe.getAtaque());
            pstmt.setInt(6, heroe.getPareja().getIdHeroe());
            pstmt.setInt(7, heroe.getGenero().getIdGenero());
            pstmt.setInt(8, heroe.getClaseHeroes().getIdClase());
            pstmt.setInt(9, heroe.getBorradoLogico());

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Heroe> parejasDisponibles() {

        ArrayList<Heroe> listaParejas= new ArrayList<>();
        Heroe pareja = new Heroe();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select idHeroe, nombreHeroe from heroes where idPareja is null")) {

            while (rs.next()){
                pareja.setIdHeroe(rs.getInt(1));
                pareja.setNombre(rs.getString(2));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return listaParejas;
    }



}
