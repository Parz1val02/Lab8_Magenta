package com.magenta.lab8_magenta.model.daos;

import com.magenta.lab8_magenta.model.beans.*;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;

public class HeroesDao extends BaseDao{

    public ArrayList<Heroe> obtenerListaHeroes (){
        ArrayList<Heroe> listaHeroes = new ArrayList<>();
        try(Connection conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM magenta.heroes h\n" +
                    "inner join clases_heroes clase on h.idClase = clase.idClase\n" +
                    " inner join genero g on h.idGenero = g.idGenero\n" +
                    "left join heroes p on h.idPareja = p.idHeroe where h.borradoLogico=0 order by h.idHeroe;")) {

            while(rs.next()){
                Heroe heroe = new Heroe();
                Heroe pareja = new Heroe();
                Genero genero = new Genero();
                ClaseHeroes claseHeroes = new ClaseHeroes();

                heroe.setIdHeroe(rs.getInt(1));
                heroe.setNombre(rs.getString(2));
                heroe.setEdad(rs.getInt(3));
                heroe.setPuntosExperiencia(rs.getDouble(4));
                heroe.setNivelInicial(rs.getInt(5));
                heroe.setAtaque(rs.getInt(6));

                pareja.setIdHeroe(rs.getInt(7));
                pareja.setNombre(rs.getString("p.nombreHeroe"));
                heroe.setPareja(pareja);

                genero.setIdGenero(rs.getInt(8));
                genero.setInicial(rs.getString("g.inicial"));
                heroe.setGenero(genero);

                claseHeroes.setIdClase(rs.getInt(9));
                claseHeroes.setNombreClase(rs.getString("clase.nombreClase"));
                heroe.setClaseHeroes(claseHeroes);

                heroe.setBorradoLogico(rs.getInt(10));
                ExperienciaDao expDao = new ExperienciaDao();
                heroe.setPuntosExperiencia(expDao.calcularExperiencia(heroe.getNivelInicial()));
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
            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM magenta.heroes h\n" +
                    "inner join clases_heroes clase on h.idClase = clase.idClase\n" +
                    "inner join genero g on h.idGenero = g.idGenero\n" +
                    "left join heroes p on h.idPareja = p.idHeroe where h.idHeroe = ? and h.borradoLogico=0")){

            pstm.setInt(1,idHeroe);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                Heroe pareja = new Heroe();
                Genero genero = new Genero();
                ClaseHeroes claseHeroes = new ClaseHeroes();

                heroe.setIdHeroe(rs.getInt(1));
                heroe.setNombre(rs.getString(2));
                heroe.setEdad(rs.getInt(3));
                heroe.setPuntosExperiencia(rs.getDouble(4));
                heroe.setNivelInicial(rs.getInt(5));
                heroe.setAtaque(rs.getInt(6));

                pareja.setIdHeroe(rs.getInt(7));
                pareja.setNombre(rs.getString("p.nombreHeroe"));
                heroe.setPareja(pareja);

                genero.setIdGenero(rs.getInt(8));
                genero.setInicial(rs.getString("g.inicial"));
                heroe.setGenero(genero);

                claseHeroes.setIdClase(rs.getInt(9));
                claseHeroes.setNombreClase(rs.getString("clase.nombreClase"));
                heroe.setClaseHeroes(claseHeroes);

                heroe.setBorradoLogico(rs.getInt(10));
                ExperienciaDao expDao = new ExperienciaDao();
                heroe.setPuntosExperiencia(expDao.calcularExperiencia(heroe.getNivelInicial()));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return heroe;
    }


    public void guardarHeroe(Heroe heroe) {


        String sql = "INSERT INTO heroes (nombreHeroe, edad, puntosExperiencia, nivelInicial, ataque, idPareja, idGenero, idClase, borradoLogico)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, heroe.getNombre());
            pstmt.setInt(2, heroe.getEdad());
            pstmt.setDouble(3, heroe.getPuntosExperiencia());
            pstmt.setInt(4, heroe.getNivelInicial());
            pstmt.setInt(5, heroe.getAtaque());
            pstmt.setInt(7, heroe.getGenero().getIdGenero());
            pstmt.setInt(8, heroe.getClaseHeroes().getIdClase());

            if(heroe.getPareja().getIdHeroe() == 0){
                pstmt.setNull(6,Types.INTEGER);
            }else{
                pstmt.setInt(6, heroe.getPareja().getIdHeroe());
            }
            pstmt.setInt(9, heroe.getBorradoLogico());

            pstmt.executeUpdate();

            if(heroe.getPareja().getIdHeroe() != 0){
                actualizarPareja(heroe.getPareja().getIdHeroe(), pstmt.getGeneratedKeys());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Heroe> parejasDisponibles() {

        ArrayList<Heroe> listaParejas= new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select idHeroe, nombreHeroe from heroes where idPareja is null and borradoLogico = 0;")) {

            while (rs.next()){
                Heroe pareja = new Heroe();
                pareja.setIdHeroe(rs.getInt(1));
                pareja.setNombre(rs.getString(2));
                listaParejas.add(pareja);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return listaParejas;
    }

    public ArrayList<Heroe> parejasDisponibles1(int idHeroe) {

        ArrayList<Heroe> listaParejas= new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select idHeroe, nombreHeroe from heroes where idPareja is null and borradoLogico = 0;")) {

            while (rs.next()){
                Heroe pareja = new Heroe();
                pareja.setIdHeroe(rs.getInt(1));
                pareja.setNombre(rs.getString(2));
                if(idHeroe!= pareja.getIdHeroe()){
                    listaParejas.add(pareja);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return listaParejas;
    }

    public void actualizarHeroe(Heroe heroe) {


        String sql = "UPDATE heroes SET nombreHeroe = ?, \n" +
                "edad = ?, \n" +
                "puntosExperiencia =?,\n" +
                "nivelInicial = ?,\n" +
                "ataque = ?, \n" +
                "idPareja = ?, \n" +
                "idGenero = ?, \n" +
                "idClase = ?\n" +
                "WHERE idHeroe = ?;";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            updatePareja(heroe.getPareja().getIdHeroe(), heroe.getIdHeroe());

            pstmt.setString(1, heroe.getNombre());
            pstmt.setInt(2, heroe.getEdad());
            pstmt.setDouble(3, heroe.getPuntosExperiencia());
            pstmt.setInt(4, heroe.getNivelInicial());
            pstmt.setInt(5, heroe.getAtaque());
            pstmt.setInt(7, heroe.getGenero().getIdGenero());
            pstmt.setInt(8, heroe.getClaseHeroes().getIdClase());
            pstmt.setInt(9, heroe.getIdHeroe());
            if(heroe.getPareja().getIdHeroe() == 0){
                pstmt.setNull(6,Types.INTEGER);
            }else{
                pstmt.setInt(6, heroe.getPareja().getIdHeroe());
            }

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void borrarHeroe(int idHeroe, int idPareja) {    /*verificar dependencias para el borrado*/
        if (idPareja!=0){
            String sql = "UPDATE heroes SET idPareja = ? where idHeroe = ?";

            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setNull(1,Types.INTEGER);
                pstmt.setInt(2, idPareja);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("UPDATE heroes SET borradoLogico = 1 WHERE idHeroe = ?")) {

            pstmt.setInt(1, idHeroe);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



    public ArrayList<Heroe> buscarPorNombreHeroe(String nombreHeroe) {

        ArrayList<Heroe> listaHeroesPorNombre = new ArrayList<>();


        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM magenta.heroes h\n" +
                     "inner join clases_heroes clase on h.idClase = clase.idClase\n" +
                     "inner join genero g on h.idGenero = g.idGenero\n" +
                     "left join heroes p on h.idPareja = p.idHeroe where h.nombreHeroe = ? and h.borradoLogico=0")) {

            pstmt.setString(1, nombreHeroe);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Heroe heroe = new Heroe();
                    Heroe pareja = new Heroe();
                    Genero genero = new Genero();
                    ClaseHeroes claseHeroes = new ClaseHeroes();

                    heroe.setIdHeroe(rs.getInt(1));
                    heroe.setNombre(rs.getString(2));
                    heroe.setEdad(rs.getInt(3));
                    heroe.setPuntosExperiencia(rs.getDouble(4));
                    heroe.setNivelInicial(rs.getInt(5));
                    heroe.setAtaque(rs.getInt(6));

                    pareja.setIdHeroe(rs.getInt(7));
                    pareja.setNombre(rs.getString("p.nombreHeroe"));
                    heroe.setPareja(pareja);

                    genero.setIdGenero(rs.getInt(8));
                    genero.setInicial(rs.getString("g.inicial"));
                    heroe.setGenero(genero);

                    claseHeroes.setIdClase(rs.getInt(9));
                    claseHeroes.setNombreClase(rs.getString("clase.nombreClase"));
                    heroe.setClaseHeroes(claseHeroes);

                    heroe.setBorradoLogico(rs.getInt(10));
                    listaHeroesPorNombre.add(heroe);

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaHeroesPorNombre;
    }

    public void actualizarPareja(int idHeroe, ResultSet generatedKeys){
        String sql = "UPDATE heroes SET idPareja = ? where idHeroe = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            if(generatedKeys.next()){
                pstmt.setInt(1, generatedKeys.getInt(1));
                pstmt.setInt(2, idHeroe);
                pstmt.executeUpdate();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void updatePareja(int Heroe, int Pareja){
        int idParejaOriginal=0;
        String sql = "SELECT idPareja from heroes where idHeroe = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, Pareja);
            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    idParejaOriginal= rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        if(Heroe!=idParejaOriginal){
            if(idParejaOriginal!=0){
                sql = "UPDATE heroes SET idPareja = ? where idHeroe = ?";

                try (Connection conn = getConnection();
                     PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setNull(1,Types.INTEGER);
                    pstmt.setInt(2, idParejaOriginal);
                    pstmt.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
            if(Heroe!=0){
                sql = "UPDATE heroes SET idPareja = ? where idHeroe = ?";

                try (Connection conn = getConnection();
                     PreparedStatement pstmt = conn.prepareStatement(sql)) {

                    pstmt.setInt(1, Pareja);
                    pstmt.setInt(2, Heroe);
                    pstmt.executeUpdate();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }

    }
}


