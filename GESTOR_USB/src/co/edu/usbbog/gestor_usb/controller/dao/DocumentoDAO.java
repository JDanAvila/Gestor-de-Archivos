/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.gestor_usb.controller.dao;

import co.edu.usbbog.gestor_usb.controller.persistence.ConexionMySQL;
import co.edu.usbbog.gestor_usb.model.Documento;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hp
 */
public class DocumentoDAO implements DocumentoDTO {
    
    private final ConexionMySQL mySQL;

    public DocumentoDAO() {
        this.mySQL = new ConexionMySQL();
    }
    
    @Override
    public boolean create(Documento doc) {
        boolean seHizo = false;
        this.mySQL.conectar();
        try {
            String query = "INSERT INTO documento VALUES("
                    + doc.getId() + ","
                    + doc.getName() + ","
                    + doc.getDescripcion() + ","
                    + doc.getFechaCreate() + ","
                    + doc.getFechaEdit() + ","
                    + doc.getAutor()+ ","
                    + doc.getSize() + ","
                    + doc.getType() + ","
                    + doc.getArchivo() + ","
                    + doc.getCarpeta()                   
                    + ");";
            Statement stmt = this.mySQL.getConnection().createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            seHizo = true;
        } catch (SQLException e) {
            seHizo = false;
        }
        this.mySQL.desconectar();
        return seHizo;
    }

    @Override
    public boolean edit(Documento doc) {
        boolean seHizo = false;
        this.mySQL.conectar();
        try {
            String query = "UPDATE documento SET"
                    + "id =" + doc.getId() + ","
                    + "name =" + doc.getName() + ","
                    + "descripcion ="+ doc.getDescripcion() + ","
                    + "fecha_create ="+ doc.getFechaCreate()+ ","
                    + "fecha_edit ="+ doc.getFechaEdit() + ","
                    + "autor ="+ doc.getAutor() + ","
                    + "size ="+ doc.getSize() + ","
                    + "type ="+ doc.getType() + ","
                    + "archivo ="+ doc.getArchivo() + ","
                    + "carpeta ="+ doc.getCarpeta() + "," 
                    + " WHERE id =" + doc.getId() + ";";
            Statement stmt = this.mySQL.getConnection().createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            seHizo = true;
        } catch (SQLException e) {
            seHizo = false;
        }
        this.mySQL.desconectar();
        return seHizo;
    }

    @Override
    public boolean remove(Documento doc) {
        boolean seHizo = false;
        this.mySQL.conectar();
        try {
            String query = "DELETE FROM documento WHERE id = " + doc.getId() + ";";
            Statement stmt = this.mySQL.getConnection().createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            seHizo = true;
        } catch (SQLException e) {
            seHizo = false;
        }
        this.mySQL.desconectar();
        return seHizo;
    }

    @Override
    public void find(JTable tabla, int id) {
        if (this.mySQL.conectar()) {
            Connection con = this.mySQL.getConnection();
            DefaultTableModel model;
            String[] columnas = {"id", "name", "descripcion", "fecha_create", "fecha_edit", "autor", "size", "type", "archivo", "carpeta"};
            model = new DefaultTableModel(null, columnas);
            String sql = "SELECT * FROM documento WHERE id = " + id + ";";
            String[] filas = new String[10];
            Statement st = null;
            ResultSet rs = null;
            try {
                st = con.createStatement();
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    for (int i = 0; i < 10; i++) {
                        filas[i] = rs.getString(i + 1);
                    }
                    model.addRow(filas);
                }
                tabla.setModel(model);
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public void findAll(JTable tabla) {
        if (this.mySQL.conectar()) {
            Connection con = this.mySQL.getConnection();
            DefaultTableModel model;
            String[] columnas = {"id", "name", "descripcion", "fecha_create", "fecha_edit", "autor", "size", "type", "archivo", "carpeta"};
            model = new DefaultTableModel(null, columnas);
            String sql = "SELECT * FROM documento;";
            String[] filas = new String[10];
            Statement st = null;
            ResultSet rs = null;
            try {
                st = con.createStatement();
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    for (int i = 0; i < 10; i++) {
                        filas[i] = rs.getString(i + 1);
                    }
                    model.addRow(filas);
                }
                tabla.setModel(model);
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public int count() {
        int count = 0;
        try {
            String query = "SELECT COUNT(id) FROM documento;";
            Connection con = this.mySQL.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = (ResultSet) stm.executeQuery(query);
            while (rs.next()) {
                count++;
            }
            rs.close();
            stm.close();
            con.close();
        } catch (SQLException e) {
            return count;
        }
        return count;
    }
    
}
