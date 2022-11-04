package com.magenta.lab8_magenta.servlets;

import com.magenta.lab8_magenta.model.beans.Genero;
import com.magenta.lab8_magenta.model.beans.Heroe;
import com.magenta.lab8_magenta.model.daos.HeroesDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "Wiki", value = "/Wiki")
public class Wiki extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "Principal" : request.getParameter("action");
        RequestDispatcher view;
        HeroesDao hDao = new HeroesDao();
        switch (action) {
            case "MenuPrincipal":
                view = request.getRequestDispatcher("MenuPrincipal.jsp");
                view.forward(request, response);
                break;
            case "MenuHeroes":
                request.setAttribute("listaHeroes", hDao.obtenerListaHeroes());
                view = request.getRequestDispatcher("Heroes/MenuHeroes.jsp");
                view.forward(request, response);
                break;
            case "MenuEnemigos":
                view = request.getRequestDispatcher("Enemigos/MenuEnemigos.jsp");
                view.forward(request, response);
                break;
            case "MenuHechizos":
                view = request.getRequestDispatcher("Hechizos/MenuHechizos.jsp");
                view.forward(request, response);
                break;
            case "CatalogoObjetos":
                view = request.getRequestDispatcher("Objetos/MenuObjetos.jsp");
                view.forward(request, response);
                break;
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "Principal" : request.getParameter("action");

        HeroesDao hDao = new HeroesDao();

        switch (action) {
            case "guardar":
                Heroe heroe = new Heroe();
                if(request.getParameter("nombre").length()>10){
                    heroe.setNombre(request.getParameter("nombre"));
                }else{

                }
                try {
                    if(Integer.parseInt(request.getParameter("edad"))>8 & Integer.parseInt(request.getParameter("edad"))<999){
                        heroe.setEdad(Integer.parseInt(request.getParameter("edad")));
                    }else{

                    }
                }catch(NumberFormatException e){

                }
                Genero genero = new Genero();
                switch(request.getParameter("genero")){
                    case "Masculino":
                        genero.setIdGenero(1);
                        genero.setInicial("M");
                        heroe.setGenero(genero);
                        break;
                    case "Femenino":
                        genero.setIdGenero(2);
                        genero.setInicial("F");
                        heroe.setGenero(genero);
                        break;
                    case "Otro":
                        genero.setIdGenero(3);
                        genero.setInicial("O");
                        heroe.setGenero(genero);
                        break;
                    default:

                }
                //Validar que la clase sea
                try{
                    heroe.setNivelInicial(Integer.parseInt(request.getParameter("nivelInicial")));

                }catch(NumberFormatException e){

                }
                try{
                    heroe.setAtaque(Integer.parseInt(request.getParameter("ataque")));
                }catch(NumberFormatException e){

                }



                response.sendRedirect("EmployeeServlet");
                break;

        }
    }
}
