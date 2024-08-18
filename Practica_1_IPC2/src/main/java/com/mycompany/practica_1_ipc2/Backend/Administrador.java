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
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author gabrielh
 */
public class Administrador {
    private static final String URL_MYSQL = "jdbc:mysql://localhost:3306/control_entidad_bancaria";
    private static final String USER = "root";
    private static final String PASSWORD = "1007";
    private Connection connection;

    public Administrador() {
        try {
            connection = DriverManager.getConnection(URL_MYSQL, USER, PASSWORD);
            System.out.println("Esquema: " + connection.getSchema());
        } catch (SQLException e) {
            System.out.println("Error al connectar a la DB");
            e.printStackTrace();
        }
    }
    
    
    public void autorizarTarjetas(){
        
    }
    
    public void buscarSolicitud(String numeroSolicitud){
        String sql = "SELECT nombre, tipo, salario FROM solicitud_nueva WHERE numero_solicitud = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            // Establecer el valor del parámetro en la consulta SQL
            pstmt.setString(1, numeroSolicitud);

            // Ejecutar la consulta
            ResultSet rs = pstmt.executeQuery();

            // Procesar el resultado
            if (rs.next()) {
                // Obtener los datos de la tupla
                String nombre = rs.getString("nombre");
                String tipo = rs.getString("tipo");
                double salario = rs.getDouble("salario");

                // Mostrar los datos
                System.out.println("Solicitud encontrada:");
                System.out.println("Nombre: " + nombre);
                System.out.println("Tipo: " + tipo);
                System.out.println("Salario: " + salario);
            } else {
                System.out.println("No se encontró la solicitud con el número: " + numeroSolicitud);
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar a la DB");
            e.printStackTrace();
        }
    }
    
    public void asignarNumeroTarjeta(){
        
    }
    
    
    
    
}
