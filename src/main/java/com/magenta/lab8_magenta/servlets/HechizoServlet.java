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
        RequestDispatcher view;

        aaa:
        if(action.equals("guardarHechizo")){
            Hechizo hechizo = new Hechizo();
            Elemento elemento = new Elemento();
            ElementoDao elementoDao = new ElementoDao();
            Hechizo hechizoBase = new Hechizo();
            HechizoDao hechizoDao = new HechizoDao();


            elemento = elementoDao.obtenerElemento(Integer.parseInt(request.getParameter("idElemento")));

            if(request.getParameter("nombreHechizo").length()<=15){
                hechizo.setNombreHechizo(request.getParameter("nombreHechizo"));
            }else{
                request.setAttribute("listaHechizosBase", hechizoDao.listarHechizosBase());
                request.setAttribute("listaElementos", elementoDao.listarElementos());
                request.setAttribute("error1","El nombre no puede tener mas de 15 caracteres");
                view = request.getRequestDispatcher("Hechizos/AgregarHechizo.jsp");
                view.forward(request, response);
                break aaa;
            }




            try {
                hechizo.setPotenciaHechizo(Integer.parseInt(request.getParameter("potenciaHechizo")));
                hechizo.setPresicionHechizo(Integer.parseInt(request.getParameter("presicionHechizo")));
            }catch(NumberFormatException e){
                request.setAttribute("listaHechizosBase", hechizoDao.listarHechizosBase());
                request.setAttribute("listaElementos", elementoDao.listarElementos());
                request.setAttribute("error2","La potencia y la presición deben ser numeros enteros");
                view = request.getRequestDispatcher("Hechizos/AgregarHechizo.jsp");
                view.forward(request, response);
                break aaa;
            }
            hechizoBase.setIdHechizo(Integer.parseInt(request.getParameter("idBase")));
            hechizo.setHechizoBase(hechizoBase);

            hechizo.setDesbloqueado(false);




            if(Integer.parseInt(request.getParameter("nivelAprendizaje"))<=10){

                hechizo.setNivelAprendizaje(Integer.parseInt(request.getParameter("nivelAprendizaje")));


            }else{
                request.setAttribute("listaHechizosBase", hechizoDao.listarHechizosBase());
                request.setAttribute("listaElementos", elementoDao.listarElementos());
                request.setAttribute("error3","El maximo nivel de aprendizaje es 10");
                view = request.getRequestDispatcher("Hechizos/AgregarHechizo.jsp");
                view.forward(request, response);
                break aaa;
            }

            hechizoDao.agregarHechizo(hechizo,elemento);
            response.sendRedirect(request.getContextPath()+"/HechizoServlet");

        }



    }
}
