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
public interface IComprasDAO {
    public List<Usuarios> buscarUsuarios(String nombre);
    public List<Productos> buscarProductos();
}
