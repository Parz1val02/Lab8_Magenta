package com.magenta.lab8_magenta.servlets;


import com.magenta.lab8_magenta.model.beans.ClaseEnemigo;
import com.magenta.lab8_magenta.model.beans.Genero;
import com.magenta.lab8_magenta.model.beans.Objeto;
import com.magenta.lab8_magenta.model.daos.ClasesEnemigosDao;
import com.magenta.lab8_magenta.model.daos.GeneroDao;


import com.magenta.lab8_magenta.model.beans.Enemigo;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "EnemigoServlet", value = "/EnemigoServlet")
public class EnemigoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "listaEnemigos" : request.getParameter("action");

        RequestDispatcher view;
        com.magenta.lab8_magenta.model.daos.EnemigoDao enemigoDao = new com.magenta.lab8_magenta.model.daos.EnemigoDao();
        GeneroDao generoDao = new GeneroDao();
        com.magenta.lab8_magenta.model.daos.ObjetoDao objetoDao = new com.magenta.lab8_magenta.model.daos.ObjetoDao();
        ClasesEnemigosDao claseEnemigoDao = new ClasesEnemigosDao();


        switch (action) {
            case "listaEnemigos":
                request.setAttribute("listaEnemigos", enemigoDao.obtenerListaEnemigos());
                view = request.getRequestDispatcher("enemigos/listaEnemigos.jsp");
                view.forward(request, response);
                break;
            case "agregarEnemigos":
                request.setAttribute("listaObjetos", objetoDao.obtenerListaObjetos());
                request.setAttribute("listaGeneros", generoDao.obtenerListaGeneros());
                request.setAttribute("listaClases", claseEnemigoDao.obtenerListaClases());
                view = request.getRequestDispatcher("enemigos/agregarEnemigos.jsp");
                view.forward(request, response);
                break;
            case "editarEnemigos":
                if (request.getParameter("id") != null) {
                    String enemigoIdString = request.getParameter("id");
                    int enemigoId = 0;
                    try {
                        enemigoId = Integer.parseInt(enemigoIdString);
                    } catch (NumberFormatException ex) {
                        response.sendRedirect(request.getContextPath() + "EnemigoServlet");
                    }

                    Enemigo enemigo = enemigoDao.obtenerEnemigo(enemigoId);

                    if (enemigo != null) {
                        request.setAttribute("enemigo", enemigo);
                        request.setAttribute("listaObjetos", objetoDao.obtenerListaObjetos());
                        request.setAttribute("listaGeneros", generoDao.obtenerListaGeneros());
                        request.setAttribute("listaClases", claseEnemigoDao.obtenerListaClases());
                        view = request.getRequestDispatcher("enemigos/editarEnemigo.jsp");
                        view.forward(request, response);
                    } else {
                        response.sendRedirect(request.getContextPath() + "/EnemigoServlet");
                    }

                } else {
                    response.sendRedirect(request.getContextPath() + "/EnemigoServlet");
                }

                break;
            case "borrarEnemigos":
                if (request.getParameter("id") != null) {
                    String enemigoIdString = request.getParameter("id");
                    int enemigoId = 0;
                    try {
                        enemigoId = Integer.parseInt(enemigoIdString);
                    } catch (NumberFormatException ex) {
                        response.sendRedirect(request.getContextPath() + "/EnemigoServlet");
                    }

                    Enemigo enemigo = enemigoDao.obtenerEnemigo(enemigoId);

                    if (enemigo != null) {
                        enemigoDao.borrarEnemigo(enemigoId);
                    }
                }

                response.sendRedirect(request.getContextPath() + "/EnemigoServlet");
                break;
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        Enemigo enemigo;
        com.magenta.lab8_magenta.model.daos.EnemigoDao enemigoDao = new com.magenta.lab8_magenta.model.daos.EnemigoDao();

        switch (action) {
            case "guardarEnemigo":

                enemigo = new Enemigo();

                enemigo.setNombreEnemigo(request.getParameter("nombreEnemigo"));
                enemigo.setAtaque(Integer.parseInt(request.getParameter("ataque")));
                enemigo.setExperienciaDerrotado(Integer.parseInt(request.getParameter("experienciaDerrotado")));
                enemigo.setProbDejarObjeto(Float.parseFloat(request.getParameter("probabilidadDejarObjeto")));


                Genero genero = new Genero();
                genero.setIdGenero(Integer.parseInt(request.getParameter("idGenero")));
                enemigo.setGenero(genero);


                Objeto objeto = new Objeto();
                objeto.setIdObjeto(Integer.parseInt(request.getParameter("idObjeto")));
                enemigo.setObjeto(objeto);

                ClaseEnemigo claseEnemigo = new ClaseEnemigo();
                claseEnemigo.setIdClaseEnemigo(Integer.parseInt(request.getParameter("idClaseEnemigo")));
                enemigo.setClaseEnemigo(claseEnemigo);

                enemigoDao.guardarEnemigo(enemigo);

                response.sendRedirect(request.getContextPath() + "/EnemigoServlet?action=listaEnemigos");
                break;
            case "actualizarEnemigo":
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

                enemigoDao.guardarEnemigo(enemigo);

                response.sendRedirect(request.getContextPath() + "/EnemigoServlet?action=listaEnemigos");

                break;
            case "buscarEnemigo":
                String searchText = request.getParameter("searchText");

                ArrayList<Enemigo> listaEnemigosPorNombre = enemigoDao.buscarPorNombreEnemigo(searchText);
                request.setAttribute("listaEnemigos", listaEnemigosPorNombre);
                request.setAttribute("searchText",searchText);

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("enemigos/listaEnemigos.jsp");
                requestDispatcher.forward(request, response);
                break;
        }
    }
}
