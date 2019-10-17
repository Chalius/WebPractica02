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
    <div class="card" style="width: 50rem;padding: 30px 0px 0px 30px;">
        <h4 class="display-8"><c:out value='${titulo}'/></h4>
        <form name=mod_opcion method=POST action="/WebPractica02/controladorPrincipal">
            <input type=HIDDEN name=accion value="GRABAR_PRODUCTO">
            <input type=hidden name=operacion value='<c:out value='${operacion}'/>'>
            <input type=hidden name=xcod value="<c:out value='${producto.cod_producto}'/>">
            <table class="table table-striped table-hover">
            <tbody>
            <tr><th>Codigo: </th>
            <td><c:out value='${producto.cod_producto}'/> </td> </tr>
            <tr><th>Nombre: </th>
            <td><input name=xnom value="<c:out value='${producto.nombre_producto}'/>"
                size=60></td></tr>
            <tr><th>Precio:</th>
            <td><input name=xpre value="<c:out value='${producto.precio_producto}'/>"
                size=60></td></tr>
            <tr><th>Stock:</th>
            <td><input name=xsto value="<c:out value='${producto.stock_producto}'/>"
                size=60></td></tr>
            <tr><th>Estado:</th>
            <td><c:choose>
                  <c:when test="${producto.estado_producto=='X'}">
                      <input type=radio name=xest value=A>Activo
                      <input type=radio name=xest value=X checked>Inactivo
                  </c:when>
                  <c:otherwise>
                      <input type=radio name=xest value=A checked>Activo
                      <input type=radio name=xest value=X>Inactivo
                  </c:otherwise>
                </c:choose>
            </td></tr>
            </tbody>
            </table>
            <input type=submit name=boton class="btn btn-primary" value="GRABAR">
            <input type=submit name=boton class="btn btn-dark" value="CANCELAR">
        </form>
    </div>
    </body>
</html>
