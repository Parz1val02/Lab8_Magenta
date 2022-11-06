package com.magenta.lab8_magenta.servlets;

import com.magenta.lab8_magenta.model.beans.ClaseEnemigo;
import com.magenta.lab8_magenta.model.beans.Enemigo;
import com.magenta.lab8_magenta.model.beans.Genero;
import com.magenta.lab8_magenta.model.beans.Objeto;
import com.magenta.lab8_magenta.model.daos.ClasesEnemigosDao;
import com.magenta.lab8_magenta.model.daos.EnemigoDao;
import com.magenta.lab8_magenta.model.daos.GeneroDao;
import com.magenta.lab8_magenta.model.daos.ObjetoDao;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

    @WebServlet(name = "ClaseServlet", value = "/ClaseServlet")
    public class ClaseServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String action = request.getParameter("action") == null ? "listaClases" : request.getParameter("action");

            RequestDispatcher view;

            ClasesEnemigosDao claseEnemigoDao = new ClasesEnemigosDao();


            switch (action) {
                case "listaClases":
                    request.setAttribute("listaClases", claseEnemigoDao.obtenerListaClases());
                    view = request.getRequestDispatcher("clases/listaClases.jsp");
                    view.forward(request, response);
                    break;
                case "editarPorcentajeDanio":
                    if (request.getParameter("id") != null) {
                        String claseIdString = request.getParameter("id");
                        int claseIdInt = 0;
                        try {
                            claseIdInt = Integer.parseInt(claseIdString);
                        } catch (NumberFormatException ex) {
                            response.sendRedirect(request.getContextPath() + "ClaseServlet");
                        }

                        ClaseEnemigo claseEnemigo = claseEnemigoDao.obtenerClaseEnemigo(claseIdInt);

                        if (claseEnemigo != null) {
                            request.setAttribute("claseEnemigo", claseEnemigo);
                            //request.setAttribute("listaElementos", elemento.obtenerListaElemento());

                            request.setAttribute("listaClases", claseEnemigoDao.obtenerListaClases());
                            view = request.getRequestDispatcher("clases/editarPorcentajeDanio.jsp");
                            view.forward(request, response);
                        } else {
                            response.sendRedirect(request.getContextPath() + "/ClaseServlet");
                        }

                    } else {
                        response.sendRedirect(request.getContextPath() + "/ClaseServlet");
                    }

                    break;
            }

        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

            Enemigo enemigo;
            EnemigoDao enemigoDao = new EnemigoDao();

            switch (action) {
                case "actualizarPorcentajeDanio":
                    enemigo = new Enemigo();

                    enemigo.setIdEnemigo(Integer.parseInt(request.getParameter("idEnemigo"))); //debo enviar el id del enemigo especifico para poder realizar el update.

                    enemigo.setNombreEnemigo(request.getParameter("nombreEnemigo"));
                    enemigo.setAtaque(Integer.parseInt(request.getParameter("ataque")));
                    enemigo.setExperienciaDerrotado(Integer.parseInt(request.getParameter("experienciaDerrotado")));
                    enemigo.setProbDejarObjeto(Float.parseFloat(request.getParameter("probabilidadDejarObjeto")));


                    Genero genero1 = new Genero();
                    genero1.setIdGenero(Integer.parseInt(request.getParameter("idGenero")));
                    enemigo.setGenero(genero1);


                    Objeto objeto2 = new Objeto();
                    objeto2.setIdObjeto(Integer.parseInt(request.getParameter("idObjeto")));
                    enemigo.setObjeto(objeto2);

                    ClaseEnemigo claseEnemigo2 = new ClaseEnemigo();
                    claseEnemigo2.setIdClaseEnemigo(Integer.parseInt(request.getParameter("idClaseEnemigo")));
                    enemigo.setClaseEnemigo(claseEnemigo2);

                    enemigoDao.actualizarEnemigo(enemigo);

                    response.sendRedirect(request.getContextPath() + "/ClaseServlet?action=listaClases");
                    break;
            }
        }
    }




