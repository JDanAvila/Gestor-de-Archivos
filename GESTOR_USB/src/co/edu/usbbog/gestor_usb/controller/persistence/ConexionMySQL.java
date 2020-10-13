/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.gestor_usb.controller.persistence;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class ConexionMySQL {
     private final String path;
    private final int port;
    private final String user;
    private final String pass;
    private final String database;
    private Connection connection;

    public ConexionMySQL() {
        this.path = "jdbc:mysql://localhost";
        this.port = 3306;
        this.user = "root";
        this.pass = "password";
        this.database = "gestor_db";
        this.connection = null;
    }
    
    public boolean conectar(){
        String path = this.path + 
                ":" + this.port + 
                "/" + this.database + 
                "?user=" + this.user +
                "&password=" + this.pass;  
        System.out.println(path);
        try {
            this.connection = (Connection) DriverManager.getConnection(path);
            if (this.connection!=null) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }   
    
    public void desconectar(){
        try {
            this.connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.connection = null;
    }

    public Connection getConnection() {
        return connection;
    }
}
