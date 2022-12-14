<%--
  Created by IntelliJ IDEA.
  User: rodro
  Date: 11/6/22
  Time: 11:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.magenta.lab8_magenta.model.beans.Objeto" %>
<%@ page import="com.magenta.lab8_magenta.model.beans.Heroe" %>

<jsp:useBean type="com.magenta.lab8_magenta.model.beans.Heroe" scope="request" id="heroe"/>
<jsp:useBean type="com.magenta.lab8_magenta.model.beans.Objeto" scope="request" id="objeto"/>
<jsp:useBean type="java.lang.Integer" scope="request" id="cantidad"/>
<%String error1 = (String) request.getAttribute("error1");%>
<%String error2 = (String) request.getAttribute("error2");%>
<%String error3 = (String) request.getAttribute("error3");%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> editarObjetoInventario </title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
        <link rel="shortcut icon" href="<%=request.getContextPath()%>/static/favicon2.ico" type="image/x-icon">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
        <link rel="stylesheet" href=../css/style.min.css>
        <!-- CSS de registrar-flujo-usuarioo -->
        <link rel="stylesheet" href="../css/form1.css">
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
                        <jsp:param name="page" value="editarObjetoInventario"/>
                    </jsp:include>
                </div>
            </div>
            <!--FINISH MENU-->


            <!--CONTENT-->
            <div class='container' style="margin-top: 30px;">

                <!--TITLE-->
                <div class="pt-4 titlecolor">
                    <div class="col-lg-6">
                        <h1 class='text-dark' class='text-dark' style="color: white !important;font-weight: bold ">Editar Objeto en el inventario de <%=heroe.getNombre()%></h1>
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
                                <div class="ibox-body" style="padding-top: 20px; padding-bottom: 20px;padding-left: 15px;padding-right: 15px" >


                                    <form method="post" action="<%=request.getContextPath()%>/InventarioServlet?action=actualizarObjetoInventario">
                                        <!-- 1era fila -->

                                        <div class="row g-2">
                                            <div class="col-md-3"  style="display: flex; justify-content: center;  flex-direction: column">
                                                <p class="campos-registrar-usuario">Nombre:</p>
                                            </div>
                                            <div class="col-md">
                                                <input type="hidden" name="idObjeto" value="<%= objeto.getIdObjeto()%>"/>
                                                <input type="hidden" name="idHeroe" value="<%=heroe.getIdHeroe()%>"/>
                                                <div class="form-floating" style="margin-bottom: 15px">
                                                    <input style="background-color: #4d4545;color: white" type="text" class="form-control" id="floatingInputGrid2" value="<%= objeto.getNombreObjeto()%>" placeholder="Nombre Objeto" name="nombreObjeto" disabled>
                                                    <label style="color: white" for="floatingInputGrid2" class="label-form-flujousuario">Nombre Objeto</label>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- 2da fila -->
                                        <div class="row g-2">
                                            <div class="col-md-3" style="display: flex; justify-content: center;  flex-direction: column">
                                                <p class="campos-registrar-usuario">Efecto:</p>
                                            </div>
                                            <div class="col-md">
                                                <div class="form-floating" style="margin-bottom: 15px;">
                                                    <input style="background-color: #4d4545;color: white" type="text" class="form-control" id="floatingInputGrid4" value="<%= objeto.getEfecto()%>" placeholder="Efecto" name="efecto" disabled>
                                                    <label style="color: white" for="floatingInputGrid4" class="label-form-flujousuario">Efecto</label>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- 3era fila -->
                                        <div class="row g-2">
                                            <div class="col-md-3" style="display: flex; justify-content: center;  flex-direction: column">
                                                <p class="campos-registrar-usuario">Peso (kg): </p>
                                            </div>
                                            <div class="col-md">
                                                <div class="form-floating" style="margin-bottom: 15px;">
                                                    <div class="form-floating" style="margin-bottom: 15px;">
                                                        <input style="background-color: #4d4545;color: white" type="text" class="form-control" id="floatingInputGrid1" value="<%= objeto.getPeso()%>" placeholder="Peso" name="peso" disabled>
                                                        <label style="color: white" for="floatingInputGrid1" class="label-form-flujousuario">Peso</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>


                                        <!-- 4ta fila -->
                                        <div class="row g-2">
                                            <div class="col-md-3" style="display: flex; justify-content: center;  flex-direction: column">
                                                <p class="campos-registrar-usuario">Cantidad:</p>
                                            </div>
                                            <div class="col-md">
                                                <div class="form-floating" style="margin-bottom: 15px;">
                                                    <div class="form-floating" style="margin-bottom: 15px;">
                                                        <input style="background-color: #4d4545;color: white" type="text" class="form-control <%=error1!=null?"is-invalid":""%> <%=error2!=null?"is-invalid":""%> <%=error3!=null?"is-invalid":""%>" id="floatingInputGrid3" value="<%=cantidad%>" placeholder="Cantidad" name="cantidad">
                                                        <label style="color: white" for="floatingInputGrid3" class="label-form-flujousuario">Cantidad</label>
                                                        <%if(error1!=null){%>
                                                        <div id="validationServer" class="invalid-tooltip">
                                                            <%=error1%>
                                                        </div>
                                                        <%}%>
                                                        <%if(error2!=null){%>
                                                        <div id="validationServer" class="invalid-tooltip">
                                                            <%=error2%>
                                                        </div>
                                                        <%}%>
                                                        <%if(error3!=null){%>
                                                        <div id="validationServer" class="invalid-tooltip">
                                                            <%=error3%>
                                                        </div>
                                                        <%}%>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>




                                        <!-- 5ta fila -->

                                        <div class="form-group" style="text-align: right">
                                            <button type="submit" class="btn btn-primary"> Editar Objeto</button>
                                            <a href="<%=request.getContextPath()%>/InventarioServlet?action=inventarioHeroe&id=<%=heroe.getIdHeroe()%>" class="btn btn-secondary">Cancelar</a>
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
