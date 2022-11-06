<%@ page import="com.magenta.lab8_magenta.model.beans.Hechizo" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 04/11/2022
  Time: 08:48 p. m.
  To change this template use File | Settings | File Templates.
--%>

<jsp:useBean type="java.util.ArrayList<com.magenta.lab8_magenta.model.beans.Hechizo>" scope="request" id="listaHechizos"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<jsp:include page="/static/head.jsp">
  <jsp:param name="title" value="listaEnemigos"/>
</jsp:include>

<body>

<div class="fondo_imagen">

  <div class = 'container-fluid' style="height: 20px; background-color: #f644dd">
  </div>
  <div class = "container-fluid" style="background-color: #c40aa8  !important;">    <!--rgb(232 17 187 / 94%) -->
    <div class="container" >
      <jsp:include page="/includes/navbar.jsp">
        <jsp:param name="page" value="listaHechizos"/>
      </jsp:include>
    </div>
  </div>
  <!--FINISH MENU-->


  <!--CONTENT-->
  <div class='container' style="margin-top: 30px;">

    <!--TITLE-->
    <div class="pb-5 pt-4 titlecolor">
      <div class="col-lg-6">
        <h1 class='text-dark' class='text-dark' style="color: #6f2e91 !important;font-weight: bold ">Menu de Hechizos</h1>
      </div>
    </div>
    <!--FINISH TITLE-->

    <!--CREAR HEROE-->
    <div class="container" style="margin-bottom: 20px">

    <a href="<%=request.getContextPath()%>/HechizoServlet?action=agregarHechizos" class="btn btn-success" >Añadir nuevo Hechizo</a>

    </div>
      <!--FINISH CREAR HEROE-->

    <!--BUSCAR HEROE POR NOMBRE-->

    <!-- FINISH BUSCAR HEROE POR NOMBRE-->


    <!--TABLA-->
    <div class="tabla " style="background-color: #332d2d"> <!-- #f8f9fa -->
      <table class="table table-dark table-transparent table-hover">
        <thead style="color: white">
        <tr>
          <!--<th>ID Heroe</th>-->
          <th>Id Hechizo</th>
          <th>Nombre Hechizo</th>
          <th>Precisión</th>
          <th>Hechizo base </th>
          <th>Nivel de Aprendizaje </th>

          <th></th>

        </tr>
        </thead>

        <tbody style="color: white">
        <% for (Hechizo hechizo : listaHechizos) { %>
        <% if(!hechizo.isBorradoLogico()){%>
        <tr>
            <td><%=hechizo.getIdHechizo()%></td>
            <td><%=hechizo.getNombreHechizo()%></td>
            <td><%=hechizo.getPresicionHechizo()%></td>
            <%if(hechizo.getHechizoBase().getNombreHechizo()==null){%>
              <td>Este es un hechizo base</td>
            <% }else { %>
            <td><%=hechizo.getHechizoBase().getNombreHechizo()%></td>
            <% } %>
            <td><%=hechizo.getNivelAprendizaje()%></td>

            <td>
              <a type="button" class="btn btn-danger"
                 onclick="return confirm('¿Estas seguro(a) que deseas borrar?')"
                 href="<%=request.getContextPath()%>/HechizoServlet?action=eliminarHechizo&id=<%=hechizo.getIdHechizo()%>">
                <i class="bi bi-trash"></i>
              </a>
            </td>

        </tr>
        <%}%>
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
