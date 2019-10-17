/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author gonzalo
 */
public class cBaseDatos {
     String driver  = "com.mysql.cj.jdbc.Driver";
    String url     = "jdbc:mysql://localhost/test?useSSL=false&serverTimezone=UTC";
    
    //CREDENCIALES PARA PC CASA:
    //String usuario = "root";
    //String clave   = "tecsup";
    //CREDENCIALES PARA LAPTOP:
    String usuario = "gonzalo";
    String clave   = "123";

    private Connection Conectar() {
        try {
            Class.forName(driver);
            Connection xcon = DriverManager.getConnection(url,usuario,clave);
            return xcon;
        }
        catch(Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }
    
    
        /*
    Función que autogenera código para una tabla cualquiera de la base de datos:
    */
    public String generarCodigo(String tabla, String campo) throws SQLException{
        String rpta = "";
        String sql = "select count(*) from " + tabla;
        Connection xcon = this.Conectar();
        Statement st = xcon.createStatement();
        ResultSet rs = st.executeQuery(sql);
        rs.next();
        int cant = Integer.parseInt(rs.getString(1).toString());
        if ( cant <= 0 )
            rpta = "1";
        else {
            sql = "select max(" + campo + ") from " + tabla;
            rs = st.executeQuery(sql);
            rs.next();
            cant = Integer.parseInt(rs.getString(1).toString()) + 1;
            rpta = "" + cant;
        }
        xcon.close();
        return rpta;
    }
    
    
     public boolean validarUsuario(String xnom, String xcla) {
        try {
            System.out.println("Entro a validarUsuario");
            Connection xcon = this.Conectar();
            String sql = "select count(*) from p_usuarios where nickname_usuario=? AND clave_usuario=?";
            PreparedStatement ps=xcon.prepareStatement(sql);
            ps.setString(1, xnom );
            ps.setString(2, xcla );
            ResultSet rs = ps.executeQuery();
            rs.next();
            String cantidad = rs.getString(1);
            int xcant = Integer.parseInt( cantidad );
            
            if ( xcant > 0 ){
                System.out.println("Salio de validarUsuario");
                return true;
            }
            xcon.close();
            
            return false;
            
            
        } catch (Exception ex ) {
            System.out.println(ex.toString());
        }
        return false;
    }
     
    public String averiguarTipoUsuario(String xnom, String xcla) {
        try {
            System.out.println("Entro a averiguar tipousuario");
            Connection xcon = this.Conectar();
            String sql = "select * from p_usuarios where nickname_usuario=? AND clave_usuario=?";
            PreparedStatement ps=xcon.prepareStatement(sql);
            ps.setString(1, xnom );
            ps.setString(2, xcla );
            ResultSet rs = ps.executeQuery();
            rs.next();
                      

            String tipo_usuario = rs.getString(5);
            System.out.println(tipo_usuario);         
            xcon.close();
            return tipo_usuario;
            
            
        } catch (Exception ex ) {
            System.out.println(ex.toString());
        }
        return "error con averiguar usuario";
    }
    
    
}







