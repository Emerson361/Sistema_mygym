<%-- 
    Document   : Ventas
    Created on : 16 may. 2024, 15:14:22
    Author     : Axel
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script> <!-- sweetalert -->
        <title>Ventas</title>        
    </head>
    <body>

        <nav class="navbar navbar-light bg-light justify-content-center">
            <form class="form-inline">
                <input class="form-control mr-sm-2 mb-3" type="search" placeholder="Buscar" id="buscarDato" style="width: 500px;">
            </form>
        </nav>

        <div class="d-flex"> 

            <div class="card col-sm-3">
                <h2 class="text-center">Ventas</h2>
                <div class="card-body">
                    
                        <form action="Controlador?menu=Ventas" method="POST">
                            <div class="form-group">
                                <label>Cliente:</label>
                                <select name="txtIdCliente" class="form-control" required>
                                    <c:forEach var="cliente" items="${clientes}">
                                        <option value="${cliente.getId()}" ${cliente.getId() == venta.getIdCliente() ? 'selected' : ''}>
                                            ${cliente.getNombre()} ${cliente.getApellido()}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group">
                                <label>Fecha de Venta:</label>
                                <input type="date" value="${venta.getFechaVenta()}" name="txtFechaVenta" class="form-control" required>
                            </div>

                            <div class="form-group">
                                <label>Observación:</label>
                                <input type="text" value="${venta.getObservacion()}" name="txtObservacion" class="form-control" required>
                            </div>

                            <div class="form-group">
                                <label>Fecha de Inicio:</label>
                                <input type="date" value="${venta.getFechaInicio()}" name="txtFechaInicio" class="form-control" required>
                            </div>

                            <div class="form-group">
                                <label>Fecha Final:</label>
                                <input type="date" value="${venta.getFechaFinal()}" name="txtFechaFinal" class="form-control" required>
                            </div>

                            <div class="form-group">
                                <label>Horario:</label>
                                <select class="form-control" value="${venta.getHorario()}" name="txtHorario">
                                    <option value="5:00 - 13:00" ${venta.getHorario().equals("5:00 - 13:00") ? 'selected' : ''}>5:00 - 13:00</option>
                                    <option value="8:00 - 16:00" ${venta.getHorario().equals("8:00 - 16:00") ? 'selected' : ''}>8:00 - 16:00</option>
                                    <option value="13:00 - 21:00" ${venta.getHorario().equals("13:00 - 21:00") ? 'selected' : ''}>13:00 - 21:00</option>
                                    <option value="14:00 - 22:00" ${venta.getHorario().equals("14:00 - 22:00") ? 'selected' : ''}>14:00 - 22:00</option>
                                </select>  
                            </div>

                            <div class="form-group">
                                <label>Membresía:</label>
                                <select name="txtIdMembresia" class="form-control" required>
                                    <c:forEach var="membresia" items="${membresias}">
                                        <option value="${membresia.getId()}" ${membresia.getId() == venta.getIdMembresia() ? 'selected' : ''}>
                                            ${membresia.getTipoMembresia()}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group">
                                <label>Dieta:</label>
                                <select name="txtIdDieta" class="form-control">
                                    <c:forEach var="dieta" items="${dietas}">
                                        <option value="${dieta.getIdDieta()}" ${dieta.getIdDieta() == venta.getIdDieta() ? 'selected' : ''}>
                                            ${dieta.getNombre()}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group">
                                <label>Rutina:</label>
                                <select name="txtIdRutina" class="form-control" required>
                                    <c:forEach var="rutina" items="${rutinas}">
                                        <option value="${rutina.getId()}" ${rutina.getId() == venta.getIdRutina() ? 'selected' : ''}>
                                            ${rutina.getNombre()}
                                        </option>
                                    </c:forEach>
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
                            <th>Cliente</th>
                            <th>Fecha de Venta</th>
                            <th>Observación</th>
                            <th>Fecha de Inicio</th>
                            <th>Fecha Final</th>
                            <th>Horario</th>
                            <th>Membresía</th>
                            <th>Dieta</th>
                            <th>Rutina</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>

                    <tbody id="TablaVenta">
                        <c:forEach var="venta" items="${ventas}">
                            <tr>
                                <td>${venta.getIdVentas()}</td>
                                <td>${venta.getCliente()}</td>
                                <td>${venta.getFechaVenta()}</td>
                                <td>${venta.getObservacion()}</td>
                                <td>${venta.getFechaInicio()}</td>
                                <td>${venta.getFechaFinal()}</td>
                                <td>${venta.getHorario()}</td>
                                <td>${venta.getMembresia()}</td>
                                <td>${venta.getDieta()}</td>
                                <td>${venta.getRutina()}</td>
                                <td>
                                    <a class="btn btn-warning" href="Controlador?menu=Ventas&accion=Editar&id=${venta.getIdVentas()}">Editar</a>
                                    <a class="btn btn-danger" href="Controlador?menu=Ventas&accion=Eliminar&id=${venta.getIdVentas()}">Eliminar</a>
                                </td>                       
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
            </div>

            <script> <!-- Buscador --> 
                document.getElementById('buscarDato').addEventListener('input', function () {
                    var Buscar = this.value.trim().toLowerCase();
                    var rows = document.querySelectorAll('#TablaVenta tr');
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

            
            
        </div><!-- Final 1er div -->

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    </body>
</html>
