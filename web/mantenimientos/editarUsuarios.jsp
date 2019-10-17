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
            <input type=HIDDEN name=accion value="GRABAR_USUARIO">
            <input type=hidden name=operacion value='<c:out value='${operacion}'/>'>
            <input type=hidden name=xcod value="<c:out value='${usuario.cod_usuario}'/>">
            <table class="table table-striped table-hover">
            <tbody>
            <tr><th>Codigo: </th>
            <td><c:out value='${usuario.cod_usuario}'/> </td> </tr>
            <tr><th>Nickname: </th>
            <td><input name=xnic value="<c:out value='${usuario.nickname_usuario}'/>"
                size=60></td></tr>
            <tr><th>Nombre:</th>
            <td><input name=xnom value="<c:out value='${usuario.nombre_usuario}'/>"
                size=60></td></tr>
            <tr><th>Clave:</th>
            <td><input name=xcla value="<c:out value='${usuario.clave_usuario}'/>"
                size=60></td></tr>
            <tr><th>Tipo:</th>
            <td><c:choose>
                  <c:when test="${usuario.tipo_usuario=='comprador'}">
                      <input type=radio name=xtip value=admin>Admin
                      <input type=radio name=xtip value=comprador checked>Comprador
                  </c:when>
                  <c:otherwise>
                      <input type=radio name=xtip value=admin checked>Admin
                      <input type=radio name=xtip value=comprador>Comprador
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
