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
            <h4 class="display-8">Listado de Productos</h4>


            <form method=POST action="/WebPractica02/controladorPrincipal">

                <c:if test = "${tipoUsuario == 'admin'}">
                    <input type=HIDDEN name=accion value="NuevoEliminarProducto">
                    <input type=submit name=boton class="btn btn-primary" value="Nuevo Registro">
                    <input type=submit name=boton class="btn btn-dark" value="Eliminar Registros">

                </c:if>


                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Codigo</th>
                            <th scope="col">Nombre</th>
                            <th scope="col">Precio</th>
                            <th scope="col">Stock</th>
                            <th scope="col">Estado</th>
                        </tr>
                    </thead>
                    <tbody>


                
                            <c:forEach items="${arrUsuarios}" var="producto">
                                <tr>
                                    <td><input type=checkbox name='xcod'
                                               value=<c:out value='${producto.cod_producto}'/> > </td>
                                    <td><c:out value='${producto.cod_producto}'/></td>
                                    <td><a href="/WebPractica02/controladorPrincipal?accion=modificarProducto&xcod=
                                           <c:out value='${producto.cod_producto}'/>">
                                            <c:out value='${producto.nombre_producto}' /></a></td>
                                    <td><c:out value='${producto.precio_producto}'/></td>
                                    <td><c:out value='${producto.stock_producto}'/></td>
                                    <td><c:out value='${producto.estado_producto}'/></td>
                                </tr>
                            </c:forEach>


                        
                    </tbody>
                </table>
            </form>
        </div>





    </body>
