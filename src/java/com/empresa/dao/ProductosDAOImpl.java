/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.dao;

import com.empresa.modelo.Conexion;
import com.empresa.modelo.Productos;
import com.empresa.modelo.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gonzalo
 */
public class ProductosDAOImpl implements IProductosDAO {

    @Override
    public boolean registrar(Productos producto) {

        Conexion co = new Conexion();
        String xcod = co.generarCodigo("p_productos", "cod_producto");
        boolean registrar = false;
        Statement stm = null;
        Connection con = null;
        String sql = "INSERT INTO p_productos values (" + xcod + ","
                + "'" + producto.getNombre_producto() + "','" + producto.getPrecio_producto() + "',"
                + "'" + producto.getStock_producto() + "','" + producto.getEstado_producto() + "')";
        try {
            con = co.Conectar();
            stm = con.createStatement();

            stm.execute(sql);
            registrar = true;
            stm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error:Clase ProductoDaoImpl," + "metodo registrar");
            e.printStackTrace();
        }
        return registrar;

    }

    @Override
    public List<Productos> obtener() {

        Connection co = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM p_productos ORDER BY cod_producto";
        List<Productos> listaProductos = new ArrayList<Productos>();

        try {
            Conexion con = new Conexion();
            co = con.Conectar();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Productos producto = new Productos();
                producto.setCod_producto(rs.getInt(1));
                producto.setNombre_producto(rs.getString(2));
                producto.setPrecio_producto(rs.getString(3));
                producto.setStock_producto(rs.getString(4));
                producto.setEstado_producto(rs.getString(5));

                listaProductos.add(producto);
            }
            stm.close();
            rs.close();
            co.close();

        } catch (SQLException e) {
            System.out.println("Error:Clase ProductoDAOImpl, método obtener");
        }
        return listaProductos;

    }

    @Override
    public boolean actualizar(Productos producto) {

        Conexion co = new Conexion();
        boolean actualizar = false;
        Statement stm = null;
        Connection con = null;
        String sql = "UPDATE p_productos SET "
                + "nombre_producto='" + producto.getNombre_producto() + "',precio_producto='" + producto.getPrecio_producto() + "',"
                + "stock_producto='" + producto.getStock_producto() + "',estado_producto='" + producto.getEstado_producto() + "'"
                + " WHERE cod_producto=" + producto.getCod_producto();
        try {
            con = co.Conectar();
            stm = con.createStatement();
            stm.execute(sql);
            actualizar = true;
            stm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase ProductoDAOImpl, " + "metodo actualizar");
            e.printStackTrace();
        }
        return actualizar;

    }

    @Override
    public boolean eliminar(String[] datos) {

  Conexion co=new Conexion();
        boolean borrar=false;
        Statement stm=null;
        Connection con = null;
        String sql = "DELETE FROM p_productos WHERE cod_producto in ( ";
        borrar=true;
        for (int xc=0; xc< datos.length; xc++){
            if (borrar){
                sql += "?";
            }else{
                sql += ",?";
            }borrar = false;
        }sql += ")";
        try{
            con =co.Conectar();
            PreparedStatement ps=con.prepareStatement(sql);
            for (int xc=0; xc < datos.length; xc++)
                ps.setString(xc + 1,datos[xc]);
            ps.execute();
            borrar = true;
            ps.close();
            con.close();
        }catch (Exception e){
            System.out.println("Error: Clase ProductoDAOImpl, "+"metodo eliminar");
            e.printStackTrace();
        }
        return borrar;



    }

    @Override
    public Productos buscar(int cod_producto) {

        Connection co = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM p_productos WHERE cod_producto=" + cod_producto;
        Productos producto = new Productos();
        try {
            Conexion con = new Conexion();
            co = con.Conectar();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                producto.setCod_producto(rs.getInt(1));
                producto.setNombre_producto(rs.getString(2));
                producto.setPrecio_producto(rs.getString(3));
                producto.setStock_producto(rs.getString(4));
                producto.setEstado_producto(rs.getString(5));

            }
            stm.close();
            rs.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error:Clase ProductoDaoImpl," + "metodo buscar");
            e.printStackTrace();
        }
        return producto;

    }

    @Override
    public Productos buscarPorNombre(String nombre) {

        Connection co = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM p_productos WHERE nombre_producto=" + nombre;
        Productos producto = new Productos();
        try {
            Conexion con = new Conexion();
            co = con.Conectar();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                producto.setCod_producto(rs.getInt(1));
                producto.setNombre_producto(rs.getString(2));
                producto.setPrecio_producto(rs.getString(3));
                producto.setStock_producto(rs.getString(4));
                producto.setEstado_producto(rs.getString(5));
            }
            stm.close();
            rs.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error:Clase ProductoDaoImpl," + "metodo buscar por nombre");
            e.printStackTrace();
        }
        return producto;

    }

    @Override
    public List<Productos> buscarProductos(String nombre) {
        Connection co =null;
        Statement stm= null;
        ResultSet rs=null;
        String sql="SELECT * FROM p_productos WHERE nombre_producto LIKE'%" + nombre + "%'";
        List<Productos> listaProductos= new ArrayList<Productos>();

        try {            
                Conexion con = new Conexion();
                co=con.Conectar();
                stm=co.createStatement();
                rs=stm.executeQuery(sql);
                while (rs.next()) {
                        Productos alumno=new Productos();
                        alumno.setCod_producto(rs.getInt(1));
                        alumno.setPrecio_producto(rs.getString(2));
                        alumno.setStock_producto(rs.getString(3));
                        alumno.setEstado_producto(rs.getString(4));
                     
                        listaProductos.add(alumno);
                }
                stm.close();
                rs.close();
                co.close();
        } catch (SQLException e) {
                System.out.println("Error:Clase ProductosDaoImpl,"
                        + "método buscarProductos");
        }
        return listaProductos;
    }
    


    

}
