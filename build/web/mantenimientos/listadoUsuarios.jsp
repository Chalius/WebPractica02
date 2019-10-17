<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" 
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
              crossorigin="anonymous">
    </head>
    <body>
        
        
        <div class="card" style="padding: 30px 0px 0px 30px;">
            <h4 class="display-8">Listado de Usuarios</h4>


            <form method=POST action="/WebPractica02/controladorPrincipal">

                <c:if test = "${tipoUsuario == 'admin'}">
                    <input type=HIDDEN name=accion value="NuevoEliminarUsuario">
                    <input type=submit name=boton class="btn btn-primary" value="Nuevo Registro">
                    <input type=submit name=boton class="btn btn-dark" value="Eliminar Registros">

                </c:if>


                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Codigo</th>
                            <th scope="col">Nickname</th>
                            <th scope="col">Nombre</th>
                            <th scope="col">Clave</th>
                            <th scope="col">Tipo</th>
                        </tr>
                    </thead>
                    <tbody>


                        <c:if test = "${tipoUsuario == 'admin'}">  
                            <c:forEach items="${arrUsuarios}" var="usuario">
                                <tr>
                                    <td><input type=checkbox name='xcod'
                                               value=<c:out value='${usuario.cod_usuario}'/> > </td>
                                    <td><c:out value='${usuario.cod_usuario}'/></td>
                                    <td><a href="/WebPractica02/controladorPrincipal?accion=modificarUsuario&xcod=
                                           <c:out value='${usuario.cod_usuario}'/>">
                                            <c:out value='${usuario.nickname_usuario}' /></a></td>
                                    <td><c:out value='${usuario.nombre_usuario}'/></td>
                                    <td><c:out value='${usuario.clave_usuario}'/></td>
                                    <td><c:out value='${usuario.tipo_usuario}'/></td>
                                </tr>
                            </c:forEach>

                        </c:if>

                        <c:if test = "${tipoUsuario == 'comprador'}">  
                            <c:forEach items="${arrUsuarios}" var="usuario">
                                <c:if test = "${usuario.nombre_usuario == xnom}">  
                                    <tr>
                                        <td><input type=checkbox name='xcod'
                                                   value=<c:out value='${usuario.cod_usuario}'/> > </td>
                                        <td><c:out value='${usuario.cod_usuario}'/></td>
                                        <td><a href="/WebPractica02/controladorPrincipal?accion=modificarUsuario&xcod=
                                               <c:out value='${usuario.cod_usuario}'/>">
                                                <c:out value='${usuario.nickname_usuario}' /></a></td>
                                        <td><c:out value='${usuario.nombre_usuario}'/></td>
                                        <td><c:out value='${usuario.clave_usuario}'/></td>
                                        <td><c:out value='${usuario.tipo_usuario}'/></td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </tbody>
                </table>
            </form>
        </div>





    </body>
