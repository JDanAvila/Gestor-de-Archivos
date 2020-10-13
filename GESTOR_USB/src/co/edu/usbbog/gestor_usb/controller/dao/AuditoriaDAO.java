/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.gestor_usb.controller.dao;

import co.edu.usbbog.gestor_usb.controller.persistence.ConexionMySQL;
import co.edu.usbbog.gestor_usb.model.Auditoria;
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
public class AuditoriaDAO implements AuditoriaDTO {
    
    private final ConexionMySQL mySQL;

    public AuditoriaDAO() {
        this.mySQL = new ConexionMySQL();
    }
    @Override
    public boolean create(Auditoria auditoria) {
    boolean seHizo = false;
        this.mySQL.conectar();
        try {
            String query = "INSERT INTO auditoria VALUES("
                    + auditoria.getId() + ","
                    + auditoria.getFecha() + ","
                    + auditoria.getDescripcion() + ","
                    + auditoria.getUsuario()
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
    public boolean edit(Auditoria auditoria) {
    boolean seHizo = false;
        this.mySQL.conectar();
        try {
            String query = "UPDATE auditoria SET"
                    + "id =" + auditoria.getId() + ","
                    + "fecha =" + auditoria.getFecha()+ ","
                    + "descripcion =" + auditoria.getDescripcion()+ ","
                    + "usuario =" + auditoria.getUsuario()
                    + " WHERE id =" + auditoria.getId() + ";";
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
    public boolean remove(Auditoria auditoria) {
    boolean seHizo = false;
        this.mySQL.conectar();
        try {
            String query = "DELETE FROM auditoria WHERE id = " + auditoria.getId() + ";";
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
            String[] columnas = {"id", "fecha", "descripcion", "usuario"};
            model = new DefaultTableModel(null, columnas);
            String sql = "SELECT * FROM auditoria WHERE id = " + id + ";";
            String[] filas = new String[4];
            Statement st = null;
            ResultSet rs = null;
            try {
                st = con.createStatement();
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    for (int i = 0; i < 4; i++) {
                        filas[i] = rs.getString(i + 1);
                    }
                    model.addRow(filas);
                }
                tabla.setModel(model);
            } catch (Exception e) {
                
            }
        }
    }

    @Override
    public void findAll(JTable tabla) {
    if (this.mySQL.conectar()) {
            Connection con = this.mySQL.getConnection();
            DefaultTableModel model;
            String[] columnas = {"id", "fecha", "descripcion", "usuario"};
            model = new DefaultTableModel(null, columnas);
            String sql = "SELECT * FROM auditoria;";
            String[] filas = new String[4];
            Statement st = null;
            ResultSet rs = null;
            try {
                st = con.createStatement();
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    for (int i = 0; i < 4; i++) {
                        filas[i] = rs.getString(i + 1);
                    }
                    model.addRow(filas);
                }
                tabla.setModel(model);
            } catch (Exception e) {
                
            }
        }
    }

    @Override
    public int count() {
    int count = 0;
        try {
            String query = "SELECT COUNT(id) FROM auditoria;";
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
