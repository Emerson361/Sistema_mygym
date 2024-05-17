<%-- 
    Document   : Dieta
    Created on : 16/05/2024, 07:48:03 PM
    Author     : Usuario
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <title>Dietas</title>
        <!-- Agrega aquí tus enlaces a archivos CSS si los necesitas -->
    </head>
<body>
   
    <h1>Dieta</h1>    
    <div class="d-flex"> 
            <div class="card col-sm-3">
                <div class="card-body">
                    <form action="Controlador?menu=Dieta" method="POST">
                        <div class="form-group">
                            <label>Nombre:</label>
                            <input type="text" value="${dieta.getNombre()}" name="txtNombre" class="form-control" required>
                        </div>

                        <div class="form-group">
                            <label>Tipo Dieta:</label>
                                <select class="form-control" value="${dieta.getTipoDieta()}" name="txtTipoDieta" required>
                                <option ${dieta.getTipoDieta().equals("Normal") ? 'selected' : ''}>Normal</option>
                                <option ${dieta.getTipoDieta().equals("Media") ? 'selected' : ''}>Media</option>
                                <option ${dieta.getTipoDieta().equals("Extrema") ? 'selected' : ''}>Extrema</option>
                            </select> 
                        </div>

                        <div class="form-group">
                            <label>Duracion:</label>
                            <select class="form-control" value="${dieta.getDuracion()}" name="txtDuracion" required>
                                <option ${dieta.getDuracion().equals("Mensual") ? 'selected' : ''}>1 mes</option>
                                <option ${dieta.getDuracion().equals("Bimestral") ? 'selected' : ''}>2 meses</option>
                                <option ${dieta.getDuracion().equals("Trimestral") ? 'selected' : ''}>3 meses</option>
                            </select>                          
                        </div>

                        <div class="form-group">
                            <label>Suplemento:</label>
                            <select class="form-control" value="${dieta.getSuplemento()}" name="txtSuplemento" required>
                                <option ${dieta.getSuplemento().equals("Vitaminas") ? 'selected' : ''}>Vitaminas</option>
                                <option ${dieta.getSuplemento().equals("Aminoácidos") ? 'selected' : ''}>Aminoacidos</option>
                                <option ${dieta.getSuplemento().equals("Lipidos") ? 'selected' : ''}>Lipidos</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label>Nutricionista:</label>
                            <select class="form-control" name="txtNutricionista" required>
                                <c:forEach var="nut" items="${nutricionistas}">
                                   <option  value="${nut.getId()}">${nut.getNombre()} ${nut.getApellido()}</option>
                                </c:forEach>
                            </select><br><br>
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
                            <th>Tipo Dieta</th>
                            <th>Duracion</th>
                            <th>Suplemento</th>
                            <th>Nutricionista</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach var="die" items="${dietas}">
                            <tr>
                                <td>${die.getIdDieta()}</td> 
                                <td>${die.getNombre()}</td>
                                <td>${die.getTipoDieta()}</td>
                                <td>${die.getDuracion()}</td>
                                <td>${die.getSuplemento()}</td>
                                <td>${die.getNutricionista()}</td>
                                <td>
                                    <a class="btn btn-warning" href="Controlador?menu=Dieta&accion=Editar&id=${die.getIdDieta()}">Editar</a>
                                    <a class="btn btn-danger" href="Controlador?menu=Dieta&accion=Eliminar&id=${die.getIdDieta()}">Delete</a>
                                </td>                       
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
            </div>
        </div>
          
    
</body>
</html>
