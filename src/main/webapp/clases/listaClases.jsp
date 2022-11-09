<%@ page import="com.magenta.lab8_magenta.model.beans.ClaseEnemigo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean type="java.util.ArrayList<com.magenta.lab8_magenta.model.beans.ClaseEnemigo>" scope="request" id="listaClases"/>



<html>
<!--Colocar como value: nombre de la presente página -->
<jsp:include page="/static/head.jsp">
    <jsp:param name="title" value="listaClases"/>
</jsp:include>

<body>
<!--MENU-->

<div class="fondo_imagen">

    <div class = 'container-fluid' style="height: 20px; background-color: #f644dd">
    </div>
    <div class = "container-fluid" style="background-color: #c40aa8  !important;">    <!--rgb(232 17 187 / 94%) -->
        <div class="container" >
            <jsp:include page="/includes/navbar.jsp">
                <jsp:param name="page" value="listaEnemigos"/>
            </jsp:include>
        </div>
    </div>
    <!--FINISH MENU-->


    <!--CONTENT-->
    <div class='container' style="margin-top: 30px;">

        <!--TITLE-->
        <div class="pb-3 pt-4 titlecolor">
            <div class="col-lg-6">
                <h1 class='text-dark' class='text-dark' style="color: white !important;font-weight: bold ">Submenu de Clases</h1>
            </div>
        </div>
        <!--FINISH TITLE-->

        <!--CREAR HEROE-->
        <a href="<%=request.getContextPath()%>/EnemigoServlet?action=listaEnemigos" class="btn btn-success">Regresar Menu Enemigos</a>
        <!--FINISH CREAR HEROE-->


        <!--TABLA-->
        <div class="tabla " style="background-color: #332d2d; margin-top: 15px"> <!-- #f8f9fa -->
            <table class="table table-dark table-transparent table-hover">
                <thead style="color: white">
                <tr>
                    <!--<th>ID Heroe</th>-->
                    <th>ID Clase</th>
                    <th class="text-center">Nombre Clase</th>
                    <th class="text-center">Ver Debilidades y Fortalezas</th>
                </tr>
                </thead>

                <tbody style="color: white">
                <% for (ClaseEnemigo claseEnemigo : listaClases) { %>
                <tr>
                    <td><%=claseEnemigo.getIdClaseEnemigo()%></td>
                    <td class="text-center"><%=claseEnemigo.getNombreClase()%></td>
                    <td class="text-center">
                        <a type="button" class="btn btn-primary"
                           href="<%=request.getContextPath()%>/ClaseServlet?action=listaDebFort&id=<%=claseEnemigo.getIdClaseEnemigo()%>">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-eye" viewBox="0 0 16 16">
                                <path d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8zM1.173 8a13.133 13.133 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13.133 13.133 0 0 1 14.828 8c-.058.087-.122.183-.195.288-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5c-2.12 0-3.879-1.168-5.168-2.457A13.134 13.134 0 0 1 1.172 8z"/>
                                <path d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5zM4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0z"/>
                            </svg>
                        </a>
                    </td>

                </tr>
                <% } %>
                </tbody>

            </table>
        </div>
        <!--FINISH TABLA-->
    </div>
</div>
<!--FINISH CONTENT-->

<!--SCRIPTS -->
<jsp:include page="/static/scripts.jsp"/>
<!--FINISH SCRIPTS -->



</body>
</html>