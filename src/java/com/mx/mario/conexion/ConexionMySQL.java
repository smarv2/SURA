package com.mx.mario.conexion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.*;

/**
 *
 * @author smarv
 */
public class ConexionMySQL {

    public String db = "empleado";
    public String url = "jdbc:mysql://localhost/" + db;
    public String user = "root";
    public String pass = "";

    /*public ConexionMySQL() {
    }*/

    /**
     * Metodo que realiza la conexion a DB
     * @return link
     */
    public Connection Conectar() {
        Connection link = null;
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            link = DriverManager.getConnection(this.url, this.user, this.pass);
            System.out.println("Conexion exitosa.");
        } catch (Exception e) {
            System.err.println("Error al realiar la conexion: " + e);
        }
        return link;
    }
}
