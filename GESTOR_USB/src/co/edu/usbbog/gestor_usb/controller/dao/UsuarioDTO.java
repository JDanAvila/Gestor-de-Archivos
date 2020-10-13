/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.gestor_usb.controller.dao;

import co.edu.usbbog.gestor_usb.model.Usuario;
import javax.swing.JTable;

/**
 *
 * @author nico_
 */
public interface UsuarioDTO {
    public boolean create(Usuario usuario);
    public boolean edit(Usuario usuario);
    public boolean remove(Usuario usuario);
    public void find(JTable tabla, int id);
    public void find(JTable tabla);
    public int count();
    public boolean validar(String nick, String pass);
    public int validar2(); 
}
