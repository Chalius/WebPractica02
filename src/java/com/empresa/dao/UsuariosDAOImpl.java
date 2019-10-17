/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.dao;

import com.empresa.modelo.Usuarios;

import com.empresa.modelo.Conexion;
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
public class UsuariosDAOImpl implements IUsuariosDAO {

    @Override
    public boolean registrar(Usuarios usuario) {
        Conexion co = new Conexion();
        String xcod = co.generarCodigo("p_usuarios", "cod_usuario");
        boolean registrar = false;
        Statement stm = null;
        Connection con = null;
        String sql = "INSERT INTO p_usuarios values (" + xcod + ","
                + "'" + usuario.getNickname_usuario() + "','" + usuario.getNombre_usuario() + "',"
                + "'" + usuario.getClave_usuario() + "','" + usuario.getTipo_usuario() + "')";
        try {
            con = co.Conectar();
            stm = con.createStatement();

            stm.execute(sql);
            registrar = true;
            stm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error:Clase UsuarioDaoImpl," + "metodo registrar");
            e.printStackTrace();
        }
        return registrar;
    }

    @Override
    public List<Usuarios> obtener() {

        Connection co = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM p_usuarios ORDER BY cod_usuario";
        List<Usuarios> listaUsuarios = new ArrayList<Usuarios>();

        try {
            Conexion con = new Conexion();
            co = con.Conectar();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Usuarios usuario = new Usuarios();
                usuario.setCod_usuario(rs.getInt(1));
                usuario.setNickname_usuario(rs.getString(2));
                usuario.setNombre_usuario(rs.getString(3));
                usuario.setClave_usuario(rs.getString(4));
                usuario.setTipo_usuario(rs.getString(5));

                listaUsuarios.add(usuario);
            }
            stm.close();
            rs.close();
            co.close();

        } catch (SQLException e) {
            System.out.println("Error:Clase AlumnoDAOImpl, método obtener");
        }
        return listaUsuarios;

    }

    @Override
    public boolean actualizar(Usuarios usuario) {
        Conexion co = new Conexion();
        boolean actualizar = false;
        Statement stm = null;
        Connection con = null;
        String sql = "UPDATE p_usuarios SET "
                + "nickname_usuario='" + usuario.getNickname_usuario() + "',nombre_usuario='" + usuario.getNombre_usuario() + "',"
                + "clave_usuario='" + usuario.getClave_usuario() + "',tipo_usuario='" + usuario.getTipo_usuario() + "'"
                + " WHERE cod_usuario=" + usuario.getCod_usuario();
        try {
            con = co.Conectar();
            stm = con.createStatement();
            stm.execute(sql);
            actualizar = true;
            stm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase UsuarioDAOImpl, " + "metodo actualizar");
            e.printStackTrace();
        }
        return actualizar;
    }

    @Override
    public boolean eliminar(String[] datos) {
        Conexion co = new Conexion();
        boolean borrar = false;
        Statement stm = null;
        Connection con = null;
        String sql = "DELETE FROM p_usuarios WHERE cod_usuario in ( ";
        borrar = true;
        for (int xc = 0; xc < datos.length; xc++) {
            if (borrar) {
                sql += "?";
            } else {
                sql += ",?";
            }
            borrar = false;
        }
        sql += ")";
        try {
            con = co.Conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            for (int xc = 0; xc < datos.length; xc++) {
                ps.setString(xc + 1, datos[xc]);
            }
            ps.execute();
            borrar = true;
            ps.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error: Clase UsuarioDAOImpl, " + "metodo eliminar");
            e.printStackTrace();
        }
        return borrar;
    }

    @Override
    public Usuarios buscar(int cod_usuario) {

        Connection co = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM p_usuarios WHERE cod_usuario=" + cod_usuario;
        Usuarios usuario = new Usuarios();
        try {
            Conexion con = new Conexion();
            co = con.Conectar();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                usuario.setCod_usuario(rs.getInt(1));
                usuario.setNickname_usuario(rs.getString(2));
                usuario.setNombre_usuario(rs.getString(3));
                usuario.setClave_usuario(rs.getString(4));
                usuario.setTipo_usuario(rs.getString(5));
            }
            stm.close();
            rs.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error:Clase UsuarioDaoImpl," + "metodo buscar");
            e.printStackTrace();
        }
        return usuario;
    }
    
    
    
    @Override
    public Usuarios buscarPorNombre(String nombre) {

        Connection co = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM p_usuarios WHERE nombre_usuario=" + nombre;
        Usuarios usuario = new Usuarios();
        try {
            Conexion con = new Conexion();
            co = con.Conectar();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                usuario.setCod_usuario(rs.getInt(1));
                usuario.setNickname_usuario(rs.getString(2));
                usuario.setNombre_usuario(rs.getString(3));
                usuario.setClave_usuario(rs.getString(4));
                usuario.setTipo_usuario(rs.getString(5));
            }
            stm.close();
            rs.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error:Clase UsuarioDaoImpl," + "metodo buscar por nombre");
            e.printStackTrace();
        }
        return usuario;
    }
    
    

}
