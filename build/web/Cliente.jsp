<%-- 
    Document   : Cliente
    Created on : 13 may. 2024, 08:32:07
    Author     : EMERSOM APAZA
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script> <!-- sweetalert -->
        <title>Cliente</title>        
    </head>
    <body>

        <nav class="navbar navbar-light bg-light justify-content-center">
            <form class="form-inline">
                <input class="form-control mr-sm-2 mb-3" type="search" placeholder="Buscar" id="buscarDato" style="width: 500px;">
            </form>
        </nav>

        <div class="d-flex">
            <div class="card col-sm-3">
                <h2 class="text-center">Cliente</h2>
                <div class="card-body">
                    <form action="Controlador?menu=Cliente" method="POST">
                        
                        <div class="form-group">
                            <label>Nombre:</label>
                            <input type="text" value="${cliente.getNombre()}" name="txtNombre" class="form-control">
                        </div>

                        <div class="form-group">
                            <label>Apellido:</label>
                            <input type="text" value="${cliente.getApellido()}" name="txtApellido" class="form-control">
                        </div>

                        <div class="form-group">
                            <label>Tipo Documento:</label>
                            <select class="form-control" value="${cliente.getTipo_doc()}" name="txtTipoDocumento">
                                <option ${cliente.getTipo_doc().equals("Dni") ? 'selected' : ''}>Dni</option>
                                <option ${cliente.getTipo_doc().equals("Pasaporte") ? 'selected' : ''}>Pasaporte</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label>Numero Documento:</label>
                            <input type="text" value="${cliente.getNum_doc()}" name="txtDocumento" class="form-control" required>
                        </div>

                        <div class="form-group">
                            <label>Telefono:</label>
                            <input type="text" value="${cliente.getTelefono()}" name="txtTelefono" class="form-control" required>
                        </div>
                        
                        <div class="form-group">
                            <label>Correo Electronico:</label>
                            <input type="text" value="${cliente.getCorreo()}" name="txtCorreo" class="form-control" required>
                        </div>

                        <div class="form-group">
                            <label>Genero:</label>
                            <select class="form-control" value="${cliente.getGenero()}" name="txtgenero">
                                <option ${cliente.getGenero().equals("Masculino") ? 'selected' : ''}>Masculino</option>
                                <option ${cliente.getGenero().equals("Femenino") ? 'selected' : ''}>Femenino</option>
                            </select>
                        </div>
                            
                        <div class="form-group">
                            <label>Fecha de nacimiento:</label>
                            <input type="date" value="${cliente.getFecha_nac()}" name="txtfechanac" class="form-control" required>
                        </div>
                         
                        <div class="form-group">
                            <label>Nombre Usuario:</label>
                            <input type="text" value="${cliente.getUsuario()}" name="txtNombreUsuario" class="form-control" required>
                        </div>

                        <div class="form-group">
                            <label>Contraseña:</label>
                            <input type="password" value="${cliente.getPassword()}" name="txtContrasena" class="form-control" required>
                        </div>

                        <input type="submit" name="accion" value="Agregar" class="btn btn-info">
                        <input type="submit" name="accion" value="Actualizar" class="btn btn-success">
                    </form>
                </div>
            </div>

            <!-- Alerta agregado y actualizado -->        
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

            <div class="col-sm-8 text-center">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>Tipo Documento</th>
                            <th>Numero Documento</th>
                            <th>Telefono</th>
                            <th>Correo</th>
                            <th>Genero</th>
                            <th>Fecha Nacimiento</th>
                            <th>Usuario</th>
                            <th>Contraseña</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>

                    <tbody id="TablaCliente">
                        <c:forEach var="cli" items="${clientes}">
                            <tr>
                                <td>${cli.getId()}</td> 
                                <td>${cli.getNombre()}</td>
                                <td>${cli.getApellido()}</td>
                                <td>${cli.getTipo_doc()}</td>
                                <td>${cli.getNum_doc()}</td>
                                <td>${cli.getTelefono()}</td>
                                <td>${cli.getCorreo()}</td>
                                <td>${cli.getGenero()}</td>
                                <td>${cli.getFecha_nac()}</td>
                                <td>${cli.getUsuario()}</td>
                                <td>${cli.getPassword()}</td>
                                <td>
                                    <a class="btn btn-warning" href="Controlador?menu=Cliente&accion=Editar&id=${cli.getId()}">Editar</a>
                                    <a class="btn btn-danger" href="Controlador?menu=Cliente&accion=Eliminar&id=${cli.getId()}">Delete</a>
                                </td>                       
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
            </div>
        </div><!-- Final 1er div -->

                <script> <!-- Buscador --> 
                    document.getElementById('buscarDato').addEventListener('input', function () {
                        var Buscar = this.value.trim().toLowerCase();
                        var rows = document.querySelectorAll('#TablaCliente tr');
                        rows.forEach(function (row) {
                            var nombre = row.querySelector('td:nth-child(5)').textContent.toLowerCase();
                            if (nombre.includes(Buscar)) {
                                row.style.display = 'table-row';
                            } else {
                                row.style.display = 'none';
                            }
                        });
                    });
          </script>

        <!-- Alerta eliminado -->        
            <%
                String msjEliminar = (String) request.getAttribute("eliminar");
                if (msjEliminar != null) {
            %>
            <script>
                // Muestra una alerta de SweetAlert2
                Swal.fire({
                    icon: 'success',
                    title: 'Éxito',
                    text: '<%= msjEliminar%>',
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

