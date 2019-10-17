/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.modelo;

/**
 *
 * @author gonzalo
 */
public class Productos {
    int cod_producto;
    String nombre_producto;
    String precio_producto;
    String stock_producto;
    String estado_producto;

    
    public Productos(){
        
    }
    
    public Productos(int cod_producto, String nombre_producto, String precio_producto, String stock_producto, String estado_producto) {
        this.cod_producto = cod_producto;
        this.nombre_producto = nombre_producto;
        this.precio_producto = precio_producto;
        this.stock_producto = stock_producto;
        this.estado_producto = estado_producto;
    }
    
    
    public int getCod_producto() {
        return cod_producto;
    }

    public void setCod_producto(int cod_producto) {
        this.cod_producto = cod_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getPrecio_producto() {
        return precio_producto;
    }

    public void setPrecio_producto(String precio_producto) {
        this.precio_producto = precio_producto;
    }

    public String getStock_producto() {
        return stock_producto;
    }

    public void setStock_producto(String stock_producto) {
        this.stock_producto = stock_producto;
    }

    public String getEstado_producto() {
        return estado_producto;
    }

    public void setEstado_producto(String estado_producto) {
        this.estado_producto = estado_producto;
    }
    
    
    
    
    
    
    
}
