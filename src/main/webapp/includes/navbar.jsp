<%--

  NO TOCAR!


--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar sticky-top navbar-expand-lg navbar-dark">
    <a class="navbar-brand" href="<%=request.getContextPath()%>/MainServlet" style="font-size: 32px;font-weight: bold">La Ultima Fantasia</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link <%=request.getParameter("page").equals("tours")? "active": "" %>" href="<%=request.getContextPath()%>/listaTours" style="font-size: 17px">Heroes</a>
            </li>
            <li class="nav-item">
                <a class="nav-link <%=request.getParameter("page").equals("listaEnemigos")? "active": "" %>" href="<%=request.getContextPath()%>/EnemigoServlet" style="font-size: 17px">Enemigos</a>
            </li>
            <li class="nav-item">
                <a class="nav-link <%=request.getParameter("page").equals("tpc")? "active": "" %>" href="<%=request.getContextPath()%>/listaToursporCiudad" style="font-size: 17px">Hechizos</a>
            </li>
            <li class="nav-item">
                <a class="nav-link <%=request.getParameter("page").equals("canciones")? "active": "" %>" href="<%=request.getContextPath()%>/listaCanciones" style="font-size: 17px">Objetos</a>
            </li>
            <li class="nav-item">
                <a class="nav-link <%=request.getParameter("page").equals("artistas")? "active": "" %>" href="<%=request.getContextPath()%>/listaArtistas" style="font-size: 17px">Artistas</a>
            </li>
            <li class="nav-item">
                <a class="nav-link <%=request.getParameter("page").equals("recomendados")? "active": "" %>" href="<%=request.getContextPath()%>/listaRecomendados" style="font-size: 17px">Recomendados</a>
            </li>

        </ul>
    </div>
</nav>

