<%--

  NO TOCAR!


--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar sticky-top navbar-expand-lg navbar-dark">
    <a class="navbar-brand" href="<%=request.getContextPath()%>/Wiki?action=MenuPrincipal" style="font-size: 32px;font-weight: bold">La Ultima Fantasia</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon" style="background-image: var(--mdb-navbar-toggler-icon-bg);"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link <%=request.getParameter("page").equals("inicio")? "active": "" %>" href="<%=request.getContextPath()%>/Wiki" style="font-size: 17px">Inicio</a>
            </li>
            <li class="nav-item">
                <a class="nav-link <%=request.getParameter("page").equals("listaHeroes")? "active": "" %>" href="<%=request.getContextPath()%>/HeroeServlet" style="font-size: 17px">Heroes</a>
            </li>
            <li class="nav-item">
                <a class="nav-link <%=request.getParameter("page").equals("listaEnemigos")? "active": "" %>" href="<%=request.getContextPath()%>/EnemigoServlet" style="font-size: 17px">Enemigos</a>
            </li>
            <li class="nav-item">
                <a class="nav-link <%=request.getParameter("page").equals("listaHechizos")? "active": "" %>" href="<%=request.getContextPath()%>/HechizoServlet" style="font-size: 17px">Hechizos</a>
            </li>
            <li class="nav-item">
                <a class="nav-link <%=request.getParameter("page").equals("listaObjetos")? "active": "" %>" href="<%=request.getContextPath()%>/ObjetoServlet" style="font-size: 17px">Objetos</a>
            </li>



        </ul>
    </div>
</nav>

