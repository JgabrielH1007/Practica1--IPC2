/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica_1_ipc2.Backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author gabrielh
 */
public class Movimiento {
    private Administrador admin;
    private double monto;
    private String numero_tarjeta;
    private String fecha;
    private String tipo;
    private String descripcion;
    private String codMovimiento;
    private Connection connection;
    
    public Movimiento(Administrador admin, double monto, String numero_tarjeta, String fecha, String tipo, String descripcion, String codMovimiento) {
        this.admin = admin;
        this.monto = monto;
        this.numero_tarjeta = numero_tarjeta;
        this.fecha = fecha;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.codMovimiento = codMovimiento;
        try {
            connection = DriverManager.getConnection(admin.getURL_MYSQL(), admin.getUSER(), admin.getPASSWORD());
            System.out.println("Esquema: " + connection.getSchema());
        } catch (SQLException e) {
            System.out.println("Error al connectar a la DB");
            e.printStackTrace();
        }
    }
    
    public void realizarCargo(double saldo){
        double nuevoSaldo = saldo - monto;
        if (actualizarSaldo(numero_tarjeta, nuevoSaldo)) {
            System.out.println("Heho");
        } else {
            JOptionPane.showMessageDialog(null, "Error al actualizar el saldo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    private boolean actualizarSaldo(String numeroTarjeta, double nuevoSaldo) {
        String sql = "UPDATE tarjetas SET saldo = ? WHERE numero_tarjeta = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDouble(1, nuevoSaldo);
            pstmt.setString(2, numeroTarjeta);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el saldo en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
    }
    
    public void realizarAbono(double Saldo){
        
    }
    
    public void guardarMovimiento(){
        
    }
    
}
