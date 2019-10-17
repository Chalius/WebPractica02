/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.controlador;

import com.empresa.dao.ComprasDAOImpl;
import com.empresa.dao.IComprasDAO;
import com.empresa.dao.IProductosDAO;
import com.empresa.modelo.cBaseDatos;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.empresa.dao.IUsuariosDAO;
import com.empresa.dao.ProductosDAOImpl;
import com.empresa.dao.UsuariosDAOImpl;
import com.empresa.modelo.Usuarios;

import com.empresa.modelo.Productos;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gonzalo
 */
@WebServlet(name = "controladorPrincipal", urlPatterns = {"/controladorPrincipal"})
public class controladorPrincipal extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String tipo_usuario = "";
    String xnom = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        try {

            cBaseDatos objDatos = new cBaseDatos();
            String accion = request.getParameter("accion");

            if (accion == null) {
                accion = "login";
            }

            //if (accion.equals("bienvenida")){
            //request.getRequestDispatcher( "/entorno.jsp" ).forward(request,response);
            //}else 
            // LOGIN 
            if (accion.compareTo("login") == 0) {
                //CÓDIGO DE SERVLETVERIFICA DE LAB 4
                xnom = request.getParameter("xnom");
                String xcla = request.getParameter("xcla");
                cBaseDatos objDB = new cBaseDatos();
                if (objDB.validarUsuario(xnom, xcla)) {
                    HttpSession misession = request.getSession(true);
                    misession.setAttribute("usuario", xnom.toUpperCase());
                    //response.sendRedirect("/WebApp04/areas.jsp");

                    // verificando rango de usuario:
                    IUsuariosDAO usuarioDAO = new UsuariosDAOImpl();
                    List<Usuarios> listaUsuarios = new ArrayList<Usuarios>();

                    listaUsuarios = usuarioDAO.obtener();

                    tipo_usuario = objDB.averiguarTipoUsuario(xnom, xcla);

                    // en caso de que el usuario se admin:
                    if (tipo_usuario.compareTo("admin") == 0) {
                        request.setAttribute("tipoUsuario", "admin");

                        // Por alguna razón la siguinete linea bota error
                        //request.getRequestDispatcher("/WebPractica02/entorno.html").forward(request,response); 
                        request.getRequestDispatcher("/entorno.html").forward(request, response);

                    } // en caso el usuario sea comprador
                    else if (tipo_usuario.compareTo("comprador") == 0) {
                        request.setAttribute("tipoUsuario", "comprador");
                        // Por alguna razón la siguinete linea bota error
                        //request.getRequestDispatcher("/WebPractica02/entorno.html").forward(request,response);
                        request.getRequestDispatcher("/entorno.html").forward(request, response);
                    }

                } else {
                    //response.sendRedirect("/WebApp04/index.html");
                    response.sendRedirect("/WebPractica02/index.html");
                }
            } // FIN LOGIN
            
            //
            //____________________________________________________
            // USUARIOS                                          |
            else if (accion.compareTo("listadoUsuarios") == 0) { // listado de usuarios
                List<Usuarios> listaUsuarios = new ArrayList<Usuarios>();

                IUsuariosDAO pdo = new UsuariosDAOImpl();

                listaUsuarios = pdo.obtener();
                request.setAttribute("arrUsuarios", listaUsuarios);
                request.setAttribute("tipoUsuario", tipo_usuario);
                if (tipo_usuario.compareTo("admin") == 0) {
                } else if (tipo_usuario.compareTo("comprador") == 0) {
                    request.setAttribute("xnom", xnom);
                }

                request.getRequestDispatcher("/mantenimientos/listadoUsuarios.jsp").forward(request, response);

            } //FIN LISTADO USUARIOS
            //EDITAR USUARIOS
            else if (accion.compareTo("modificarUsuario") == 0) {
                String xcod = request.getParameter("xcod").trim();
                IUsuariosDAO dao = new UsuariosDAOImpl();
                Usuarios usuario = dao.buscar(Integer.parseInt(xcod));
                request.setAttribute("usuario", usuario);
                request.setAttribute("operacion", "UPDATE");
                request.setAttribute("titulo", "Modificar Usuario");
                request.getRequestDispatcher("/mantenimientos/editarUsuarios.jsp").forward(request, response);
            } // grabar usuario desde la ventana de edición
            else if (accion.compareTo("GRABAR_USUARIO") == 0) {
                if (request.getParameter("boton").compareTo("GRABAR") == 0) {

                    String operacion = request.getParameter("operacion");

                    Usuarios alumno = new Usuarios();
                    alumno.setCod_usuario(Integer.parseInt(request.getParameter("xcod")));
                    alumno.setNickname_usuario(request.getParameter("xnic"));
                    alumno.setNombre_usuario(request.getParameter("xnom"));
                    alumno.setClave_usuario(request.getParameter("xcla"));
                    alumno.setTipo_usuario(request.getParameter("xtip"));

                    if (operacion.equals("INSERT")) {
                        IUsuariosDAO dao = new UsuariosDAOImpl();
                        dao.registrar(alumno);
                    } else {
                        IUsuariosDAO dao = new UsuariosDAOImpl();
                        dao.actualizar(alumno);
                    }
                }

                request.getRequestDispatcher("/controladorPrincipal?accion=listadoUsuarios").forward(request, response);
            } // IR A VENTANA AÑADIR USUARIOS
            else if (accion.equals("NuevoEliminarUsuario")) {

                if (request.getParameter("boton").compareTo("Nuevo Registro") == 0) {
                    // Aquí codigo para ir a la interfaz de nuevo registro
                    Usuarios usuario = new Usuarios();
                    request.setAttribute("usuario", usuario);
                    request.setAttribute("operacion", "INSERT");
                    request.setAttribute("titulo", "Nuevo Usuario");
                    request.getRequestDispatcher("/mantenimientos/editarUsuarios.jsp").forward(request, response);

                } else if (request.getParameter("boton").compareTo("Eliminar Registros") == 0) {
                    // aquí código para eliminar
                    String[] datos = request.getParameterValues("xcod");
                    IUsuariosDAO dao = new UsuariosDAOImpl();
                    dao.eliminar(datos);
                    request.getRequestDispatcher("/controladorPrincipal?accion=listadoUsuarios").forward(
                       request,response );

                    
                }
            } 
            //
            //____________________________________________________
            // PRODUCTOS                                         |
            else if (accion.compareTo("listadoProductos") == 0) { // listado productos

                List<Productos> listaProductos = new ArrayList<Productos>();

                IProductosDAO pdo = new ProductosDAOImpl();

                listaProductos = pdo.obtener();
                request.setAttribute("arrUsuarios", listaProductos);
                request.setAttribute("tipoUsuario", tipo_usuario);

                request.getRequestDispatcher("/mantenimientos/listadoProductos.jsp").forward(request, response);

            } else if (accion.equals("NuevoEliminarProducto")) {  // IR A VENTANA AÑADIR PRODUCTOS

                if (request.getParameter("boton").compareTo("Nuevo Registro") == 0) {
                    // Aquí codigo para ir a la interfaz de nuevo registro
                    Productos producto = new Productos();
                    request.setAttribute("producto", producto);
                    request.setAttribute("operacion", "INSERT");
                    request.setAttribute("titulo", "Nuevo Producto");
                    request.getRequestDispatcher("/mantenimientos/editarProductos.jsp").forward(request, response);

                } else if (request.getParameter("boton").compareTo("Eliminar Registros") == 0) {
                    // aquí código para eliminar
                    String[] datos = request.getParameterValues("xcod");
                    IProductosDAO dao = new ProductosDAOImpl();
                    dao.eliminar(datos);
                    request.getRequestDispatcher("/controladorPrincipal?accion=listadoProductos").forward(
                       request,response );

                    
                    
                }
            } 
           
            else if (accion.compareTo("modificarProducto") == 0) { //EDITAR USUARIOS
                String xcod = request.getParameter("xcod").trim();
                IProductosDAO dao = new ProductosDAOImpl();
                Productos producto = dao.buscar(Integer.parseInt(xcod));
                request.setAttribute("producto", producto);
                request.setAttribute("operacion", "UPDATE");
                request.setAttribute("titulo", "Modificar producto");
                request.getRequestDispatcher("/mantenimientos/editarProductos.jsp").forward(request, response);
            }            
            
            
            
            
            else if (accion.compareTo("GRABAR_PRODUCTO") == 0) {  // grabar producto desde la ventana de edición
                if (request.getParameter("boton").compareTo("GRABAR") == 0) {

                    String operacion = request.getParameter("operacion");

                    Productos producto = new Productos();
                    producto.setCod_producto(Integer.parseInt(request.getParameter("xcod")));
                    producto.setNombre_producto(request.getParameter("xnom"));
                    producto.setPrecio_producto(request.getParameter("xpre"));
                    producto.setStock_producto(request.getParameter("xsto"));
                    producto.setEstado_producto(request.getParameter("xest"));

                    if (operacion.equals("INSERT")) {
                        IProductosDAO dao = new ProductosDAOImpl();
                        dao.registrar(producto);
                    } else {
                        IProductosDAO dao = new ProductosDAOImpl();
                        dao.actualizar(producto);
                    }
                }

                request.getRequestDispatcher("/controladorPrincipal?accion=listadoProductos").forward(request, response);

            }

            // FIN PRODUCTOS                                      |
            //_____________________________________________________

            //_____________________________________________________
            // HACER COMPRAS                                      |
            else if(accion.compareTo("compraMostrarUsuarios")==0){
                if(request.getParameter("modo").compareTo("Lista")==0){
                    List<Usuarios> arrUsuarios = new ArrayList<Usuarios>();
                    String strUsuario= " ";
                    IComprasDAO dao = new ComprasDAOImpl();
                    arrUsuarios = dao.buscarUsuarios(strUsuario);
                    request.setAttribute("arrUsuarios",arrUsuarios);
                    request.getRequestDispatcher("/mantenimientos/compraMostrarUsuarios.jsp").forward(request, response);
                }else if(request.getParameter("boton").compareTo("Buscar")==0){
                    List<Usuarios> arrUsuarios = new ArrayList<Usuarios>();
                    String strUsuario = request.getParameter("xusu");
                    IComprasDAO dao = new ComprasDAOImpl();
                    arrUsuarios = dao.buscarUsuarios(strUsuario);
                    request.setAttribute("arrUsuarios",arrUsuarios);
                    request.setAttribute("nombre_usuario",strUsuario);
                    request.getRequestDispatcher("/mantenimientos/compraMostrarUsuarios.jsp").forward(request, response);
                }
            }
            
                
                
            
            // TODO añadir diferentes urls para otras paginas
            
               else if(accion.compareTo("cerrarSesion")==0){
                cerrarSesion(request, response);
               
                
                
                    
            } 
            
            
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            out.close();
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>



    
      private void cerrarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException{
         HttpSession misession= (HttpSession) request.getSession();
            misession.removeAttribute("usuario");
            misession.invalidate();
            response.sendRedirect("/WebPractica02/index.html");
    }




}
