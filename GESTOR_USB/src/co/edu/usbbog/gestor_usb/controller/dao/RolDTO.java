/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.gestor_usb.controller.dao;

import co.edu.usbbog.gestor_usb.model.Rol;
import javax.swing.JTable;

/**
 *
 * @author hp
 */
public interface RolDTO {
    public boolean create(Rol rol);
    public boolean edit(Rol rol);
    public boolean remove(Rol rol);
    public void find(JTable tabla, int id);
    public void find(JTable tabla);
    public int count();
}
