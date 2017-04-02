/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.mario.dao;

import com.mx.mario.conexion.ConexionMySQL;
import com.mx.mario.vo.EmpleadoExrtrasVO;
import com.mx.mario.vo.EmpleadoVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author smarv
 */
public class EmpleadoDAO {

    private static final String OBTEN_EMPLEADOS = "select id_emp, apellido1, apellido2, nombre1, nombre2, fecha_nac, genero, domicilio, telefono from datos_personales";
    private static final String INSERTA_EMPLEADOS = "insert into datos_personales values (?,?,?,?,?,?,?,?,?)";

    /**
     * Metodo que obtiene los reguistros de datos personales
     *
     * @return List
     */
    public List<EmpleadoVO> getDatosPersonales() {

        List<EmpleadoVO> list = new ArrayList<EmpleadoVO>();
        ConexionMySQL mysql = new ConexionMySQL();
        Connection con = mysql.Conectar();

        String sql = OBTEN_EMPLEADOS;

        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = con.prepareStatement(sql);
            //ps.setInt(1, folio.intValue());
            //ps.setString(2, tipoRegistro);
            System.out.println("ps: " + ps);
            rs = ps.executeQuery();
            while (rs.next()) {
                EmpleadoVO vo = new EmpleadoVO();
                EmpleadoExrtrasVO voe = new EmpleadoExrtrasVO();
                vo.setIdEmp(rs.getInt("id_emp"));
                vo.setApellido1(rs.getString("apellido1"));
                vo.setApellido2(rs.getString("apellido2"));
                vo.setNombre1(rs.getString("nombre1"));
                vo.setNombre2(rs.getString("nombre2"));
                vo.setFechaNacimiento(rs.getString("fecha_nac"));
                vo.setGenero(rs.getString("genero"));
                voe.setDomicilio(rs.getString("domicilio"));
                voe.setTelefono(rs.getString("telefono"));
                vo.setEmpleadoExtrasVO(voe);
                list.add(vo);
            }
            ps.close();

        } catch (Exception e) {
            System.out.println("No se pudo obtener el detalle de BD: " + e);
        }
        return list;
    }

    /**
     * Metodo que inserta un registro de datos personales.
     * @param id
     * @param nombre1
     * @param nombre2
     * @param apellido1
     * @param apellido2
     * @param fechaNacimiento
     * @param genero
     * @param domicilio
     * @param telefono
     * @return resultado
     */
    public boolean insertaDatosPersonales(int id, String nombre1, String nombre2, String apellido1, String apellido2, String fechaNacimiento, 
            String genero, String domicilio, String telefono) {

        boolean resultado;
        String query = "";
        ConexionMySQL mysql = new ConexionMySQL();
        Connection con = mysql.Conectar();
        String sql = INSERTA_EMPLEADOS;
        PreparedStatement ps;

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, apellido1);
            ps.setString(3, apellido2);
            ps.setString(4, nombre1);
            ps.setString(5, nombre2);
            ps.setString(6, fechaNacimiento);
            ps.setString(7, genero);
            ps.setString(8, domicilio);
            ps.setString(9, telefono);
            System.out.println("ps: " + ps);
            query = ps.toString();
            ps.execute();
            resultado = true;

        } catch (SQLException ex) {
            System.err.println("Error al realizar la sentencia : " + query + " error: " + ex);
            resultado = false;
        }
        
        try {
            con.close();
            System.out.println("conexion cerrada.");
        } catch (SQLException ex) {
            System.err.println("Error al cerrar la conexion: " + ex);
        }
        return resultado;
    }
}