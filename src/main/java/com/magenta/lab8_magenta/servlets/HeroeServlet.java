package com.magenta.lab8_magenta.servlets;

import com.magenta.lab8_magenta.model.beans.ClaseHeroes;
import com.magenta.lab8_magenta.model.beans.Genero;
import com.magenta.lab8_magenta.model.beans.Heroe;
import com.magenta.lab8_magenta.model.daos.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "HeroeServlet", value = "/HeroeServlet")
public class HeroeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "listaHeroes" : request.getParameter("action");

        RequestDispatcher view;
        com.magenta.lab8_magenta.model.daos.HeroesDao heroesDao = new com.magenta.lab8_magenta.model.daos.HeroesDao();
        GeneroDao generoDao = new GeneroDao();
        com.magenta.lab8_magenta.model.daos.ObjetoDao objetoDao = new com.magenta.lab8_magenta.model.daos.ObjetoDao();
        ClasesHeroesDao claseHeroesDao = new ClasesHeroesDao();
        HeroesDao hDao = new HeroesDao();

        switch(action){
            case "listaHeroes":
                request.setAttribute("listaHeroes", heroesDao.obtenerListaHeroes());
                view = request.getRequestDispatcher("heroes/listaHeroes.jsp");
                view.forward(request, response);
                break;
            case "agregarHeroes":
                request.setAttribute("listaGeneros", generoDao.obtenerListaGeneros());
                request.setAttribute("listaClases", claseHeroesDao.obtenerListaClases());
                request.setAttribute("parejasDisponibles", hDao.parejasDisponibles());
                view = request.getRequestDispatcher("heroes/agregarHeroes.jsp");
                view.forward(request, response);
                break;

        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        HeroesDao hDao = new HeroesDao();

        switch (action) {
            case "guardarHeroe":
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
                    heroe.setPareja(pareja);
                }catch(NumberFormatException e){
                    response.sendRedirect(request.getContextPath()+"/Wiki?action=MenuHeroes");
                }
                ExperienciaDao expDao = new ExperienciaDao();
                heroe.setPuntosExperiencia(expDao.calcularExperiencia(heroe.getNivelInicial()));
                heroe.setBorradoLogico(0);

                hDao.guardarHeroe(heroe);
                response.sendRedirect(request.getContextPath() + "/HeroeServlet?action=listaHeroes");
                break;
            case("actualizarHeroe"):

                break;
            case("buscarHeroe"):
                break;
        }
    }
}





