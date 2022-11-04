package Daos;

import Beans.Genero;
import Beans.Objeto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ObjetoDao  extends BaseDao {

    public ArrayList<Objeto> obtenerListaObjetos() {

        ArrayList<Objeto> listaObjetos= new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select * from objetos")) {

            while (rs.next()) {
                Objeto objeto = new Objeto();
                objeto.setIdObjeto(rs.getInt(1));
                objeto.setNombreObjeto(rs.getString(2));
                objeto.setEfecto(rs.getString(3));
                objeto.setPeso(rs.getString(4));
                listaObjetos.add(objeto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return listaObjetos;
    }
}
