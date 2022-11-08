package com.magenta.lab8_magenta.model.daos;

import com.magenta.lab8_magenta.model.beans.Elemento;
import com.magenta.lab8_magenta.model.beans.Hechizo;

import java.sql.*;
import java.util.ArrayList;

public class HechizoDao extends BaseDao{

    public ArrayList<Hechizo> listarHechizos (){
        ArrayList<Hechizo> listaHechizos = new ArrayList<>();

        String sql = "select h.idHechizo, h.nombreHechizo, e.nombreElemento, h.potenciaHechizo, h.precisionHechizo, b.nombreHechizo, h.nivelAprendizaje,h.borradoLogico\n" +
                "                from hechizos h\n" +
                "                inner join elementos e on e.idElemento = h.idElemento\n" +
                "                left join hechizos b on b.idHechizo = h.idHechizoBase order by h.idhechizo";

        try(Connection conn = getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql)){

            while(rs.next()){
                if(!rs.getBoolean("borradoLogico")){
                    Hechizo hechizo = new Hechizo();
                    Elemento elemento = new Elemento();
                    Hechizo base = new Hechizo();
                    hechizo.setIdHechizo(rs.getInt(1));
                    hechizo.setNombreHechizo(rs.getString(2));
                    elemento.setNombreElemento(rs.getString(3));
                    hechizo.setElemento(elemento);
                    hechizo.setPotenciaHechizo(rs.getInt(4));
                    hechizo.setPresicionHechizo(rs.getInt(5));
                    base.setNombreHechizo(rs.getString(6));
                    hechizo.setHechizoBase(base);
                    hechizo.setNivelAprendizaje(rs.getInt(7));
                    listaHechizos.add(hechizo);
                }

            }




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaHechizos;
    }

    public ArrayList<Hechizo> listarHechizosBase (){
        ArrayList<Hechizo> listaHechizos = new ArrayList<>();

        String sql = "select h.idHechizo, h.nombreHechizo, e.nombreElemento, h.potenciaHechizo, h.precisionHechizo, b.nombreHechizo, h.nivelAprendizaje\n" +
                "from hechizos h\n" +
                "inner join elementos e on e.idElemento = h.idElemento\n" +
                "left join hechizos b on b.idHechizo = h.idHechizo\n" +
                "where h.idHechizoBase is null";

        try(Connection conn = getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql)){

            while(rs.next()){
                Hechizo hechizo = new Hechizo();
                Elemento elemento = new Elemento();
                Hechizo base = new Hechizo();
                hechizo.setIdHechizo(rs.getInt(1));
                hechizo.setNombreHechizo(rs.getString(2));
                elemento.setNombreElemento(rs.getString(3));
                hechizo.setElemento(elemento);
                hechizo.setPotenciaHechizo(rs.getInt(4));
                hechizo.setPresicionHechizo(rs.getInt(5));
                base.setNombreHechizo(rs.getString(6));
                hechizo.setHechizoBase(base);
                hechizo.setNivelAprendizaje(rs.getInt(7));
                listaHechizos.add(hechizo);
            }




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaHechizos;

    }




    public void agregarHechizo (Hechizo hechizo,Elemento elemento){

        String sql = "insert into hechizos (nombreHechizo,idElemento,potenciaHechizo,precisionHechizo,nivelAprendizaje,borradoLogico,desbloqueado,idHechizoBase) \n" +
                "values (?,?,?,?,?,?,?,?)";

        try (Connection conn = getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)){

            pstm.setString(1,hechizo.getNombreHechizo());
            pstm.setInt(2,elemento.getIdElemento());
            pstm.setInt(3,hechizo.getPotenciaHechizo());
            pstm.setInt(4,hechizo.getPresicionHechizo());
            pstm.setInt(5,hechizo.getNivelAprendizaje());
            pstm.setBoolean(6,hechizo.isBorradoLogico());
            pstm.setBoolean(7,hechizo.isDesbloqueado());



            pstm.setInt(8,hechizo.getHechizoBase().getIdHechizo());


            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void eliminarHechizo (int idHechizo){
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("UPDATE hechizos SET borradoLogico = 1  WHERE idHechizo = ? or idHechizoBase = ?")) {

            pstmt.setInt(1, idHechizo);
            pstmt.setInt(2, idHechizo);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public Hechizo obtenerHechizo (int idHechizo){

        String sql = "select * from hechizos where idHechizo = ?";
        Hechizo hechizo = new Hechizo();
        ElementoDao elementoDao = new ElementoDao();

        try(Connection conn = getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql)){

            pstm.setInt(1,idHechizo);

            ResultSet rs = pstm.executeQuery();

            if(rs.next()){
                hechizo = new Hechizo();
                hechizo.setIdHechizo(rs.getInt(1));
                hechizo.setNombreHechizo(rs.getString(2));
                hechizo.setPotenciaHechizo(rs.getInt(3));
                hechizo.setPresicionHechizo(rs.getInt(4));
                hechizo.setDesbloqueado(rs.getBoolean(5));
                hechizo.setNivelAprendizaje(rs.getInt(6));
                Elemento elemento = elementoDao.obtenerElemento(rs.getInt(7));
                hechizo.setElemento(elemento);
                hechizo.getHechizoBase().setIdHechizo(rs.getInt(8));
                hechizo.setBorradoLogico(false);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hechizo;
    }


}
