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
                    ArrayList<Objeto> CatalogoObjetos = oDao.obtenerListaObjetos();
                    ArrayList<Objeto> ObjetosNoPresentes = new ArrayList<>();
                    boolean contiene=false;
                    for(Objeto o : CatalogoObjetos){
                        for(Objeto c : InventarioHeroe){
                            contiene = o.getIdObjeto() == c.getIdObjeto();
                        }
                        if(!contiene){
                            ObjetosNoPresentes.add(o);
                        }
                    }
                    request.setAttribute("heroe", heroe);
                    request.setAttribute("catalogoObjetosNo", ObjetosNoPresentes);
                    view = request.getRequestDispatcher("inventario/agregarObjetoInventario.jsp");
                    view.forward(request, response);
                    break;
                }
                response.sendRedirect(request.getContextPath() + "/HeroeServlet");
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
                        iDao.borrarObjetoInventario(heroeId, objetoId);
                        response.sendRedirect(request.getContextPath() + "/InventarioServlet?action=inventarioHeroe&id="+ heroeId);
                        break;
                    }
                    response.sendRedirect(request.getContextPath() + "/HeroeServlet");
                    break;
                }
                response.sendRedirect(request.getContextPath() + "/HeroeServlet");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        HeroesDao hDao = new HeroesDao();
        ObjetoDao oDao = new ObjetoDao();
        InventarioDao iDao = new InventarioDao();
        Objeto objeto;
        Heroe heroe;
        switch(action){
            case "guardarObjetoInventario":
                heroe =  hDao.obtenerHeroe(Integer.parseInt(request.getParameter("idHeroe")));
                objeto = oDao.obtenerObjeto(Integer.parseInt(request.getParameter("idObjeto")));
                int cantidad1 = 0;
                try{
                    cantidad1 = Integer.parseInt(request.getParameter("cantidad"));
                }catch(NumberFormatException e){
                    ArrayList<Objeto> InventarioHeroe = iDao.obtenerObjetos(heroe.getIdHeroe());
                    ArrayList<Objeto> CatalogoObjetos = oDao.obtenerListaObjetos();
                    ArrayList<Objeto> ObjetosNoPresentes = new ArrayList<>();
                    boolean contiene=false;
                    for(Objeto o : CatalogoObjetos){
                        for(Objeto c : InventarioHeroe){
                            contiene = o.getIdObjeto() == c.getIdObjeto();
                        }
                        if(!contiene){
                            ObjetosNoPresentes.add(o);
                        }
                    }
                    request.setAttribute("heroe", heroe);
                    request.setAttribute("catalogoObjetosNo", ObjetosNoPresentes);
                    request.setAttribute("error1", "El campo ingresado debe ser un numero");
                    RequestDispatcher view = request.getRequestDispatcher("inventario/agregarObjetoInventario.jsp");
                    view.forward(request, response);
                    break;
                    }
                ArrayList<Objeto> InventarioHeroe = iDao.obtenerObjetos(heroe.getIdHeroe());
                ArrayList<Integer> cantidadObjetosInventario = iDao.obtenerCantidadObjetos(heroe.getIdHeroe());
                double pesoNuevo = iDao.verificarMaxPeso(InventarioHeroe, cantidadObjetosInventario, objeto, cantidad1);
                int pesoMax = heroe.getAtaque()*heroe.getAtaque();
                if(pesoNuevo>pesoMax){
                    ArrayList<Objeto> CatalogoObjetos = oDao.obtenerListaObjetos();
                    ArrayList<Objeto> ObjetosNoPresentes = new ArrayList<>();
                    boolean contiene=false;
                    for(Objeto o : CatalogoObjetos){
                        for(Objeto c : InventarioHeroe){
                            contiene = o.getIdObjeto() == c.getIdObjeto();
                        }
                        if(!contiene){
                            ObjetosNoPresentes.add(o);
                        }
                    }
                    request.setAttribute("heroe", heroe);
                    request.setAttribute("catalogoObjetosNo", ObjetosNoPresentes);
                    request.setAttribute("error2", "El heroe no puede cargar tanto peso");
                    RequestDispatcher view = request.getRequestDispatcher("inventario/agregarObjetoInventario.jsp");
                    view.forward(request, response);
                    break;
                }
                iDao.agregarObjetoInventario(heroe.getIdHeroe(), objeto.getIdObjeto(), cantidad1);
                response.sendRedirect(request.getContextPath() + "/InventarioServlet?action=inventarioHeroe&id="+heroe.getIdHeroe());
                break;
            case "actualizarObjetoInventario":
                heroe =  hDao.obtenerHeroe(Integer.parseInt(request.getParameter("idHeroe")));
                objeto = oDao.obtenerObjeto(Integer.parseInt(request.getParameter("idObjeto")));
                int cantidad = 0;
                try{
                    cantidad = Integer.parseInt(request.getParameter("cantidad"));
                }catch (NumberFormatException e){
                    cantidad = iDao.obtenerCantidad(heroe.getIdHeroe(), objeto.getIdObjeto());
                    request.setAttribute("heroe", heroe);
                    request.setAttribute("objeto", objeto);
                    request.setAttribute("cantidad", cantidad);
                    request.setAttribute("error1", "El campo ingresado debe ser un numero");
                    RequestDispatcher view = request.getRequestDispatcher("inventario/editarObjetoInventario.jsp");
                    view.forward(request, response);
                    break;
                }
                InventarioHeroe = iDao.obtenerObjetos(heroe.getIdHeroe());
                cantidadObjetosInventario = iDao.obtenerCantidadObjetos(heroe.getIdHeroe());
                pesoNuevo = iDao.verificarMaxPeso(InventarioHeroe, cantidadObjetosInventario, objeto, cantidad);
                pesoMax = heroe.getAtaque()*heroe.getAtaque();
                if(pesoNuevo>pesoMax){
                    ArrayList<Objeto> CatalogoObjetos = oDao.obtenerListaObjetos();
                    ArrayList<Objeto> ObjetosNoPresentes = new ArrayList<>();
                    boolean contiene=false;
                    for(Objeto o : CatalogoObjetos){
                        for(Objeto c : InventarioHeroe){
                            contiene = o.getIdObjeto() == c.getIdObjeto();
                        }
                        if(!contiene){
                            ObjetosNoPresentes.add(o);
                        }
                    }
                    cantidad = iDao.obtenerCantidad(heroe.getIdHeroe(), objeto.getIdObjeto());
                    request.setAttribute("heroe", heroe);
                    request.setAttribute("objeto", objeto);
                    request.setAttribute("cantidad", cantidad);
                    request.setAttribute("error2", "El heroe no puede cargar tanto peso");
                    RequestDispatcher view = request.getRequestDispatcher("inventario/editarObjetoInventario.jsp");
                    view.forward(request, response);
                    break;
                }
                iDao.actualizarCantidad(heroe.getIdHeroe(), objeto.getIdObjeto(), cantidad);
                response.sendRedirect(request.getContextPath() + "/InventarioServlet?action=inventarioHeroe&id="+heroe.getIdHeroe());
                break;
        }
    }
}
