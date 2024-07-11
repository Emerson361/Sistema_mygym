<%-- 
    Document   : VentasCliente
    Created on : 1 jul. 2024, 21:42:34
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
        <title>Ventas</title>        
    </head>
    <body>

     

        <div class="d-flex"> 

            <div class="card col-sm-3">
                <h2 class="text-center">Ventas</h2>
                <div class="card-body">
                    
                        <form action="Controlador?menu=VentasCliente" method="POST">
                            
                            <div class="form-group">
                                <label>Fecha de Venta:</label>
                                <input type="date" value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>" name="txtFechaVenta" class="form-control" required readonly>
                            </div>

                            <div class="form-group">
                                <label>Observación:</label>
                                <input type="text" value="" name="txtObservacion" class="form-control" required>
                            </div>
                            
                            <div class="form-group">
                                <label hidden>Cliente:</label>
                                <input type="text" value="${sessionScope.clientes.id}" name="txtIdCliente" class="form-control" required readonly hidden>              
                            </div>

                            <div class="form-group">
                                <label>Membresía:</label>
                                <select name="txtIdMembresia" class="form-control" required>
                                    <c:forEach var="membresia" items="${membresias}">
                                        <option value="${membresia.getId()}">
                                            ${membresia.getTipoMembresia()}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group">
                                <label>Dieta:</label>
                                <select name="txtIdDieta" class="form-control">
                                    <c:forEach var="dieta" items="${dietas}">
                                        <option value="${dieta.getIdDieta()}">
                                            ${dieta.getNombre()}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group">
                                <label>Rutina:</label>
                                <select name="txtIdRutina" class="form-control" required>
                                    <c:forEach var="rutina" items="${rutinas}">
                                        <option value="${rutina.getId()}">
                                            ${rutina.getNombre()}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <input type="submit" name="accion" value="Agregar" class="btn btn-info">
                           
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
                            <th>Fecha de Venta</th>
                            <th>Observación</th>
                           <th>Cliente</th>
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
                                <td>${venta.getFechaVenta()}</td>
                                <td>${venta.getObservacion()}</td>
                                <td>${venta.getCliente()}</td>
                                <td>${venta.getMembresia()}</td>
                                <td>${venta.getDieta()}</td>
                                <td>${venta.getRutina()}</td>
                                                      
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
