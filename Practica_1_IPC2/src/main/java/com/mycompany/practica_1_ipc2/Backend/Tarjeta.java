/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica_1_ipc2.Backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author gabrielh
 */
public class Tarjeta {
    private Administrador admin;
    private String numeroTarjeta;
    private String numeroSolicitud;
    private String fecha;
    private double limite;
    private double saldo;
    private Connection connection;
    
    public Tarjeta(Administrador admin, String numeroSolicitud, String fecha, double limite, double saldo){
        this.numeroSolicitud = numeroSolicitud;
        this.fecha = fecha;
        this.limite = limite;
        this.saldo = saldo;
        
        try {
            connection = DriverManager.getConnection(admin.getURL_MYSQL(), admin.getUSER(), admin.getPASSWORD());
            System.out.println("Esquema: " + connection.getSchema());
        } catch (SQLException e) {
            System.out.println("Error al connectar a la DB");
            e.printStackTrace();
        }
        guardarTarjeta(); 
    
    }
    
    private String obtenerNumeroTarjeta(String tipo) {
        String prefix = "";
        switch (tipo) {
            case "Nacional":
                prefix = "4256 3102 654";
                break;
            case "Internacional":
                prefix = "4256 3102 656";
                break;
            case "Regional":
                prefix = "4256 3102 658";
                break;
            default:
                throw new IllegalArgumentException("Tipo de tarjeta no válido");
        }
        
        String ultimoNumero = obtenerUltimoNumeroTarjeta(prefix);
        int contador = 1; // Valor inicial por defecto si no hay número anterior

        if (ultimoNumero != null) {
            // Extraer y aumentar el contador
            String contadorStr = ultimoNumero.substring(15).trim();
            contador = Integer.parseInt(contadorStr) + 1;
        }

        // Formatear el nuevo número de tarjeta
        String nuevoNumero = prefix + " " + String.format("%04d", contador);
        return nuevoNumero;
    }
    
    private String obtenerUltimoNumeroTarjeta(String prefix) {
        String sql = "SELECT numero_tarjeta FROM tarjetas WHERE numero_tarjeta LIKE ? ORDER BY numero_tarjeta DESC LIMIT 1";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, prefix + "%");
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("numero_tarjeta");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el último número de tarjeta");
            e.printStackTrace();
        }
        return null;
    }
    
    public void guardarTarjeta() {
        // Obtener el número de tarjeta
        numeroTarjeta = obtenerNumeroTarjeta(admin.getTipo());

        String sql = "INSERT INTO tarjeta (numero_tarjeta, numero_solicitud, fecha, limite, saldo) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, numeroTarjeta);
            pstmt.setString(2, numeroSolicitud);
            pstmt.setString(3, fecha);
            pstmt.setDouble(4, limite);
            pstmt.setDouble(5, saldo);

            pstmt.executeUpdate();
            System.out.println("Tarjeta guardada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al guardar la tarjeta en la DB");
            e.printStackTrace();
        }
    }
    
}
