/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.gestor_usb.controller.dao;

import co.edu.usbbog.gestor_usb.model.Carpeta;
import javax.swing.JTable;

/**
 *
 * @author hp
 */
public interface CarpetaDTO {
    public boolean create(Carpeta carpeta);
    public boolean edit(Carpeta carpeta);
    public boolean remove(Carpeta carpeta);
    public void find(JTable tabla, int id);
    public void findAll(JTable tabla);
    public int count();
}
