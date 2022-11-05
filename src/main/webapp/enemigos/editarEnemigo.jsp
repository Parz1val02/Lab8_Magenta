<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.magenta.lab8_magenta.model.beans.Genero" %>
<%@ page import="com.magenta.lab8_magenta.model.beans.ClaseEnemigo" %>
<%@ page import="com.magenta.lab8_magenta.model.beans.Objeto" %>


<jsp:useBean type="com.magenta.lab8_magenta.model.beans.Enemigo" scope="request" id="enemigo"/>

<jsp:useBean type="java.util.ArrayList<com.magenta.lab8_magenta.model.beans.Objeto>" scope="request" id="listaObjetos"/>
<jsp:useBean type="java.util.ArrayList<com.magenta.lab8_magenta.model.beans.Genero>" scope="request" id="listaGeneros"/>
<jsp:useBean type="java.util.ArrayList<com.magenta.lab8_magenta.model.beans.ClaseEnemigo>" scope="request" id="listaClases"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title> editarEnemigos </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/static/favicon2.ico" type="image/x-icon">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href=css/style.min.css>
    <!-- CSS de registrar-flujo-usuarioo -->
    <link rel="stylesheet" href="css/form1.css">
    <style>
        body {
            /*background-color: white;*/

        }
        .fondo_imagen {
            height: 100%;
            margin: 0;
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
            background-image: url("img/fondo_oscuro.jpg");
        }

        .container-fluid {
            padding-right: 0px !important;
            padding-left: 0px !important;
        }

        .titlecolor {
            /*background: linear-gradient(0deg, #1d1d1d 0%, #525252 100%);*/
            background: none; /*white*/
        }

        .navbar {
            padding-left: 20px !important;
            padding-right: 20px !important;
            /*background-color: rgb(232 17 187 / 94%) !important;*/
        }

        .table-transparent {
            background-color: #343a4000 !important
        }

        .tabla {
            background-color: #1d1d1d;
            padding-left: 30px;
            padding-right: 30px;
        }

        thead {
            border-top: hidden;
            font-size: 14px;
        }

        .table td {
            padding: 0.5rem;
            font-weight: 500;
        }

        .table th {
            padding: 0.5rem;
            font-weight: 100;
        }

        .fila-red {
            background-color: #ba49498c;
        }

        .fila-blue {
            background-color: #2441ac8c;
        }

        .fila-purple {
            background-color: #a4219a8c;
        }

        .fila-yellow {
            background-color: #f0e01f91;
        }


    </style>
</head>




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
        <div class="pt-4 titlecolor">
            <div class="col-lg-6">
                <h1 class='text-dark' class='text-dark' style="color: #6f2e91 !important;font-weight: bold ">Agregar Enemigos</h1>
            </div>
        </div>
        <!--FINISH TITLE-->


        <!--FORM ENEMIGOS-->
        <div class="row align-items-center">
            <div class="page-content fade-in-up col-md" style="align-content: center">
                <div class="container" style=" height: 100%">
                    <!--<div class="page-heading" style="text-align: center">
                      <h1 class="page-title" style="font-size: 40px; font-weight: bold"><b>Registrar nuevo Enemigo</b></h1>
                    </div> -->
                    <div class="ibox" style="align-content: center; color: white; background-color: #332d2d; border: none"> <!--class="ibox"-->
                        <!--div class="ibox-head">
                            <div class="ibox-title" style="font-size: 20px">Registrar Incidencia</div>
                            <div class="ibox-tools">
                                <a class="ibox-collapse"><i class="fa fa-minus"></i></a>
                            </div>
                        </div-->
                        <div class="ibox-body" style="padding-top: 35px" >


                            <form method="post" action="<%=request.getContextPath()%>/EnemigoServlet?action=actualizarEnemigo">
                                <!-- 1era fila -->

                                <div class="row g-2">
                                    <div class="col-md-3"  style="display: flex; justify-content: center;  flex-direction: column">
                                        <p class="campos-registrar-usuario">Nombre:</p>
                                    </div>
                                    <div class="col-md">
                                        <input type="hidden" name="idEnemigo" value="<%= enemigo.getIdEnemigo()%>"/>
                                        <div class="form-floating" style="margin-bottom: 15px">
                                            <input style="background-color: #4d4545;color: white" type="text" class="form-control" id="floatingInputGrid2" value="<%= enemigo.getNombreEnemigo()%>" placeholder="Nombre Enemigo" name="nombreEnemigo">
                                            <label style="color: white" for="floatingInputGrid2" class="label-form-flujousuario">Nombre Enemigo</label>
                                        </div>
                                    </div>
                                </div>
                                <!-- 2da fila -->
                                <div class="row g-2">
                                    <div class="col-md-3" style="display: flex; justify-content: center;  flex-direction: column">
                                        <p class="campos-registrar-usuario">Ataque:</p>
                                    </div>
                                    <div class="col-md">
                                        <div class="form-floating" style="margin-bottom: 15px;">
                                            <input style="background-color: #4d4545;color: white" type="text" class="form-control" id="floatingInputGrid4" value="<%= enemigo.getAtaque()%>" placeholder="Ataque" name="ataque">
                                            <label style="color: white" for="floatingInputGrid4" class="label-form-flujousuario">Ataque</label>
                                        </div>
                                    </div>
                                </div>

                                <!-- 3era fila -->
                                <div class="row g-2">
                                    <div class="col-md-3" style="display: flex; justify-content: center;  flex-direction: column">
                                        <p class="campos-registrar-usuario">Experiencia al ser Derrotado:</p>
                                    </div>
                                    <div class="col-md">
                                        <div class="form-floating" style="margin-bottom: 15px;">
                                            <div class="form-floating" style="margin-bottom: 15px;">
                                                <input style="background-color: #4d4545;color: white" type="text" class="form-control" id="floatingInputGrid12" value="<%= enemigo.getExperienciaDerrotado()%>" placeholder="Experiencia al ser Derrotado" name="experienciaDerrotado">
                                                <label style="color: white" for="floatingInputGrid12" class="label-form-flujousuario">Experiencia al ser Derrotado</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                                <!-- 4ta fila -->
                                <div class="row g-2">
                                    <div class="col-md-3" style="display: flex; justify-content: center;  flex-direction: column">
                                        <p class="campos-registrar-usuario">Probabilidad de dejar Objeto:</p>
                                    </div>
                                    <div class="col-md">
                                        <div class="form-floating" style="margin-bottom: 15px;">
                                            <input style="background-color: #4d4545;color: white" type="text" class="form-control" id="floatingInputGrid20" value="<%= enemigo.getProbDejarObjeto()%>" placeholder="Probabilidad de dejar Objeto" name="probabilidadDejarObjeto">
                                            <label style="color: white" for="floatingInputGrid20" class="label-form-flujousuario">Probabilidad de dejar Objeto</label>
                                        </div>
                                    </div>
                                </div>


                                <!-- 5ta fila -->
                                <div class="row g-2">
                                    <div class="col-md-3" style="display: flex; flex-direction: column">
                                        <p class="campos-registrar-usuario">Clase:</p>
                                    </div>
                                    <div class="col-md">
                                        <div class="form-floating" style="margin-bottom: 15px;">

                                            <select style="background-color: #4d4545;color: white" class="form-select" id="floatingSelectGrid3" name="idClaseEnemigo">
                                                <% for (ClaseEnemigo claseEnemigo : listaClases) { %>
                                                <option value="<%=claseEnemigo.getIdClaseEnemigo()%>" <%=enemigo.getClaseEnemigo().getIdClaseEnemigo() == claseEnemigo.getIdClaseEnemigo() ? "selected" : ""%>> <%=claseEnemigo.getNombreClase()%> </option>
                                                <% } %>
                                            </select>
                                            <label style="color: white" for="floatingSelectGrid3" class="label-form-flujousuario">Clase</label>

                                        </div>
                                    </div>

                                </div>



                                <!-- 6ta fila -->
                                <div class="row g-2">
                                    <div class="col-md-3" style="display: flex; justify-content: center;  flex-direction: column">
                                        <p class="campos-registrar-usuario">Genero:</p>
                                    </div>
                                    <div class="col-md">
                                        <div class="form-floating " style="margin-bottom: 15px;">
                                            <select style="background-color: #4d4545;color: white" class="form-select" id="floatingSelectGrid2" name="idGenero">
                                                <option value="0"> -- </option>
                                                <% for (Genero genero : listaGeneros) { %>
                                                <option value="<%=genero.getIdGenero()%>"  <%=enemigo.getGenero().getIdGenero() == genero.getIdGenero() ? "selected" : ""%>    >   <%=genero.getInicial()%>
                                                </option>
                                                <% } %>
                                            </select>
                                            <label style="color: white" for="floatingSelectGrid2" class="label-form-flujousuario">Genero</label>
                                        </div>
                                    </div>
                                </div>


                                <!-- 7ma fila -->
                                <div class="row g-2">
                                    <div class="col-md-3" style="display: flex; justify-content: center;  flex-direction: column">
                                        <p class="campos-registrar-usuario">Objeto:</p>
                                    </div>
                                    <div class="col-md">
                                        <div class="form-floating" style="margin-bottom: 15px;">
                                            <select style="background-color: #4d4545;color: white" class="form-select" id="floatingSelectGrid1" name="idObjeto">
                                                <% for (Objeto objeto : listaObjetos) { %>
                                                <option value="<%=objeto.getIdObjeto()%>"   <%=enemigo.getObjeto().getIdObjeto() == objeto.getIdObjeto() ? "selected" : ""%>>     <%=objeto.getNombreObjeto()%>
                                                </option>
                                                <% } %>
                                            </select>
                                            <label  style="color: white" for="floatingSelectGrid1" class="label-form-flujousuario">Objeto</label>
                                        </div>
                                        <div style="color:#FF0000;"><p text-align="center;" style="margin-top: 1px;" class="font-weight-bold">Todos los campos son obligatorios.</p></div>
                                    </div>
                                </div>


                                <div class="form-group" style="text-align: right">
                                    <button type="submit" class="btn btn-primary"> Editar Enemigo</button>
                                    <a href="<%=request.getContextPath()%>/EnemigoServlet?accion=listaEnemigos" class="btn btn-secondary">Cancelar</a>
                                </div>

                            </form>


                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- FINISH BUSCAR HEROE POR NOMBRE-->




    </div>
</div>
<!--FINISH CONTENT-->

<!--SCRIPTS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
<jsp:include page="/static/scripts.jsp"/>
<!--FINISH SCRIPTS -->





</body>
</html>