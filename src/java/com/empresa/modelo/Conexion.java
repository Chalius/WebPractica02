/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author gonzalo
 */
public class Conexion {
    
    
      String driver  = "com.mysql.cj.jdbc.Driver";
    String url     = "jdbc:mysql://localhost/test?useSSL=false&serverTimezone=UTC";
    
    //CREDENCIALES PARA PC CASA:
    //String usuario = "root";
    //String clave   = "tecsup";
    //CREDENCIALES PARA LAPTOP:
    String usuario = "gonzalo";
    String clave   = "123";

    public Connection Conectar() {
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

    // Función que permite generar un código para una tabla cualquiera de la BD
    // Creo que hace que el codigo sea autoincremental y le asigna un valor dependiendo de cuantos registros haya en la tabla.
    public String generarCodigo(String tabla, String campo) {       
        String rpta = "";
        String sql = "select count(*) from " + tabla;
        Connection xcon = this.Conectar();
        try {
            Statement st;
                st = xcon.createStatement();
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
        } catch (SQLException ex) {
            System.out.println("Error al generar codigo");
            ex.printStackTrace();
        }
        return rpta;   
    }

    
    
    
    
    
}


 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
