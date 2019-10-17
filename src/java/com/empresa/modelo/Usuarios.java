/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.modelo;
import java.sql.Date;

/**
 *
 * @author gonzalo
 */
public class Usuarios {
    int cod_usuario;
    String nickname_usuario;
    String nombre_usuario;
    String clave_usuario;
    String tipo_usuario;

    public Usuarios(){
        
    }
    
    
    public Usuarios(int cod_usuario, String nickname_usuario, String nombre_usuario, String clave_usuario, String tipo_usuario) {
        this.cod_usuario = cod_usuario;
        this.nickname_usuario = nickname_usuario;
        this.nombre_usuario = nombre_usuario;
        this.clave_usuario = clave_usuario;
        this.tipo_usuario = tipo_usuario;
    }

    
    
    
    
    public int getCod_usuario() {
        return cod_usuario;
    }

    public void setCod_usuario(int cod_usuario) {
        this.cod_usuario = cod_usuario;
    }

    public String getNickname_usuario() {
        return nickname_usuario;
    }

    public void setNickname_usuario(String nickname_usuario) {
        this.nickname_usuario = nickname_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getClave_usuario() {
        return clave_usuario;
    }

    public void setClave_usuario(String clave_usuario) {
        this.clave_usuario = clave_usuario;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }
            
  
    
    
    
}
