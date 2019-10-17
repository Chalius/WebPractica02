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
    <div class="card" style="width: 50rem; padding: 30px 0px 0px 30px;">
        <h4 class="display-8">Seleccionar Usuario</h4>
        <form method=POST action="/WebPractica02/controladorPrincipal">
            <input type=HIDDEN name=accion value="productoMostrarSubtotal">
            <h4 class="display-8">Datos del Usuario</h4>
            <table class="table table-striped table-hover">
                <tr>
                    <th>Codigo</th>
                    <td><input name="xcodUsuario" size="10" 
                          value="<c:out value='${usuario.cod_usuario}'/>" readonly></td>
                    <th>Nombre</th>
                    <td><input name="xnomUsuario" size="50" 
                          value="<c:out value='${usuario.nombre_usuario}'/>" readonly></td>
                </tr>
            </table>
            <h4 class="display-8">Seleccione los productos</h4>
            <table class="table table-striped table-hover">
                <tr><td>
                Productos disponibles:
                </td></tr>
                <tr><td>
                <select name="xcodProducto" size="10" class="form-control" id="exampleFormControlSelect2" multiple>
                    <c:forEach items="${arrProducto}" var="producto">
                    <option value=<c:out value='${producto.cod_producto}'/> >
                        <c:out value='${producto.nombre_producto}'/>
                    </option>
                    </c:forEach>
                </select>
                </td></tr>
            <tr><td>
                <input type=submit name=boton class="btn btn-primary" value="Aceptar">
            </td></tr>
        </form>

    </div>
    </body>
</html>
