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
    private boolean estado;
    private Connection connection;
    
    public Tarjeta(Administrador admin, String numeroSolicitud, String fecha, double limite, double saldo, boolean estado){
        this.admin = admin;
        this.numeroSolicitud = numeroSolicitud;
        this.fecha = fecha;
        this.limite = limite;
        this.saldo = saldo;
        this.estado = estado;
        
        try {
            connection = DriverManager.getConnection(admin.getURL_MYSQL(), admin.getUSER(), admin.getPASSWORD());
            System.out.println("Esquema: " + connection.getSchema());
        } catch (SQLException e) {
            System.out.println("Error al connectar a la DB");
            e.printStackTrace();
        }
        
    
    }
    
    private String obtenerNumeroTarjeta(String tipo) {
    String prefix = "";
    int digitIndex = 0; // Para seleccionar el dígito variable (X)

    // Determinar el prefijo base y el índice del dígito variable
    switch (tipo) {
        case "Nacional":
            prefix = "4256 3102 654";
            break;
        case "Internacional":
            prefix = "4256 3102 658";
            break;
        case "Regional":
            prefix = "4256 3102 656";
            break;
        default:
            throw new IllegalArgumentException("Tipo de tarjeta no válido");
    }

    String ultimoNumero = obtenerUltimoNumeroTarjeta(prefix);
    int contador = 1; // Valor inicial por defecto si no hay número anterior
    char digitoVariable = '0'; // Valor inicial del dígito variable

    if (ultimoNumero != null) {
        // Extraer el dígito variable (X) y el contador del último número
        digitoVariable = ultimoNumero.charAt(15); // El dígito variable está en la posición 15
        String contadorStr = ultimoNumero.substring(17).trim(); // El contador comienza en la posición 17

        contador = Integer.parseInt(contadorStr) + 1;

        // Si el contador supera 9999, incrementar el dígito variable y reiniciar el contador
        if (contador > 9999) {
            contador = 1;
            digitoVariable++;
            if (digitoVariable > '9') { // Si el dígito variable supera 9, reiniciarlo y manejar el caso
                digitoVariable = '0';
                throw new IllegalStateException("Se han agotado los dígitos variables disponibles.");
            }
        }
    }

    // Formatear el nuevo número de tarjeta
    String nuevoNumero = prefix + digitoVariable + " " + String.format("%04d", contador);
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

        String sql = "INSERT INTO tarjetas (numero_tarjeta, numero_solicitud, fecha_estado, limite, saldo, estado_tarjeta)"
                + " VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, numeroTarjeta);
            pstmt.setString(2, numeroSolicitud);
            pstmt.setString(3, fecha);
            pstmt.setDouble(4, limite);
            pstmt.setDouble(5, saldo);
            pstmt.setBoolean(6, estado);

            pstmt.executeUpdate();
            System.out.println("Tarjeta guardada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al guardar la tarjeta en la DB");
            e.printStackTrace();
        }
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }
    
    
    
}
