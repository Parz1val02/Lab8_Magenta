package com.magenta.lab8_magenta.servlets;

import com.magenta.lab8_magenta.model.beans.Objeto;
import com.magenta.lab8_magenta.model.daos.ObjetoDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ObjetoServlet", value = "/ObjetoServlet")
public class ObjetoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "listaObjetos" : request.getParameter("action");

        RequestDispatcher view;
        ObjetoDao objetoDao = new ObjetoDao();

        switch (action){

            case "listaObjetos":
                request.setAttribute("listaObjetos",objetoDao.obtenerListaObjetos());
                view = request.getRequestDispatcher("Objetos/listaObjetos.jsp");
                view.forward(request,response);
                break;
            case "editarObjeto":
                String idObjeto = request.getParameter("id");

                Objeto objeto = objetoDao.obtenerObjeto(Integer.parseInt(idObjeto));

                request.setAttribute("Objeto",objeto);
                view = request.getRequestDispatcher("Objetos/editarObjeto.jsp");
                view.forward(request,response);
                break;

            case "agregarObjeto":

                view = request.getRequestDispatcher("Objetos/agregarObjeto.jsp");
                view.forward(request,response);
                break;

            case "borrarObjeto":
                String idObjetoo = request.getParameter("id");
                objetoDao.eliminarObjeto(Integer.parseInt(idObjetoo));

                response.sendRedirect(request.getContextPath()+"/ObjetoServlet");
                break;

        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        ObjetoDao objetoDao = new ObjetoDao();
        switch(action){
            case "guardarEnemigo":
                Objeto objeto = new Objeto();

                    objeto.setNombreObjeto(request.getParameter("nombreObjeto"));
                    objeto.setEfecto(request.getParameter("efecto"));
                    objeto.setPeso(Float.parseFloat(request.getParameter("peso")));

                    objetoDao.agregarObjeto(objeto);

                    response.sendRedirect(request.getContextPath()+"ObjetoServlet");
                break;

            case "actualizarObjeto":
                Objeto objeto1 = new Objeto();
                objeto1.setIdObjeto(Integer.parseInt(request.getParameter("idObjeto")));
                objeto1.setNombreObjeto(request.getParameter("nombreObjeto"));
                objeto1.setEfecto(request.getParameter("efecto"));
                objeto1.setPeso(Float.parseFloat(request.getParameter("peso")));

                objetoDao.actualizarObjeto(objeto1);

                response.sendRedirect(request.getContextPath() + "/ObjetoServlet");

                break;

        }

    }
}
