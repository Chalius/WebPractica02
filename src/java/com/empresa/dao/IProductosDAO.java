/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.dao;

import com.empresa.modelo.Productos;
import com.empresa.modelo.Usuarios;
import java.util.List;

/**
 *
 * @author gonzalo
 */
public interface IProductosDAO {
    public boolean registrar(Productos producto);
    public List<Productos> obtener();
    public boolean actualizar(Productos producto);
    public boolean eliminar(String[] datos);
    public Productos buscar(int codigo);
    public Productos buscarPorNombre(String nombre);
    public List<Productos> buscarProductos(String nombre);
 }
