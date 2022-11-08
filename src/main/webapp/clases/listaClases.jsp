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
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                 class="bi bi-pencil" viewBox="0 0 16 16">
                                <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"></path>
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