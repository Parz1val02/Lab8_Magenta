package com.magenta.lab8_magenta.servlets;

import com.magenta.lab8_magenta.model.beans.Heroe;
import com.magenta.lab8_magenta.model.beans.Objeto;
import com.magenta.lab8_magenta.model.daos.HeroesDao;
import com.magenta.lab8_magenta.model.daos.InventarioDao;
import com.magenta.lab8_magenta.model.daos.ObjetoDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "InventarioServlet", value = "/InventarioServlet")
public class InventarioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "inventarioHeroe" : request.getParameter("action");
        RequestDispatcher view;
        HeroesDao hDao = new HeroesDao();
        ObjetoDao oDao = new ObjetoDao();
        InventarioDao iDao = new InventarioDao();
        switch (action) {
            case "inventarioHeroe":
                if (request.getParameter("id") != null) {
                    String heroeIdString = request.getParameter("id");
                    int heroeId = 0;
                    try {
                        heroeId = Integer.parseInt(heroeIdString);
                    } catch (NumberFormatException ex) {
                        response.sendRedirect(request.getContextPath() + "/HeroeServlet");
                    }

                    Heroe heroe = hDao.obtenerHeroe(heroeId);
                    ArrayList<Objeto> InventarioHeroe = iDao.obtenerObjetos(heroeId);
                    ArrayList<Integer> cantidadObjetosInventario = iDao.obtenerCantidadObjetos(heroeId);
                    request.setAttribute("heroe", heroe);
                    request.setAttribute("inventarioHeroe", InventarioHeroe);
                    request.setAttribute("cantidadObjetos", cantidadObjetosInventario);
                    view = request.getRequestDispatcher("inventario/listaInventario.jsp");
                    view.forward(request, response);
                    break;
                }
                response.sendRedirect(request.getContextPath() + "/HeroeServlet");
                break;
            case "agregarObjeto":
                break;
            case "editarObjeto":
                if (request.getParameter("id1") != null) {
                    String heroeIdString = request.getParameter("id1");
                    int heroeId = 0;
                    try {
                        heroeId = Integer.parseInt(heroeIdString);
                    } catch (NumberFormatException ex) {
                        response.sendRedirect(request.getContextPath() + "/HeroeServlet");
                    }
                    if (request.getParameter("id2") != null) {
                        String objetoIdString = request.getParameter("id2");
                        int objetoId = 0;
                        try {
                            objetoId = Integer.parseInt(objetoIdString);
                        } catch (NumberFormatException ex) {
                            response.sendRedirect(request.getContextPath() + "/HeroeServlet");
                        }
                        Heroe heroe = hDao.obtenerHeroe(heroeId);
                        Objeto objeto = oDao.obtenerObjeto(objetoId);
                        int cantidad = iDao.obtenerCantidad(heroeId,objetoId);
                        request.setAttribute("heroe", heroe);
                        request.setAttribute("objeto", objeto);
                        request.setAttribute("cantidad", cantidad);
                        view = request.getRequestDispatcher("inventario/editarObjetoInventario.jsp");
                        view.forward(request, response);
                        break;
                    }
                    response.sendRedirect(request.getContextPath() + "/HeroeServlet");
                    break;
                }
                response.sendRedirect(request.getContextPath() + "/HeroeServlet");
                break;
            case "eliminarObjeto":
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        HeroesDao hDao = new HeroesDao();
        ObjetoDao oDao = new ObjetoDao();
        InventarioDao iDao = new InventarioDao();
        switch(action){
            case "guardarObjetoInventario":
                break;
            case "actualizarObjetoInventario":
                Heroe heroe =  hDao.obtenerHeroe(Integer.parseInt(request.getParameter("idHeroe")));
                Objeto objeto = oDao.obtenerObjeto(Integer.parseInt(request.getParameter("idObjeto")));
                int cantidad = Integer.parseInt(request.getParameter("cantidad"));
                iDao.actualizarCantidad(heroe.getIdHeroe(), objeto.getIdObjeto(), cantidad);

                response.sendRedirect(request.getContextPath() + "/InventarioServlet?action=inventarioHeroe&id="+heroe.getIdHeroe());
                break;
        }
    }
}
