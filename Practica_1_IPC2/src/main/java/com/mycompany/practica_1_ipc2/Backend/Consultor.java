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
import javax.swing.JOptionPane;

/**
 *
 * @author gabrielh
 */
public class Consultor {
    private Connection connection;
    private String numeroTarjeta;
    private double limite;
    private boolean estadoTarjeta;
    private String tipo;
    private String nombre;
    private String direccion;

    // Constructor
    public Consultor() {
        String URL_MYSQL = "jdbc:mysql://localhost:3306/control_entidad_bancaria";
        String USER = "root";
        String PASSWORD = "1007";
        try {
            connection = DriverManager.getConnection(URL_MYSQL, USER, PASSWORD);
            System.out.println("Esquema: " + connection.getSchema());
        } catch (SQLException e) {
            System.out.println("Error al connectar a la DB");
            e.printStackTrace();
        };
    }

    // Método para obtener la información de la tarjeta
    public boolean obtenerInformacionTarjeta(String numeroTarjeta) {
        String sql = "SELECT t.numero_tarjeta, t.limite, t.estado_tarjeta, s.tipo, s.nombre, s.direccion "
                   + "FROM tarjetas t "
                   + "JOIN solicitud_nueva s ON t.numero_solicitud = s.numero_solicitud "
                   + "WHERE t.numero_tarjeta = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, numeroTarjeta);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                this.numeroTarjeta = rs.getString("numero_tarjeta");
                this.limite = rs.getDouble("limite");
                this.estadoTarjeta = rs.getBoolean("estado_tarjeta");
                this.tipo = rs.getString("tipo");
                this.nombre = rs.getString("nombre");
                this.direccion = rs.getString("direccion");
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            mostrarMensaje("Error al conectar con la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
    }

    // Método para actualizar el estado de la tarjeta
    public boolean actualizarEstadoTarjeta(String numeroTarjeta, boolean nuevoEstado) {
        String sql = "UPDATE tarjetas SET estado_tarjeta = ?, fecha_estado = CURRENT_DATE WHERE numero_tarjeta = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setBoolean(1, nuevoEstado);
            pstmt.setString(2, numeroTarjeta);
            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            mostrarMensaje("Error al actualizar el estado de la tarjeta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
    }

    // Método para verificar si la tarjeta tiene deuda
    public boolean tieneDeuda() {
        return limite > obtenerSaldo();
    }

    private double obtenerSaldo() {
        // Obtener saldo de la tarjeta desde la base de datos
        String sql = "SELECT saldo FROM tarjetas WHERE numero_tarjeta = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, numeroTarjeta);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getDouble("saldo");
            }
        } catch (SQLException e) {
            mostrarMensaje("Error al obtener el saldo de la tarjeta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return 0.0;
    }

    // Método para mostrar mensajes
    private void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, tipo);
    }

    // Método para cerrar la conexión
    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    // Métodos getter para obtener la información de la tarjeta
    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public double getLimite() {
        return limite;
    }

    public boolean isEstadoTarjeta() {
        return estadoTarjeta;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }
}

