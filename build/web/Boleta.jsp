<%-- 
    Document   : Boleta
    Created on : 2 jul. 2024, 22:18:22
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
        <title>Boleta</title>        
    </head>
    <body>
        <nav class="navbar navbar-light bg-light justify-content-center">
            <form class="form-inline">
                <input class="form-control mr-sm-2 mb-3" type="search" placeholder="Buscar" id="buscarDato" style="width: 500px;">
            </form>
        </nav>
        <div class="d-flex"> 

            <div class="card col-sm-3">
                <h2 class="text-center">Boleta</h2>
                <div class="card-body">
                    
                        <form action="Controlador?menu=Boleta" method="POST">
                            <div class="form-group">
                                <label>Recibo:</label>
                                <select name="txtIdRecibo" class="form-control" required>
                                    <c:forEach var="venta" items="${ventas}">
                                        <option value="${venta.getId_recibo()}" ${venta.getId_recibo() == boleta.getId_recibo() ? 'selected' : ''}>
                                            ${venta.getId_recibo()}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group">
                                <label>Fecha de Pago:</label>
                                <input type="date" id="txtFechaPago" value="${boleta.getFecha_pago()}" name="txtFechaPago" class="form-control" required>
                            </div>

                            <div class="form-group">
                                <label>Tipo de Pago:</label>
                                <select name="txtTipoPago" class="form-control" required>
                                    <option value="Efectivo" ${boleta.getTipo_pago() == 'Efectivo' ? 'selected' : ''}>Efectivo</option>
                                    <option value="Tarjeta" ${boleta.getTipo_pago() == 'Tarjeta' ? 'selected' : ''}>Tarjeta</option>
                                </select>
                            </div>

                            <div class="form-group">
                                <label>Estado Pago:</label>
                                <select name="txtEstadoPago" class="form-control">
                                    <option value="Pagado" ${boleta.getEstado_pago() == 'Pagado' ? 'selected' : ''}>Pagado</option>
                                    <option value="Faltante" ${boleta.getEstado_pago() == 'Faltante' ? 'selected' : ''}>Faltante</option>
                                    <option value="Pendiente" ${boleta.getEstado_pago() == 'Pendiente' ? 'selected' : ''}>Pendiente</option>
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
                            <th>Recibo</th>
                            <th>Fecha Pago</th>
                            <th>Tipo Pago</th>
                            <th>Estado Pago</th>
                        </tr>
                    </thead>

                    <tbody id="TablaBoleta">
                        <c:forEach var="boleta" items="${boletas}">
                            <tr>
                                <td>${boleta.getId_boleta()}</td>
                                <td>${boleta.getId_recibo()}</td>
                                <td>${boleta.getFecha_pago()}</td>
                                <td>${boleta.getTipo_pago()}</td>
                                <td>${boleta.getEstado_pago()}</td>
                                <td>
                                    <a class="btn btn-warning" href="Controlador?menu=Boleta&accion=Editar&id=${boleta.getId_boleta()}">Editar</a>
                                    <a class="btn btn-danger" href="Controlador?menu=Boleta&accion=Eliminar&id=${boleta.getId_boleta()}">Eliminar</a>
                                </td>                       
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
            </div>

            <script> <!-- Buscador --> 
                document.getElementById('buscarDato').addEventListener('input', function () {
                    var Buscar = this.value.trim().toLowerCase();
                    var rows = document.querySelectorAll('#TablaBoleta tr');
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
        
        <script>
            // Set the current date if the value is empty
            var fechaPagoInput = document.getElementById('fechaPago');
            if (!fechaPagoInput.value) {
                fechaPagoInput.value = new Date().toISOString().slice(0, 10);
            }
        </script>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
