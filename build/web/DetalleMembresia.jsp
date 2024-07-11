<%-- 
    Document   : Membresia
    Created on : 11 may. 2024, 10:47:37
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
        <title>Detalle Membresia</title>        
    </head>
    <body>

        <nav class="navbar navbar-light bg-light justify-content-center">
            <form class="form-inline">
                <input class="form-control mr-sm-2 mb-3" type="search" placeholder="Buscar" id="buscarDato" style="width: 500px;">
            </form>
        </nav>

        <div class="d-flex"> 
            <div class="card col-sm-3">
                <h2 class="text-center">Detalle Membresia</h2>
                <div class="card-body">
                    <form action="Controlador?menu=DetalleMembresia" method="POST">
                        <div class="form-group">
                            <label>Fecha inicio:</label>
                            <input type="date" value="${detalle.getFecha()}" name="txtfecha" class="form-control" required>
                        </div>

                        <div class="form-group">
                            <label>Fecha final:</label>
                            <input type="date" value="${detalle.getFechaTermino()}" name="txtfechatermino" class="form-control" required>
                        </div>

                        <div class="form-group">
                            <label>Horario:</label>
                            <select class="form-control" value="${detalle.getHorario()}" name="txtHorario">
                                <option value="5:00 - 13:00" ${detalle.getHorario().equals("5:00 - 13:00") ? 'selected' : ''}>5:00 - 13:00</option>
                                <option value="8:00 - 16:00" ${detalle.getHorario().equals("8:00 - 16:00") ? 'selected' : ''}>8:00 - 16:00</option>
                                <option value="13:00 - 21:00" ${detalle.getHorario().equals("13:00 - 21:00") ? 'selected' : ''}>13:00 - 21:00</option>
                                <option value="14:00 - 22:00" ${detalle.getHorario().equals("14:00 - 22:00") ? 'selected' : ''}>14:00 - 22:00</option>
                            </select>  
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
                            <th>fecha inicio</th>
                            <th>fecha final</th>
                            <th>horario</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>

                    <tbody id="TablaRutina">
                        <c:forEach var="det" items="${detalles}">
                            <tr>
                                <td>${det.getId()}</td> 
                                <td>${det.getFecha()}</td>
                                <td>${det.getFechaTermino()}</td>
                                <td>${det.getHorario()}</td>
                                 
                                <td>
                                    <a class="btn btn-warning" href="Controlador?menu=DetalleMembresia&accion=Editar&id=${det.getId()}">Editar</a>
                                    <a class="btn btn-danger" href="Controlador?menu=DetalleMembresia&accion=Eliminar&id=${det.getId()}">Delete</a>
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