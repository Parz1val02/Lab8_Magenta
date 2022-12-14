package com.magenta.lab8_magenta.servlets;

import com.magenta.lab8_magenta.model.beans.ClaseEnemigo;
import com.magenta.lab8_magenta.model.beans.Enemigo;
import com.magenta.lab8_magenta.model.beans.Genero;
import com.magenta.lab8_magenta.model.beans.Objeto;
import com.magenta.lab8_magenta.model.daos.*;
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
        EnemigoDao enemigoDao = new EnemigoDao();
        GeneroDao generoDao = new GeneroDao();
        ObjetoDao objetoDao = new ObjetoDao();
        ClasesEnemigosDao claseEnemigoDao = new ClasesEnemigosDao();


        switch (action) {
            case "listaEnemigos":
                /*para estadisticas a mostrar en MenuEnemigo*/
                String claseMasComun = enemigoDao.claseEnemigoMasComun();
                request.setAttribute("claseMasComun",claseMasComun);

                String objetoMasComun = enemigoDao.objetoMasComun();
                request.setAttribute("objetoMasComun",objetoMasComun);

                float enemigosSinGenero = enemigoDao.enemigosSinGenero();
                request.setAttribute("enemigosSinGenero",enemigosSinGenero);
                /*FINISH para estadisticas a mostrar en MenuEnemigo*/

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
                        response.sendRedirect(request.getContextPath() + "/EnemigoServlet");
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
        EnemigoDao enemigoDao = new EnemigoDao();

        switch (action) {
            case "guardarEnemigo":

                enemigo = new Enemigo();

                if(request.getParameter("nombreEnemigo").matches("[a-zA-Z]+$")) {
                    enemigo.setNombreEnemigo(request.getParameter("nombreEnemigo"));
                }else{
                    ObjetoDao objetoDao = new ObjetoDao();
                    request.setAttribute("listaObjetos", objetoDao.obtenerListaObjetos());
                    GeneroDao generoDao = new GeneroDao();
                    request.setAttribute("listaGeneros", generoDao.obtenerListaGeneros());
                    ClasesEnemigosDao claseEnemigoDao = new ClasesEnemigosDao();
                    request.setAttribute("listaClases", claseEnemigoDao.obtenerListaClases());
                    request.setAttribute("error3", "El campo ingresado solo debe contener letras");
                    RequestDispatcher view = request.getRequestDispatcher("enemigos/agregarEnemigos.jsp");
                    view.forward(request, response);
                    break;
                }
                try {
                    enemigo.setAtaque(Integer.parseInt(request.getParameter("ataque")));
                    if(enemigo.getAtaque()==0){
                        ObjetoDao objetoDao = new ObjetoDao();
                        request.setAttribute("listaObjetos", objetoDao.obtenerListaObjetos());
                        GeneroDao generoDao = new GeneroDao();
                        request.setAttribute("listaGeneros", generoDao.obtenerListaGeneros());
                        ClasesEnemigosDao claseEnemigoDao = new ClasesEnemigosDao();
                        request.setAttribute("listaClases", claseEnemigoDao.obtenerListaClases());
                        request.setAttribute("error4", "El campo ingresado debe ser mayor que cero");
                        RequestDispatcher view = request.getRequestDispatcher("enemigos/agregarEnemigos.jsp");
                        view.forward(request, response);
                        break;
                    }
                }catch (NumberFormatException e){
                    ObjetoDao objetoDao = new ObjetoDao();
                    request.setAttribute("listaObjetos", objetoDao.obtenerListaObjetos());
                    GeneroDao generoDao = new GeneroDao();
                    request.setAttribute("listaGeneros", generoDao.obtenerListaGeneros());
                    ClasesEnemigosDao claseEnemigoDao = new ClasesEnemigosDao();
                    request.setAttribute("listaClases", claseEnemigoDao.obtenerListaClases());
                    request.setAttribute("error1", "El campo ingresado debe ser un numero entero");
                    RequestDispatcher view = request.getRequestDispatcher("enemigos/agregarEnemigos.jsp");
                    view.forward(request, response);
                    break;
                }
                try {
                    enemigo.setExperienciaDerrotado(Integer.parseInt(request.getParameter("experienciaDerrotado")));
                    if(enemigo.getExperienciaDerrotado()==0){
                        ObjetoDao objetoDao = new ObjetoDao();
                        request.setAttribute("listaObjetos", objetoDao.obtenerListaObjetos());
                        GeneroDao generoDao = new GeneroDao();
                        request.setAttribute("listaGeneros", generoDao.obtenerListaGeneros());
                        ClasesEnemigosDao claseEnemigoDao = new ClasesEnemigosDao();
                        request.setAttribute("listaClases", claseEnemigoDao.obtenerListaClases());
                        request.setAttribute("error4", "El campo ingresado debe ser mayor que cero");
                        RequestDispatcher view = request.getRequestDispatcher("enemigos/agregarEnemigos.jsp");
                        view.forward(request, response);
                        break;
                    }
                }catch (NumberFormatException e){
                    ObjetoDao objetoDao = new ObjetoDao();
                    request.setAttribute("listaObjetos", objetoDao.obtenerListaObjetos());
                    GeneroDao generoDao = new GeneroDao();
                    request.setAttribute("listaGeneros", generoDao.obtenerListaGeneros());
                    ClasesEnemigosDao claseEnemigoDao = new ClasesEnemigosDao();
                    request.setAttribute("listaClases", claseEnemigoDao.obtenerListaClases());
                    request.setAttribute("error1", "El campo ingresado debe ser un numero entero");
                    RequestDispatcher view = request.getRequestDispatcher("enemigos/agregarEnemigos.jsp");
                    view.forward(request, response);
                    break;
                }
                try {
                    enemigo.setProbDejarObjeto(Double.parseDouble(request.getParameter("probabilidadDejarObjeto")));
                    if(enemigo.getProbDejarObjeto()==0){
                        ObjetoDao objetoDao = new ObjetoDao();
                        request.setAttribute("listaObjetos", objetoDao.obtenerListaObjetos());
                        GeneroDao generoDao = new GeneroDao();
                        request.setAttribute("listaGeneros", generoDao.obtenerListaGeneros());
                        ClasesEnemigosDao claseEnemigoDao = new ClasesEnemigosDao();
                        request.setAttribute("listaClases", claseEnemigoDao.obtenerListaClases());
                        request.setAttribute("error4", "El campo ingresado debe ser mayor que cero");
                        RequestDispatcher view = request.getRequestDispatcher("enemigos/agregarEnemigos.jsp");
                        view.forward(request, response);
                        break;
                    }
                    if(enemigo.getProbDejarObjeto()>=1){
                        ObjetoDao objetoDao = new ObjetoDao();
                        request.setAttribute("listaObjetos", objetoDao.obtenerListaObjetos());
                        GeneroDao generoDao = new GeneroDao();
                        request.setAttribute("listaGeneros", generoDao.obtenerListaGeneros());
                        ClasesEnemigosDao claseEnemigoDao = new ClasesEnemigosDao();
                        request.setAttribute("listaClases", claseEnemigoDao.obtenerListaClases());
                        request.setAttribute("error5", "El campo ingresado debe ser menor que uno");
                        RequestDispatcher view = request.getRequestDispatcher("enemigos/agregarEnemigos.jsp");
                        view.forward(request, response);
                        break;
                    }
                }catch (NumberFormatException e){
                    ObjetoDao objetoDao = new ObjetoDao();
                    request.setAttribute("listaObjetos", objetoDao.obtenerListaObjetos());
                    GeneroDao generoDao = new GeneroDao();
                    request.setAttribute("listaGeneros", generoDao.obtenerListaGeneros());
                    ClasesEnemigosDao claseEnemigoDao = new ClasesEnemigosDao();
                    request.setAttribute("listaClases", claseEnemigoDao.obtenerListaClases());
                    request.setAttribute("error2", "El campo ingresado debe ser un numero decimal");
                    RequestDispatcher view = request.getRequestDispatcher("enemigos/agregarEnemigos.jsp");
                    view.forward(request, response);
                    break;
                }


                Genero genero = new Genero();
                genero.setIdGenero(Integer.parseInt(request.getParameter("idGenero")));
                enemigo.setGenero(genero);


                Objeto objeto = new Objeto();
                objeto.setIdObjeto(Integer.parseInt(request.getParameter("idObjeto")));
                enemigo.setObjeto(objeto);

                ClaseEnemigo claseEnemigo = new ClaseEnemigo();
                claseEnemigo.setIdClaseEnemigo(Integer.parseInt(request.getParameter("idClaseEnemigo")));
                enemigo.setClaseEnemigo(claseEnemigo);
                enemigo.setBorradoLogico(0);
                enemigoDao.guardarEnemigo(enemigo);

                response.sendRedirect(request.getContextPath() + "/EnemigoServlet?action=listaEnemigos");
                break;
            case "actualizarEnemigo":
                enemigo = new Enemigo();

                enemigo.setIdEnemigo(Integer.parseInt(request.getParameter("idEnemigo"))); //debo enviar el id del enemigo especifico para poder realizar el update.

                if(request.getParameter("nombreEnemigo").matches("[a-zA-Z]+$")) {
                    enemigo.setNombreEnemigo(request.getParameter("nombreEnemigo"));
                }else{
                    Enemigo enemigo2 = enemigoDao.obtenerEnemigo(enemigo.getIdEnemigo());
                    request.setAttribute("enemigo", enemigo2);
                    ObjetoDao objetoDao = new ObjetoDao();
                    request.setAttribute("listaObjetos", objetoDao.obtenerListaObjetos());
                    GeneroDao generoDao = new GeneroDao();
                    request.setAttribute("listaGeneros", generoDao.obtenerListaGeneros());
                    ClasesEnemigosDao claseEnemigoDao = new ClasesEnemigosDao();
                    request.setAttribute("listaClases", claseEnemigoDao.obtenerListaClases());
                    request.setAttribute("error3", "El campo ingresado solo debe contener letras");
                    RequestDispatcher view = request.getRequestDispatcher("enemigos/editarEnemigo.jsp");
                    view.forward(request, response);
                    break;
                }
                try{
                    enemigo.setAtaque(Integer.parseInt(request.getParameter("ataque")));
                    if(enemigo.getAtaque()==0){
                        Enemigo enemigo2 = enemigoDao.obtenerEnemigo(enemigo.getIdEnemigo());
                        request.setAttribute("enemigo", enemigo2);
                        ObjetoDao objetoDao = new ObjetoDao();
                        request.setAttribute("listaObjetos", objetoDao.obtenerListaObjetos());
                        GeneroDao generoDao = new GeneroDao();
                        request.setAttribute("listaGeneros", generoDao.obtenerListaGeneros());
                        ClasesEnemigosDao claseEnemigoDao = new ClasesEnemigosDao();
                        request.setAttribute("listaClases", claseEnemigoDao.obtenerListaClases());
                        request.setAttribute("error4", "El campo ingresado debe ser mayor a 0");
                        RequestDispatcher view = request.getRequestDispatcher("enemigos/editarEnemigo.jsp");
                        view.forward(request, response);
                        break;
                    }
                }catch(NumberFormatException e){
                    Enemigo enemigo2 = enemigoDao.obtenerEnemigo(enemigo.getIdEnemigo());
                    request.setAttribute("enemigo", enemigo2);
                    ObjetoDao objetoDao = new ObjetoDao();
                    request.setAttribute("listaObjetos", objetoDao.obtenerListaObjetos());
                    GeneroDao generoDao = new GeneroDao();
                    request.setAttribute("listaGeneros", generoDao.obtenerListaGeneros());
                    ClasesEnemigosDao claseEnemigoDao = new ClasesEnemigosDao();
                    request.setAttribute("listaClases", claseEnemigoDao.obtenerListaClases());
                    request.setAttribute("error1", "El campo ingresado debe ser un numero entero");
                    RequestDispatcher view = request.getRequestDispatcher("enemigos/editarEnemigo.jsp");
                    view.forward(request, response);
                    break;
                }
                try{
                    enemigo.setExperienciaDerrotado(Integer.parseInt(request.getParameter("experienciaDerrotado")));
                    if(enemigo.getExperienciaDerrotado()==0) {
                        Enemigo enemigo2 = enemigoDao.obtenerEnemigo(enemigo.getIdEnemigo());
                        request.setAttribute("enemigo", enemigo2);
                        ObjetoDao objetoDao = new ObjetoDao();
                        request.setAttribute("listaObjetos", objetoDao.obtenerListaObjetos());
                        GeneroDao generoDao = new GeneroDao();
                        request.setAttribute("listaGeneros", generoDao.obtenerListaGeneros());
                        ClasesEnemigosDao claseEnemigoDao = new ClasesEnemigosDao();
                        request.setAttribute("listaClases", claseEnemigoDao.obtenerListaClases());
                        request.setAttribute("error4", "El campo ingresado debe ser mayor a 0");
                        RequestDispatcher view = request.getRequestDispatcher("enemigos/editarEnemigo.jsp");
                        view.forward(request, response);
                        break;
                        }
                    }catch(NumberFormatException e){
                    Enemigo enemigo2 = enemigoDao.obtenerEnemigo(enemigo.getIdEnemigo());
                    request.setAttribute("enemigo", enemigo2);
                    ObjetoDao objetoDao = new ObjetoDao();
                    request.setAttribute("listaObjetos", objetoDao.obtenerListaObjetos());
                    GeneroDao generoDao = new GeneroDao();
                    request.setAttribute("listaGeneros", generoDao.obtenerListaGeneros());
                    ClasesEnemigosDao claseEnemigoDao = new ClasesEnemigosDao();
                    request.setAttribute("listaClases", claseEnemigoDao.obtenerListaClases());
                    request.setAttribute("error1", "El campo ingresado debe ser un numero entero");
                    RequestDispatcher view = request.getRequestDispatcher("enemigos/editarEnemigo.jsp");
                    view.forward(request, response);
                    break;
                }
                try{
                    enemigo.setProbDejarObjeto(Double.parseDouble(request.getParameter("probabilidadDejarObjeto")));
                    if(enemigo.getProbDejarObjeto()==0){
                        Enemigo enemigo2 = enemigoDao.obtenerEnemigo(enemigo.getIdEnemigo());
                        request.setAttribute("enemigo", enemigo2);
                        ObjetoDao objetoDao = new ObjetoDao();
                        request.setAttribute("listaObjetos", objetoDao.obtenerListaObjetos());
                        GeneroDao generoDao = new GeneroDao();
                        request.setAttribute("listaGeneros", generoDao.obtenerListaGeneros());
                        ClasesEnemigosDao claseEnemigoDao = new ClasesEnemigosDao();
                        request.setAttribute("listaClases", claseEnemigoDao.obtenerListaClases());
                        request.setAttribute("error4", "El campo ingresado debe ser mayor a 0");
                        RequestDispatcher view = request.getRequestDispatcher("enemigos/editarEnemigo.jsp");
                        view.forward(request, response);
                        break;
                    }
                    if(enemigo.getProbDejarObjeto()>=1){
                        Enemigo enemigo2 = enemigoDao.obtenerEnemigo(enemigo.getIdEnemigo());
                        request.setAttribute("enemigo", enemigo2);
                        ObjetoDao objetoDao = new ObjetoDao();
                        request.setAttribute("listaObjetos", objetoDao.obtenerListaObjetos());
                        GeneroDao generoDao = new GeneroDao();
                        request.setAttribute("listaGeneros", generoDao.obtenerListaGeneros());
                        ClasesEnemigosDao claseEnemigoDao = new ClasesEnemigosDao();
                        request.setAttribute("listaClases", claseEnemigoDao.obtenerListaClases());
                        request.setAttribute("error5", "El campo ingresado debe ser menor a 1");
                        RequestDispatcher view = request.getRequestDispatcher("enemigos/editarEnemigo.jsp");
                        view.forward(request, response);
                        break;
                    }
                }catch(NumberFormatException e){
                    Enemigo enemigo2 = enemigoDao.obtenerEnemigo(enemigo.getIdEnemigo());
                    request.setAttribute("enemigo", enemigo2);
                    ObjetoDao objetoDao = new ObjetoDao();
                    request.setAttribute("listaObjetos", objetoDao.obtenerListaObjetos());
                    GeneroDao generoDao = new GeneroDao();
                    request.setAttribute("listaGeneros", generoDao.obtenerListaGeneros());
                    ClasesEnemigosDao claseEnemigoDao = new ClasesEnemigosDao();
                    request.setAttribute("listaClases", claseEnemigoDao.obtenerListaClases());
                    request.setAttribute("error2", "El campo ingresado debe ser un numero decimal");
                    RequestDispatcher view = request.getRequestDispatcher("enemigos/editarEnemigo.jsp");
                    view.forward(request, response);
                    break;
                }

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

                response.sendRedirect(request.getContextPath() + "/EnemigoServlet?action=listaEnemigos");

                break;
            case "buscarEnemigo":
                String searchText = request.getParameter("searchText");

                String claseMasComun = enemigoDao.claseEnemigoMasComun();
                request.setAttribute("claseMasComun",claseMasComun);

                String objetoMasComun = enemigoDao.objetoMasComun();
                request.setAttribute("objetoMasComun",objetoMasComun);

                float enemigosSinGenero = enemigoDao.enemigosSinGenero();
                request.setAttribute("enemigosSinGenero",enemigosSinGenero);


                ArrayList<Enemigo> listaEnemigosPorNombre = enemigoDao.buscarPorNombreEnemigo(searchText);
                request.setAttribute("listaEnemigos", listaEnemigosPorNombre);
                request.setAttribute("searchText",searchText);

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("enemigos/listaEnemigos.jsp");
                requestDispatcher.forward(request, response);
                break;
        }
    }
}