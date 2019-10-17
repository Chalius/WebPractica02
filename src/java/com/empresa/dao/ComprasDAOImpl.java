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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gonzalo
 */
public class ComprasDAOImpl implements IComprasDAO{

    @Override
    public List<Usuarios> buscarUsuarios(String nombre) {
        
        
        Connection co =null;
        Statement stm= null;
        ResultSet rs=null;
        String sql="SELECT * FROM p_usuarios WHERE nombre_usuario LIKE'%" + nombre + "%'";
        List<Usuarios> listaUsuarios= new ArrayList<Usuarios>();

        try {            
                Conexion con = new Conexion();
                co=con.Conectar();
                stm=co.createStatement();
                rs=stm.executeQuery(sql);
                while (rs.next()) {
                        Usuarios alumno=new Usuarios();
                        alumno.setCod_usuario(rs.getInt(1));
                        alumno.setNickname_usuario(rs.getString(2));
                        alumno.setNombre_usuario(rs.getString(3));
                        alumno.setClave_usuario(rs.getString(4));
                        alumno.setTipo_usuario(rs.getString(5));
                        listaUsuarios.add(alumno);
                }
                stm.close();
                rs.close();
                co.close();
        } catch (SQLException e) {
                System.out.println("Error:Clase MatriculaDaoImpl,"
                        + "método buscarAlumnos");
        }
        return listaUsuarios;
    }

    @Override
    public List<Productos> buscarProductos() {
        
        
            Connection co =null;
        Statement stm= null;
        ResultSet rs=null;
        String sql="SELECT * FROM p_productos ORDER BY cod_producto";
        List<Productos> listaProductos= new ArrayList<Productos>();

        try {            
                Conexion con = new Conexion();
                co=con.Conectar();
                stm=co.createStatement();
                rs=stm.executeQuery(sql);
                while (rs.next()) {
                        Productos curso=new Productos();
                        curso.setCod_producto(rs.getInt(1));
                        curso.setPrecio_producto(rs.getString(2));
                        curso.setStock_producto(rs.getString(3));
                        curso.setEstado_producto(rs.getString(4));
                    
                        listaProductos.add(curso);
                }
                stm.close();
                rs.close();
                co.close();
        } catch (SQLException e) {
                System.out.println("Error:Clase MatriculaDaoImpl,"
                        + "método obtener");
        }
        return listaProductos;

        
    

    
    }
    
}
