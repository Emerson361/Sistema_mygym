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
        <title>Rutina</title>        
    </head>
    <body>

        <div class="d-flex"> 
            <div class="card col-sm-3">
                <div class="card-body">
                    <form action="Controlador?menu=Rutina" method="POST">
                        <div class="form-group">
                            <label>Nombre:</label>
                            <input type="text" value="${entrenador.getNombre()}" name="txtNombre" class="form-control">
                        </div>

                        <div class="form-group">
                            <label>Tipo Rutina:</label>
                            <select class="form-control" value="${entrenador.getTipo_doc()}" name="txttiporutina">
                                <option>Fuerza</option>
                                <option>Cardiovascular</option>
                                <option>Flexibilidad</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label>Descripción:</label>
                            <textarea class="form-control" value="${entrenador.getTipo_doc()}" name="txtdescripcion"></textarea>                         
                        </div>

                        <div class="form-group">
                            <label>Dificultad:</label>
                            <select class="form-control" value="${entrenador.getTipo_doc()}" name="txtdificultad">
                                <option>Principante</option>
                                <option>Intermedio</option>
                                <option>Avanzado</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label>Frecuencia:</label>
                            <select class="form-control" value="${entrenador.getTipo_doc()}" name="txtfrecuencia">
                                <option>1 vez a la semana</option>
                                <option>2 veces a la semana</option>
                                <option>3 veces a la semana</option>
                            </select>
                        </div>
                         
                        <div class="form-group">
                            <label>Entrenador:</label>
                            <select class="form-control" value="${entrenador.getTipo_doc()}" name="txtentrenador">
                                <option>Juan</option>
                                <option>Pedro</option>
                                <option>Carlos</option>
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
                            <th>Tipo Rutina</th>
                            <th>Descripción</th>
                            <th>Dificultad</th>
                            <th>Frecuencia</th>
                            <th>Entrenador</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach var="rut" items="${rutinas}">
                            <tr>
                                <td>${rut.getId()}</td> 
                                <td>${rut.getNombre()}</td>
                                <td>${rut.getTiporutina()}</td>
                                <td>${rut.getDescripcion()}</td>
                                <td>${rut.getDificultad()}</td>
                                <td>${rut.getFrecuencia()}</td>
                                <td></td>
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

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
