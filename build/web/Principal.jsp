<%-- 
    Document   : Principal
    Created on : 18 abr. 2024, 09:33:00
    Author     : EMERSOM APAZA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>MyGym</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-warning">
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item active">
                        <a style="margin-left:10px; border:none" class="btn btn-outline-light" href="#"><strong>Home</strong></span></a>
                    </li>
                    <li class="nav-item">
                        <a style="margin-left:10px; border:none" class="btn btn-outline-light" href="Controlador?menu=Administrador&accion=Listar" target="myFrame"><strong>Administrador</strong></a>
                    </li>
                    <li class="nav-item">
                        <a style="margin-left:10px; border:none" class="btn btn-outline-light" href="Controlador?menu=Cliente&accion=Listar" target="myFrame"><strong>Cliente</strong></a>
                    </li>
                    <li class="nav-item">
                        <a style="margin-left:10px; border:none" class="btn btn-outline-light" href="Controlador?menu=Entrenador&accion=Listar" target="myFrame"><strong>Entrenador</strong></a>
                    </li>
                    <li class="nav-item">
                        <a style="margin-left:10px; border:none" class="btn btn-outline-light" href="Controlador?menu=Nutricionista&accion=Listar" target="myFrame"><strong>Nutricionista</strong></a>
                    </li>
                    <li class="nav-item">
                        <a style="margin-left:10px; border:none" class="btn btn-outline-light" href="Controlador?menu=Dieta&accion=Listar" target="myFrame"><strong>Dieta</strong></a>
                    </li>
                    <li class="nav-item">
                        <a style="margin-left:10px; border:none" class="btn btn-outline-light" href="Controlador?menu=Rutina&accion=Listar" target="myFrame"><strong>Rutina</strong></a>
                    </li>
                    <li class="nav-item">
                        <a style="margin-left:10px; border:none" class="btn btn-outline-light" href="Controlador?menu=Membresia&accion=Listar" target="myFrame"><strong>Membresia</strong></a>
                    </li>
                    <li class="nav-item">
                        <a style="margin-left:10px; border:none" class="btn btn-outline-light" href="#"><strong>Venta</strong></a>
                    </li>
                </ul>
            </div>

            <div style="margin-right: 30px" class="dropdown">
                <button style="border: none" class="btn btn-outline-light dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <strong>${usuario.getNombre()} ${usuario.getApellido()}</strong>
                </button>
                <div class="dropdown-menu text-center">
                    <a class="dropdown-item" href="#">
                        <img src="img/user.png" alt="90" width="90">
                    </a>
                    <a class="dropdown-item" href="#">${usuario.getUsuario()}</a>
                    <a class="dropdown-item" href="#">${usuario.getCorreo()}</a>
                    <div class="dropdown-divider"></div>

                    <form action="Validar" method="POST">
                        <button name="accion" value="Salir" class="dropdown-item" href="#">Salir</button>
                    </form>
                </div>
            </div>
        </nav>

        <div class="m-4" style="height: 600px;">
            <iframe name="myFrame" style="height: 100%; width: 100%; border: none;"></iframe>
        </div>            

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
