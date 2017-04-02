/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.mario.facade;

import com.mx.mario.dao.EmpleadoDAO;
import com.mx.mario.vo.EmpleadoVO;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author smarv
 */
public class EmpleadoFacade {

    /**
     * Metodo main
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        muestraMenu();
    }

    /**
     * Metodo consultar
     */
    public static void consultar() {
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        List<EmpleadoVO> list = empleadoDAO.getDatosPersonales();
        if (!list.isEmpty()) {
            System.out.println("Tama?o de lista: " + list.size());
            for (Iterator<EmpleadoVO> it = list.iterator(); it.hasNext();) {
                EmpleadoVO empleadoVO = it.next();
                System.out.println("***********************************************");
                System.out.println("empleadoVO.getIdEmp(): " + empleadoVO.getIdEmp());
                System.out.println("empleadoVO.getApellido1(): " + empleadoVO.getApellido1());
                System.out.println("empleadoVO.getApellido2(): " + empleadoVO.getApellido2());
                System.out.println("empleadoVO.getNombre1(): " + empleadoVO.getNombre1());
                System.out.println("empleadoVO.getNombre2(): " + empleadoVO.getNombre2());
                System.out.println("empleadoVO.getFechaNacimiento(): " + empleadoVO.getFechaNacimiento());
                System.out.println("empleadoVO.getGenero(): " + empleadoVO.getGenero());
                System.out.println("empleadoVO.getEmpleadoExtrasVO().getDomicilio(): " + empleadoVO.getEmpleadoExtrasVO().getDomicilio());
                System.out.println("empleadoVO.getEmpleadoExtrasVO().getTelefono(): " + empleadoVO.getEmpleadoExtrasVO().getTelefono());
            }
            muestraMenu();
        } else {
            System.out.println("No hay datos en la tabla.");
        }
    }

    /**
     * metodo insertar
     */
    public static void insertar() {
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        int id;
        String nombre1;
        String nombre2;
        String apellido1;
        String apellido2;
        String fechaNacimiento;
        String genero;
        String domicilio;
        String telefono;

        id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa ID"));
        nombre1 = JOptionPane.showInputDialog("Ingresa Nombre 1");
        nombre2 = JOptionPane.showInputDialog("Ingresa Nombre 2");
        apellido1 = JOptionPane.showInputDialog("Ingresa Apellido 1");
        apellido2 = JOptionPane.showInputDialog("Ingresa Apellido 2");
        fechaNacimiento = JOptionPane.showInputDialog("Fecha nacimiento AAAA-MM-DD");
        genero = JOptionPane.showInputDialog("Genero");
        domicilio = JOptionPane.showInputDialog("Domicilio");
        telefono = JOptionPane.showInputDialog("Telefono");

        if (String.valueOf(id) != null && nombre1 != null && nombre2 != null && apellido1 != null && apellido2 != null && fechaNacimiento != null && genero != null) {
            System.out.println("Entra...");
            if (empleadoDAO.insertaDatosPersonales(id, nombre1, nombre2, apellido1, apellido2, fechaNacimiento, genero, domicilio, telefono)) {
                System.out.println("Registro insertado.");
                muestraMenu();
            } else {
                System.out.println("No se pudo insertar el registro.");
                muestraMenu();
            }

        }
    }

    public static void muestraMenu() {
        System.out.println("empleiza metodo main...");
        System.out.println("Elija una opcion");
        System.out.println("1- Consultar");
        System.out.println("2- Insertar");
        System.out.println("0- Salir");
        String opcion;
        opcion = JOptionPane.showInputDialog("Elija una opcion");

        if (opcion.equalsIgnoreCase("1")) {
            consultar();
        } else if (opcion.equalsIgnoreCase("2")) {
            insertar();
        } else if (opcion.equalsIgnoreCase("0")) {
            System.exit(0);
        } else {
            JOptionPane.showMessageDialog(null, "No elijio nunguna opcion valida");
            muestraMenu();
        }
    }
}
