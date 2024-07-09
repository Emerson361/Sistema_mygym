<%-- 
    Document   : Nuevo Usuario
    Created on : 07/07/2024, 07:55:57 PM
    Author     : Armando Fiestas
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script> <!-- sweetalert -->
    <title>Registro de Usuario</title>
</head>
<body>
    <div class="container mt-5 col-lg-5" >
            <div class="card col-sm-10">
                <div class="card-body">
                    <form action="Controlador?menu=Registro" method="POST">
                        <div class="form-group text-center">
                            <h2>Registro de Usuario</h2>
                            <img src="img/MyGym.png" alt="80" width="210"/>
<!--                            <label>Bienvenidos al sistema</label>-->
                         </div>
                        <div class="form-group">
                            <label >Nombre:</label>
                            <input type="text"  name="txtNombre" required class="form-control"><br>
                        </div>

                        <div class="form-group">
                            <label >Apellidos:</label>
                            <input type="text" name="txtApellido" required class="form-control"><br>
                        </div>

                        <div class="form-group">
                        <label >Tipo Documento:</label>
                        <select name="txtTipoDocumento" required class="form-control">
                            <option value="DNI">DNI</option>
                            <option value="CE">Pasaporte</option>
                        </select><br>
                        </div>
                        
                        <div class="form-group">
                        <label>Número Documento:</label>
                        <input type="text" name="txtDocumento" required class="form-control"><br>
                        </div>
                        
                        <div class="form-group">
                        <label >Teléfono:</label>
                        <input type="text" name="txtTelefono" required class="form-control"><br>
                        </div>
                           
                        <div class="form-group">
                        <label >Correo Electrónico:</label>
                        <input type="email" name="txtCorreo" required class="form-control"><br>
                        </div>

                        <div class="form-group">
                        <label >Género:</label>
                        <select name="txtGenero" required class="form-control">
                            <option value="Masculino">Masculino</option>
                            <option value="Femenino">Femenino</option>
                        </select><br>
                        </div>

                        <div class="form-group">
                        <label >Fecha de Nacimiento:</label>
                        <input type="date" name="txtfechanac" required class="form-control"><br>
                        </div>
                        
                        <div class="form-group">
                        <label>Nombre de Usuario:</label>
                        <input type="text"  name="txtNombreUsuario" required class="form-control"><br>
                        </div>
                        
                        <div class="form-group">
                        <label >Contraseña:</label>
                        <input type="password"  name="txtContrasena" required class="form-control"><br>
                        </div>
                        
                        <input type="submit" name="accion" value="Registrar" class="btn btn-primary">
                       
                        <a href="index.jsp" class="btn btn-link">Regresar al Inicio</a>
                        
                        
                    </form>
                </div>
            </div>
    </div>
            <%
                String mensaje = (String) request.getAttribute("mensaje");
                if (mensaje != null && !mensaje.isEmpty()) {
            %>
            <script>
                // Muestra una alerta de SweetAlert2
                Swal.fire({
                    icon: 'success',
                    title: 'Éxito',
                    text: '<%= mensaje%>',
                    confirmButtonText: 'OK'
                });
            </script>
            <%
                }
            %> 
            <%
                String m1 = (String) request.getAttribute("mensaje1");
                if (m1 != null && !m1.isEmpty()) {
            %>
            <script>
                // Muestra una alerta de SweetAlert2
                Swal.fire({
                    icon: 'warning',
                    title: 'Advertencia',
                    text: '<%= m1%>',
                    confirmButtonText: 'OK'
                });
            </script>
            <%
                }
            %> 
            <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
