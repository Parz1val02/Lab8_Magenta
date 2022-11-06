package com.magenta.lab8_magenta.servlets;

import com.magenta.lab8_magenta.model.beans.Elemento;
import com.magenta.lab8_magenta.model.beans.Hechizo;
import com.magenta.lab8_magenta.model.daos.ElementoDao;
import com.magenta.lab8_magenta.model.daos.HechizoDao;
import com.magenta.lab8_magenta.model.daos.ObjetoDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "HechizoServlet", value = "/HechizoServlet")
public class HechizoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "listaHechizos" : request.getParameter("action");

        RequestDispatcher view;
        HechizoDao hechizoDao = new HechizoDao();
        ElementoDao elementoDao = new ElementoDao();

        switch (action) {

            case "listaHechizos":
                request.setAttribute("listaHechizos", hechizoDao.listarHechizos());
                view = request.getRequestDispatcher("Hechizos/ListarHechizos.jsp");
                view.forward(request, response);
                break;

            case "agregarHechizos":
                request.setAttribute("listaHechizosBase", hechizoDao.listarHechizosBase());
                request.setAttribute("listaElementos", elementoDao.listarElementos());
                view = request.getRequestDispatcher("Hechizos/AgregarHechizo.jsp");
                view.forward(request, response);
                break;

            case "eliminarHechizo":

                int idHechizo = Integer.parseInt(request.getParameter("id"));
                hechizoDao.eliminarHechizo(idHechizo);

                response.sendRedirect(request.getContextPath()+"/HechizoServlet");
                break;
    }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String action = request.getParameter("action");

        if(action.equals("guardarHechizo")){
            Hechizo hechizo = new Hechizo();
            Elemento elemento = new Elemento();
            ElementoDao elementoDao = new ElementoDao();
            Hechizo hechizoBase = new Hechizo();
            HechizoDao hechizoDao = new HechizoDao();

            hechizo.setNombreHechizo(request.getParameter("nombreHechizo"));
            elemento = elementoDao.obtenerElemento(Integer.parseInt(request.getParameter("idElemento")));

            hechizo.setPotenciaHechizo(Integer.parseInt(request.getParameter("potenciaHechizo")));
            hechizo.setPresicionHechizo(Integer.parseInt(request.getParameter("presicionHechizo")));
            hechizoBase.setIdHechizo(Integer.parseInt(request.getParameter("idBase")));
            hechizo.setHechizoBase(hechizoBase);

            hechizo.setDesbloqueado(Boolean.parseBoolean(request.getParameter("Desbloqueado")));
            hechizo.setNivelAprendizaje(Integer.parseInt(request.getParameter("nivelAprendizaje")));

            hechizoDao.agregarHechizo(hechizo,elemento);
            response.sendRedirect(request.getContextPath()+"/HechizoServlet");

        }



    }
}
