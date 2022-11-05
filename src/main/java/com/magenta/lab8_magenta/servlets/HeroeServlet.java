package com.magenta.lab8_magenta.servlets;

import com.magenta.lab8_magenta.model.beans.*;
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
            case "editarHeroe":
                if (request.getParameter("id") != null) {
                    String heroeIdString = request.getParameter("id");
                    int heroeId = 0;
                    try {
                        heroeId = Integer.parseInt(heroeIdString);
                    } catch (NumberFormatException ex) {
                        response.sendRedirect(request.getContextPath() + "/HeroeServlet");
                    }

                    Heroe heroe = hDao.obtenerHeroe(heroeId);

                    if (heroe != null) {
                        request.setAttribute("heroe", heroe);
                        request.setAttribute("listaGeneros", generoDao.obtenerListaGeneros());
                        request.setAttribute("listaClases", claseHeroesDao.obtenerListaClases());
                        request.setAttribute("parejasDisponibles", hDao.parejasDisponibles1(heroe.getIdHeroe()));
                        view = request.getRequestDispatcher("heroes/editarHeroe.jsp");
                        view.forward(request, response);
                    } else {
                        response.sendRedirect(request.getContextPath() + "/HeroeServlet");
                    }

                } else {
                    response.sendRedirect(request.getContextPath() + "/HeroeServlet");
                }

                break;
            case "borrarHeroes":
                if (request.getParameter("id") != null) {
                    String heroeIdString = request.getParameter("id");
                    int heroeId = 0;
                    int parejaId=0;
                    try {
                        heroeId = Integer.parseInt(heroeIdString);
                    } catch (NumberFormatException ex) {
                        response.sendRedirect(request.getContextPath() + "/HeroeServlet");
                    }

                    Heroe heroe = hDao.obtenerHeroe(heroeId);

                    if (heroe != null) {
                        if(heroe.getPareja().getNombre()!=null) {
                            parejaId = heroe.getPareja().getIdHeroe();
                        }
                        hDao.borrarHeroe(heroeId,parejaId);
                    }
                }

                response.sendRedirect(request.getContextPath() + "/HeroeServlet");
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
                }
                Genero genero = new Genero();
                try{
                    genero.setIdGenero(Integer.parseInt(request.getParameter("idGenero")));
                    heroe.setGenero(genero);
                }catch(NumberFormatException e){
                }
                try{
                    //Validar rango de nivel entre 1 al 100
                    heroe.setNivelInicial(Integer.parseInt(request.getParameter("nivelInicial")));

                }catch(NumberFormatException e){
                }
                try{
                    //Validar ataque mayor a 0
                    heroe.setAtaque(Integer.parseInt(request.getParameter("ataque")));
                }catch(NumberFormatException e){
                }
                ClaseHeroes claseHeroes = new ClaseHeroes();
                try{
                    claseHeroes.setIdClase(Integer.parseInt(request.getParameter("idClaseHeroe")));
                    heroe.setClaseHeroes(claseHeroes);
                }catch(NumberFormatException e){
                }
                Heroe pareja = new Heroe();
                try{
                    pareja.setIdHeroe(Integer.parseInt(request.getParameter("idPareja")));
                    heroe.setPareja(pareja);
                }catch(NumberFormatException e){
                }
                ExperienciaDao expDao = new ExperienciaDao();
                heroe.setPuntosExperiencia(expDao.calcularExperiencia(heroe.getNivelInicial()));
                heroe.setBorradoLogico(0);
                hDao.guardarHeroe(heroe);

                response.sendRedirect(request.getContextPath() + "/HeroeServlet?action=listaHeroes");
                break;
            case("actualizarHeroe"):
                heroe = new Heroe();
                heroe.setIdHeroe(Integer.parseInt(request.getParameter("idHeroe"))); //debo enviar el id del heroe especifico para poder realizar el update.
                heroe.setNombre(request.getParameter("nombreHeroe"));

                //Validar una longitud maxima de 10 caracteres para el nombre
                heroe.setNombre(request.getParameter("nombreHeroe"));
                try{
                    //Validar el rango de edad entre 8 a 999
                    heroe.setEdad(Integer.parseInt(request.getParameter("edad")));

                }catch(NumberFormatException e){
                }
                Genero genero2 = new Genero();
                try{
                    genero2.setIdGenero(Integer.parseInt(request.getParameter("idGenero")));
                    heroe.setGenero(genero2);
                }catch(NumberFormatException e){
                }
                try{
                    //Validar rango de nivel entre 1 al 100
                    heroe.setNivelInicial(Integer.parseInt(request.getParameter("nivelInicial")));

                }catch(NumberFormatException e){
                }
                try{
                    //Validar ataque mayor a 0
                    heroe.setAtaque(Integer.parseInt(request.getParameter("ataque")));
                }catch(NumberFormatException e){
                }
                ClaseHeroes claseHeroes1 = new ClaseHeroes();
                try{
                    claseHeroes1.setIdClase(Integer.parseInt(request.getParameter("idClaseHeroe")));
                    heroe.setClaseHeroes(claseHeroes1);
                }catch(NumberFormatException e){
                }
                Heroe pareja1 = new Heroe();
                try{
                    pareja1.setIdHeroe(Integer.parseInt(request.getParameter("idPareja")));
                    heroe.setPareja(pareja1);
                }catch(NumberFormatException e){
                }
                ExperienciaDao experienciaDao = new ExperienciaDao();
                heroe.setPuntosExperiencia(experienciaDao.calcularExperiencia(heroe.getNivelInicial()));

                hDao.actualizarHeroe(heroe);

                response.sendRedirect(request.getContextPath() + "/HeroeServlet?action=listaHeroes");

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





