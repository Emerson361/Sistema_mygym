<%-- 
    Document   : Administrador
    Created on : 18 abr. 2024, 15:14:22
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
        <title>Rutina</title>        
    </head>
    <body>

        <nav class="navbar navbar-light bg-light justify-content-center">
            <form class="form-inline">
                <input class="form-control mr-sm-2 mb-3" type="search" placeholder="Buscar" id="buscarDato" style="width: 500px;">
            </form>
        </nav>
        
        <div class="d-flex"> 
            <div class="card col-sm-3">
                <h2 class="text-center">Rutina</h2>
                <div class="card-body">
                    <form action="Controlador?menu=Rutina" method="POST">
                        <div class="form-group">
                            <label>Nombre:</label>
                            <input type="text" value="${rutina.getNombre()}" name="txtNombre" class="form-control" required>
                        </div>

                        <div class="form-group">
                            <label>Descripción:</label>
                            <input class="form-control" value="${rutina.getDescripcion()}" name="txtdescripcion" required></input>                         
                        </div>

                        <div class="form-group">
                            <label>Dificultad:</label>
                            <select class="form-control" value="${rutina.getDificultad()}" name="txtdificultad" required>
                                <option ${rutina.getDificultad().equals("Principiante") ? 'selected' : ''}>Principante</option>
                                <option ${rutina.getDificultad().equals("Intermedio") ? 'selected' : ''}>Intermedio</option>
                                <option ${rutina.getDificultad().equals("Avanzado") ? 'selected' : ''}>Avanzado</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label>Frecuencia:</label>
                            <select class="form-control" value="${rutina.getFrecuencia()}" name="txtfrecuencia" required>
                                <option ${rutina.getFrecuencia().equals("1 vez a la semana") ? 'selected' : ''}>1 vez a la semana</option>
                                <option ${rutina.getFrecuencia().equals("2 veces a la semana") ? 'selected' : ''}>2 veces a la semana</option>
                                <option ${rutina.getFrecuencia().equals("3 veces a la semana") ? 'selected' : ''}>3 veces a la semana</option>
                            </select>
                        </div>
                         
                        <div class="form-group">
                            <label>Entrenador:</label>
                            <select class="form-control" name="txtentrenador" required>
                                <c:forEach var="ent" items="${entrenadores}">
                                    <!<!--value="${ent.getNombre()}" asegura que el valor del option sea el ID del entrenador. -->
                                    <option  value="${ent.getId()}">${ent.getNombre()} ${ent.getApellido()}</option>
                                </c:forEach>
                            </select><br><br>
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
                            <th>Descripción</th>
                            <th>Dificultad</th>
                            <th>Frecuencia</th>
                            <th>Entrenador</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>

                    <tbody id="TablaRutina">
                        <c:forEach var="rut" items="${rutinas}">
                            <tr>
                                <td>${rut.getId()}</td> 
                                <td>${rut.getNombre()}</td>
                                <td>${rut.getDescripcion()}</td>
                                <td>${rut.getDificultad()}</td>
                                <td>${rut.getFrecuencia()}</td>
                                <td>${rut.getEntrenador()}</td>
                                <td>
                                    <a class="btn btn-warning" href="Controlador?menu=Rutina&accion=Editar&id=${rut.getId()}">Editar</a>
                                    <a class="btn btn-danger" href="Controlador?menu=Rutina&accion=Eliminar&id=${rut.getId()}">Delete</a>
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
                var rows = document.querySelectorAll('#TablaRutina tr');
                rows.forEach(function (row) {
                    var nombre = row.querySelector('td:nth-child(2)').textContent.toLowerCase();
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
