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
        String action = request.getParameter("action") == null ? "MenuPrincipal" : request.getParameter("action");
        RequestDispatcher view;
        HeroesDao hDao = new HeroesDao();
        switch (action) {
            case "MenuPrincipal":
                view = request.getRequestDispatcher("index.jsp");
                view.forward(request, response);
                break;
            case "MenuHeroes":
                request.setAttribute("listaHeroes", hDao.obtenerListaHeroes());
                view = request.getRequestDispatcher("heroes/listaHeroes.jsp");
                view.forward(request, response);
                break;
            case "MenuEnemigos":
                view = request.getRequestDispatcher("enemigos/listaEnemigos.jsp");
                view.forward(request, response);
                break;
            case "MenuHechizos":
                view = request.getRequestDispatcher("hechizos/");
                view.forward(request, response);
                break;
            case "CatalogoObjetos":
                view = request.getRequestDispatcher("objetos/");
                view.forward(request, response);
                break;
        }


    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
