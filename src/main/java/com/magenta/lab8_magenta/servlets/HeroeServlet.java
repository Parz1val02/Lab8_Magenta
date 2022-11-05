package com.magenta.lab8_magenta.servlets;

import com.magenta.lab8_magenta.model.beans.ClaseHeroes;
import com.magenta.lab8_magenta.model.beans.Enemigo;
import com.magenta.lab8_magenta.model.beans.Genero;
import com.magenta.lab8_magenta.model.beans.Heroe;
import com.magenta.lab8_magenta.model.daos.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

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
            case "editarHeroes":
                break;
            case "borrarHeroes":
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
                //Validar una longitud maxima de 10 caracteres para el nombre
                heroe.setNombre(request.getParameter("nombreHeroe"));
                try{
                    //Validar el rango de edad entre 8 a 999
                    heroe.setEdad(Integer.parseInt(request.getParameter("edad")));

                }catch(NumberFormatException e){
                    response.sendRedirect(request.getContextPath()+"/Wiki?action=MenuHeroes");
                }
                Genero genero = new Genero();
                try{
                    genero.setIdGenero(Integer.parseInt(request.getParameter("idGenero")));
                    heroe.setGenero(genero);
                }catch(NumberFormatException e){
                    response.sendRedirect(request.getContextPath()+"/Wiki?action=MenuHeroes");
                }
                try{
                    //Validar rango de nivel entre 1 al 100
                    heroe.setNivelInicial(Integer.parseInt(request.getParameter("nivelInicial")));

                }catch(NumberFormatException e){
                    response.sendRedirect(request.getContextPath()+"/Wiki?action=MenuHeroes");
                }
                try{
                    //Validar ataque mayor a 0
                    heroe.setAtaque(Integer.parseInt(request.getParameter("ataque")));
                }catch(NumberFormatException e){
                    response.sendRedirect(request.getContextPath()+"/Wiki?action=MenuHeroes");
                }
                ClaseHeroes claseHeroes = new ClaseHeroes();
                try{
                    claseHeroes.setIdClase(Integer.parseInt(request.getParameter("idClaseHeroe")));
                    heroe.setClaseHeroes(claseHeroes);
                }catch(NumberFormatException e){
                    response.sendRedirect(request.getContextPath()+"/Wiki?action=MenuHeroes");
                }
                Heroe pareja = new Heroe();
                try{
                    pareja.setIdHeroe(Integer.parseInt(request.getParameter("idPareja")));
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
            case "buscarHeroe":
                String searchText = request.getParameter("searchText");

                ArrayList<Heroe> listaHeroesPorNombre = hDao.buscarPorNombreHeroe(searchText);
                request.setAttribute("listaHeroes", listaHeroesPorNombre);
                request.setAttribute("searchText",searchText);

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("heroes/listaHeroes.jsp");
                requestDispatcher.forward(request, response);
                break;
        }
    }
}





