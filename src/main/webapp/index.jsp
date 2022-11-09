<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
    <!--Colocar como value: nombre de la presente página -->
    <jsp:include page="/static/head.jsp">
        <jsp:param name="title" value="Bienvenido"/>
    </jsp:include>
    <body>

        <div class="fondo_imagen">

        <div class = 'container-fluid' style="height: 20px; background-color: #f644dd"></div>


            <div class = "container-fluid" style="background-color:#c40aa8  !important;">    <!--rgb(232 17 187 / 94%) -->
                <div class="container" >
                    <jsp:include page="/includes/navbar.jsp">
                        <jsp:param name="page" value="inicio"/>
                    </jsp:include>
                </div>
            </div>

            <div class='container' style="margin-top: 20px;" >
                <!--Colocar como value: artistas, canciones, bandas, tours o tpc  (dependiendo de la pagina a la que corresponda) -->

                <div class="pb-3 pt-3 px-3 titlecolor d-flex justify-content-between align-items-center">
                    <div class="col-lg">
                        <div class="text-center" ><h1 class='text-dark' style="color: white !important;font-weight: bold "> Bienvenido joven aventurero! Te presentamos la nueva wiki de <br></h1></div>
                        <div class="text-center"><h1 class='text-dark text-center' style="margin-top: 30px;color: white !important;font-weight: bold "> La Ultima Fantasia <br></h1> </div>
                        <div class="text-center"><h4 class='text-dark' style="margin-top: 30px;color: white!important;"> Escoge a tus heroes, identifica a tus enemigos, prepara tus mejores
                            objetos y hechizos! Forond te espera...</h4></div>
                    </div>
                </div>

                <!-- Start your project here-->
                <!-- Carousel wrapper -->
                <div
                        id="carouselBasicExample"
                        class="carousel slide carousel-fade"
                        data-mdb-ride="carousel"
                >
                    <!-- Indicators -->
                    <div class="carousel-indicators">
                        <button
                                type="button"
                                data-mdb-target="#carouselBasicExample"
                                data-mdb-slide-to="0"
                                class="active"
                                aria-current="true"
                                aria-label="Slide 1"
                        ></button>
                        <button
                                type="button"
                                data-mdb-target="#carouselBasicExample"
                                data-mdb-slide-to="1"
                                aria-label="Slide 2"
                        ></button>
                        <button
                                type="button"
                                data-mdb-target="#carouselBasicExample"
                                data-mdb-slide-to="2"
                                aria-label="Slide 3"
                        ></button>
                    </div>

                    <!-- Inner -->
                    <div class="carousel-inner">
                        <!-- Single item -->
                        <div class="carousel-item active">
                            <img style="filter: brightness(0.8)"
                                    src="img/pj1.jpg"
                                    class="d-block w-100"
                                    alt="Sunset Over the City"
                                    width="1320"
                                    height="585"
                            />
                            <div class="carousel-caption d-none d-md-block">
                                <h5>Escoge a tu heroe</h5>
                                <p>
                                    Equipate con tus mejores objetos y hechizos.
                                </p>
                            </div>
                        </div>

                        <!-- Single item -->
                        <div class="carousel-item">
                            <img style="filter: brightness(0.7)"
                                    src="img/pj2.jpg"
                                    class="d-block w-100"
                                    alt="Canyon at Nigh"
                                    width="1320"
                                    height="585"
                            />
                            <div class="carousel-caption d-none d-md-block">
                                <h5>Desafia a nuevos enemigos</h5>
                                <p>
                                    Ten en cuenta sus fortalezas y debilidades.
                                </p>
                            </div>
                        </div>

                        <!-- Single item -->
                        <div class="carousel-item">
                            <img style="filter: brightness(0.7)"
                                    src="img/pj3.jpg"
                                    class="d-block w-100"
                                    alt="Cliff Above a Stormy Sea"
                                    width="1320"
                                    height="585"
                            />
                            <div class="carousel-caption d-none d-md-block">
                                <h5>Derrota a Forond</h5>
                                <p>
                                    Basicamente no tienes oportunidad.
                                </p>
                            </div>
                        </div>
                    </div>
                    <!-- Inner -->

                    <!-- Controls -->
                    <button
                            class="carousel-control-prev"
                            type="button"
                            data-mdb-target="#carouselBasicExample"
                            data-mdb-slide="prev"
                    >
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button
                            class="carousel-control-next"
                            type="button"
                            data-mdb-target="#carouselBasicExample"
                            data-mdb-slide="next"
                    >
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>
                <!-- Carousel wrapper -->
                <!-- End your project here-->
                <!--<div class="tabla" style="background-color: #f8f9fa">
                    <table class="table table-light table-transparent table-hover">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>NOMBRE</th>
                                <th>ROL</th>
                                <th>CORREO</th>
                            </tr>
                        </thead>

                        <tbody>
                            <tr>
                                <td>1
                                </td>
                                <td>Stuardo Lucho
                                </td>
                                <td>Profesor
                                </td>
                                <td>stuardo.lucho@pucp.edu.pe
                                </td>
                            </tr>

                            <tr>
                                <td>2
                                </td>
                                <td>Oscar Diaz
                                </td>
                                <td>JP
                                </td>
                                <td>diaz.oa@pucp.edu.pe
                                </td>
                            </tr>

                            <tr>
                                <td>3
                                </td>
                                <td>Mario Gustavo
                                </td>
                                <td>JP
                                </td>
                                <td>a2015@pucp.edu.pe
                                </td>
                            </tr>

                            <tr>
                                <td>4
                                </td>
                                <td>Rodrigo Adauto
                                </td>
                                <td>JP
                                </td>
                                <td>a20160679@pucp.edu.pe
                                </td>
                            </tr>
                            <tr>
                                <td>4
                                </td>
                                <td>Álvaro Burga
                                </td>
                                <td>JP
                                </td>
                                <td>a20160679@pucp.edu.pe
                                </td>
                            </tr>
                            <tr>
                                <td>4
                                </td>
                                <td>Josué López
                                </td>
                                <td>JP
                                </td>
                                <td>a20160679@pucp.edu.pe
                                </td>
                            </tr>
                            <tr>
                                <td>4
                                </td>
                                <td> Alejandro Macedo
                                </td>
                                <td>JP
                                </td>
                                <td>a20160679@pucp.edu.pe
                                </td>
                            </tr>

                        </tbody>
                    </table>
                </div>-->


            </div>
            <jsp:include page="/static/scripts.jsp"/>
            <!-- MDB -->
            <script type="text/javascript" src="js/mdb.min.js"></script>
        </div>
    </body>
</html>
