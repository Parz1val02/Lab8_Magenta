package com.magenta.lab8_magenta.servlets;

import com.magenta.lab8_magenta.model.beans.ClaseHeroes;
import com.magenta.lab8_magenta.model.beans.Genero;
import com.magenta.lab8_magenta.model.beans.Heroe;
import com.magenta.lab8_magenta.model.daos.HeroesDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "Heroes", value = "/Heroes")
public class Heroes extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        HeroesDao hDao = new HeroesDao();

        switch (action) {
            case "guardar":
                Heroe heroe = new Heroe();
                heroe.setNombre(request.getParameter("nombre"));
                try{
                    heroe.setEdad(Integer.parseInt(request.getParameter("edad")));

                }catch(NumberFormatException e){
                    response.sendRedirect(request.getContextPath()+"/Wiki?action=MenuHeroes");
                }
                Genero genero = new Genero();
                try{
                    genero.setIdGenero(Integer.parseInt("genero_id"));
                    heroe.setGenero(genero);
                }catch(NumberFormatException e){
                    response.sendRedirect(request.getContextPath()+"/Wiki?action=MenuHeroes");
                }
                try{
                    heroe.setNivelInicial(Integer.parseInt(request.getParameter("nivelInicial")));

                }catch(NumberFormatException e){
                    response.sendRedirect(request.getContextPath()+"/Wiki?action=MenuHeroes");
                }
                try{
                    heroe.setAtaque(Integer.parseInt(request.getParameter("ataque")));
                }catch(NumberFormatException e){
                    response.sendRedirect(request.getContextPath()+"/Wiki?action=MenuHeroes");
                }
                ClaseHeroes claseHeroes = new ClaseHeroes();
                try{
                    claseHeroes.setIdClase(Integer.parseInt(request.getParameter("claseHeroe_id")));
                    heroe.setClaseHeroes(claseHeroes);
                }catch(NumberFormatException e){
                    response.sendRedirect(request.getContextPath()+"/Wiki?action=MenuHeroes");
                }
                Heroe pareja = new Heroe();
                try{
                    pareja.setIdHeroe(Integer.parseInt(request.getParameter("pareja_id")));
                    boolean parejaExiste = false;
                    for()
                        hDao.ParejaExiste(pareja.getIdHeroe());
                }catch(NumberFormatException e){
                    response.sendRedirect(request.getContextPath()+"/Wiki?action=MenuHeroes");
                }



                response.sendRedirect("");
                break;

        }
    }
}