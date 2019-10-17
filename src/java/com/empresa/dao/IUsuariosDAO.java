/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.dao;
import com.empresa.modelo.Usuarios;
import java.util.List;

/**
 *
 * @author gonzalo
 */
public interface IUsuariosDAO {
    
    public boolean registrar(Usuarios usuario);
    public List<Usuarios> obtener();
    public boolean actualizar(Usuarios usuario);
    public boolean eliminar(String[] datos);
    public Usuarios buscar(int codigo);
    public Usuarios buscarPorNombre(String nombre);
    
}
