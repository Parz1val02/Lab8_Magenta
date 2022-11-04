package com.magenta.lab8_magenta.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExperienciaDao extends BaseDao{

    public void ExperienciaPorNivel (int nivel, int idHeroe){
        double exp = calcularExperiencia(nivel);

        try(Connection conn = getConnection();
            PreparedStatement pstm = conn.prepareStatement("update heroes set puntosExperiencia = exp where idHeroe=?")) {


            pstm.setInt(1,idHeroe);
            pstm.executeUpdate();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public double calcularExperiencia (int nivel){
        double exp = 0;
        boolean caso1 = nivel>0 && nivel<16;
        boolean caso2 = nivel>15 && nivel<36;
        boolean caso3 = nivel>35 && nivel<101;
        if(caso1){
            exp = (nivel*nivel*nivel)*(24+((nivel+1.0)/3.0)/50.0);
        }else if(caso2){
            exp = (nivel*nivel*nivel)*((14+nivel)/50.0);
        }else if(caso3){
            exp = (nivel*nivel*nivel)*((32+(nivel/2.0))/50.0);
        }
        return exp;
    }


}
