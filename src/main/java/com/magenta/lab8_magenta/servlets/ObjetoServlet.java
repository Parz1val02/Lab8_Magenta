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
                boolean usado = objetoDao.isUsadoPorHeroe(Integer.parseInt(idObjeto));

                Objeto objeto = objetoDao.obtenerObjeto(Integer.parseInt(idObjeto));

                request.setAttribute("Objeto",objeto);
                request.setAttribute("usado",usado);
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
        RequestDispatcher view;

        aaa:
        switch(action){
            case "guardarObjeto":
                Objeto objeto = new Objeto();

                    boolean repite = false;

                    for (Objeto o : objetoDao.obtenerListaObjetos()){
                        if(request.getParameter("nombreObjeto").equalsIgnoreCase(o.getNombreObjeto())){
                            repite = true;
                            break;
                        }
                    }

                    if(!repite){
                        objeto.setNombreObjeto(request.getParameter("nombreObjeto"));
                    }else{

                        request.setAttribute("error1","El nombre del objeto no se puede repetir");
                        view = request.getRequestDispatcher("Objetos/agregarObjeto.jsp");
                        view.forward(request,response);
                        break;
                    }


                    objeto.setEfecto(request.getParameter("efecto"));


                    try {

                        objeto.setPeso(Float.parseFloat(request.getParameter("peso")));
                        if(objeto.getPeso()<0){
                            throw new Exception();
                        }

                    }catch(Exception e){
                        request.setAttribute("error2","El peso debe ser un numero decimal positivo");
                        view = request.getRequestDispatcher("Objetos/agregarObjeto.jsp");
                        view.forward(request,response);
                        break;
                    }


                    objetoDao.agregarObjeto(objeto);

                    response.sendRedirect(request.getContextPath()+"ObjetoServlet");
                break;

            case "actualizarObjeto":
                Objeto objeto1 = new Objeto();
                objeto1.setIdObjeto(Integer.parseInt(request.getParameter("idObjeto")));
                Objeto objetoDefault = objetoDao.obtenerObjeto(objeto1.getIdObjeto());

                boolean usado = objetoDao.isUsadoPorHeroe(objeto1.getIdObjeto());

                if(!usado){
                    objeto1.setNombreObjeto(request.getParameter("nombreObjeto"));
                }else{
                    objeto1.setNombreObjeto(objetoDefault.getNombreObjeto());
                }


                objeto1.setEfecto(request.getParameter("efecto"));

                if(!usado){
                    objeto1.setPeso(Float.parseFloat(request.getParameter("peso")));
                } else{
                    objeto1.setPeso(objetoDefault.getPeso());
                }



                objetoDao.actualizarObjeto(objeto1);

                response.sendRedirect(request.getContextPath() + "/ObjetoServlet");

                break;

        }

    }
}
