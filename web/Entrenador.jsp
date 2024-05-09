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
        <title>Entrenador</title>        
    </head>
    <body>

        <div class="d-flex"> 
            <div class="card col-sm-3">
                <div class="card-body">
                    <form action="Controlador?menu=Entrenador" method="POST">
                        <div class="form-group">
                            <label>Nombre:</label>
                            <input type="text" value="${entrenador.getNombre()}" name="txtNombre" class="form-control">
                        </div>

                        <div class="form-group">
                            <label>Apellido:</label>
                            <input type="text" value="${entrenador.getApellido()}" name="txtApellido" class="form-control">
                        </div>

                        <div class="form-group">
                            <label>Tipo Documento:</label>
                            <select class="form-control" value="${entrenador.getTipo_doc()}" name="txtTipoDocumento">
                                <option>Dni</option>
                                <option>Pasaporte</option>
                            </select>                          
                        </div>

                        <div class="form-group">
                            <label>Numero Documento:</label>
                            <input type="text" value="${entrenador.getNum_doc()}" name="txtDocumento" class="form-control">
                        </div>

                        <div class="form-group">
                            <label>Telefono:</label>
                            <input type="text" value="${entrenador.getTelefono()}" name="txtTelefono" class="form-control">
                        </div>

                        <div class="form-group">
                            <label>Correo Electronico:</label>
                            <input type="text" value="${entrenador.getCorreo()}" name="txtCorreo" class="form-control">
                        </div>

                        <div class="form-group">
                            <label>Fecha Contratación:</label>
                            <input type="date" value="${entrenador.getFecha()}" name="txtfecha" class="form-control">
                        </div>
                        
                        <div class="form-group">
                            <label>Fecha Finalización:</label>
                            <input type="date" value="${entrenador.getFechaTermino()}" name="txtfechatermino" class="form-control">
                        </div>

                        <div class="form-group">
                            <label>Horario:</label>
                            <select class="form-control" value="${entrenador.getHorario()}" name="txthorario" >
                                <option>5:00 - 13:00</option>
                                <option>8:00 - 16:00</option>
                                <option>13:00 - 21:00</option>
                                <option>14:00 - 22:00</option>
                            </select>  
                        </div>

                        <input type="submit" name="accion" value="Agregar" class="btn btn-info">
                        <input type="submit" name="accion" value="Actualizar" class="btn btn-success">
                    </form>
                </div>
            </div>

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
                            <th>Correo Electronico</th>
                            <th>Fecha Contratación</th>
                            <th>Fecha Finalización</th>
                            <th>Horario</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach var="ent" items="${entrenadores}">
                            <tr>
                                <td>${ent.getId()}</td> 
                                <td>${ent.getNombre()}</td>
                                <td>${ent.getApellido()}</td>
                                <td>${ent.getTipo_doc()}</td>
                                <td>${ent.getNum_doc()}</td>
                                <td>${ent.getTelefono()}</td>
                                <td>${ent.getCorreo()}</td>
                                <td>${ent.getFecha()}</td>
                                <td>${ent.getFechaTermino()}</td>
                                <td>${ent.getHorario()}</td>
                                <td>
                                    <a class="btn btn-warning" href="Controlador?menu=Entrenador&accion=Editar&id=${ent.getId()}">Editar</a>
                                    <a class="btn btn-danger" href="Controlador?menu=Entrenador&accion=Eliminar&id=${ent.getId()}">Delete</a>
                                </td>                       
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
            </div>
        </div><!-- Final 1er div -->

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
