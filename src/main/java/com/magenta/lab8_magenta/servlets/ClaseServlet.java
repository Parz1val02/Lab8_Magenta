package com.magenta.lab8_magenta.servlets;

import com.magenta.lab8_magenta.model.beans.*;
import com.magenta.lab8_magenta.model.daos.*;

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
            DebFortDao debFortDao = new DebFortDao();
            ArrayList<DebFort> listaDebFortPorClase;
            ArrayList<Elemento> listaElementos;
            ClaseEnemigo claseEnemigo;
            ElementoDao elementoDao = new ElementoDao();

            switch (action) {
                case "listaClases":
                    request.setAttribute("listaClases", claseEnemigoDao.obtenerListaClases());
                    view = request.getRequestDispatcher("clases/listaClases.jsp");
                    view.forward(request, response);
                    break;
                case "listaDebFort":
                    if (request.getParameter("id") != null) {
                        String claseIdString = request.getParameter("id");
                        int claseIdInt = 0;
                        try {
                            claseIdInt = Integer.parseInt(claseIdString);
                        } catch (NumberFormatException ex) {
                            response.sendRedirect(request.getContextPath() + "ClaseServlet");
                        }

                        listaDebFortPorClase = debFortDao.obtenerDebFortPorClase(claseIdInt);
                        claseEnemigo = claseEnemigoDao.obtenerClaseEnemigo(claseIdInt);
                        listaElementos = elementoDao.listarElementos();
                        if (listaDebFortPorClase != null) {

                            request.setAttribute("listaDebFortPorClase", listaDebFortPorClase);
                            request.setAttribute("claseEnemigo", claseEnemigo);
                            request.setAttribute("listarElementos", listaElementos);
                            //request.setAttribute("listaElementos", elemento.obtenerListaElemento());
                            view = request.getRequestDispatcher("clases/listaDebFort.jsp");
                            view.forward(request, response);
                        } else {
                            response.sendRedirect(request.getContextPath() + "/ClaseServlet");
                        }

                    } else {
                        response.sendRedirect(request.getContextPath() + "/ClaseServlet");
                    }

                    break;
                case "editarPorcentajeDanio":
                    if (request.getParameter("idElemento") != null && request.getParameter("idClase") != null) {
                        String elementoIdString = request.getParameter("idElemento");
                        String claseIdString = request.getParameter("idClase");

                        int claseIdInt = 0;
                        int elementoIdInt = 0;

                        try {
                            claseIdInt = Integer.parseInt(claseIdString);
                            elementoIdInt = Integer.parseInt(elementoIdString);
                        } catch (NumberFormatException ex) {
                            response.sendRedirect(request.getContextPath() + "ClaseServlet");
                        }

                        DebFort DebFortPorClaseYElemento = debFortDao.obtenerDebFortPorClaseYElemento(claseIdInt,elementoIdInt);

                        if (DebFortPorClaseYElemento != null) {
                            request.setAttribute("DebFortPorClaseYElemento", DebFortPorClaseYElemento);
                            //request.setAttribute("listaElementos", elemento.obtenerListaElemento());
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

            DebFort debFort;
            DebFortDao debFortDao = new DebFortDao();
            Elemento elemento;
            ClaseEnemigo claseEnemigo;

            switch (action) {
                case "actualizarPorcentajeDanio":
                    elemento = new Elemento();
                    debFort = new DebFort();
                    claseEnemigo = new ClaseEnemigo();

                    elemento.setIdElemento(Integer.parseInt(request.getParameter("idElemento")));//debo enviar el id del enemigo especifico para poder realizar el update.
                    debFort.setElemento(elemento);

                    claseEnemigo.setIdClaseEnemigo(Integer.parseInt(request.getParameter("idClase")));
                    debFort.setClaseEnemigo(claseEnemigo);

                    try{
                        debFort.setPorcentajeDanio(Double.parseDouble(request.getParameter("porcentajeDanio")));
                    }catch(NumberFormatException e){
                        DebFort DebFortPorClaseYElemento = debFortDao.obtenerDebFortPorClaseYElemento(debFort.getClaseEnemigo().getIdClaseEnemigo(),debFort.getElemento().getIdElemento());
                        request.setAttribute("DebFortPorClaseYElemento", DebFortPorClaseYElemento);
                        RequestDispatcher view = request.getRequestDispatcher("clases/editarPorcentajeDanio.jsp");
                        request.setAttribute("error2", "El campo ingresado debe ser un numero decimal");
                        view.forward(request, response);
                        break;
                    }


                    debFortDao.actualizarPorcentajeDanio(debFort);

                    response.sendRedirect(request.getContextPath() + "/ClaseServlet?action=listaDebFort&id=" + debFort.getClaseEnemigo().getIdClaseEnemigo() );

                    break;
            }
        }
    }




