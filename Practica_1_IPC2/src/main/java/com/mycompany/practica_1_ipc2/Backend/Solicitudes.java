/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica_1_ipc2.Backend;

import com.mycompany.practica_1_ipc2.Fronted.InterlFrameSolicitud;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 *
 * @author gabrielh
 */
public class Solicitudes {
    private InterlFrameSolicitud interSoli;
    private boolean estado = true;
    private String nombre;
    private String direccion;
    private String tipo;
    private String fecha;
    private double salario;
    private static final String URL_MYSQL = "jdbc:mysql://localhost:3306/control_entidad_bancaria";
    private static final String USER = "root";
    private static final String PASSWORD = "1007";
     private Connection connection;
    
    public Solicitudes(InterlFrameSolicitud interSoli, String nombre, String direccion, String tipo, String fecha, double salario){
        this.interSoli = interSoli;
        this.nombre = nombre;
        this.direccion = direccion;
        this.tipo = tipo;
        this.fecha = fecha;
        this.salario = salario;
        //DecimalFormat decimalFormat = new DecimalFormat("#0.00");      
        try {
            connection = DriverManager.getConnection(URL_MYSQL, USER, PASSWORD);
            System.out.println("Esquema: " + connection.getSchema());
        } catch (SQLException e) {
            System.out.println("Error al connectar a la DB");
            e.printStackTrace();
        }
         /*   // Formatear el salario
        String salarioFormateado = decimalFormat.format(salario);
         System.out.println("Dirección: " + direccion);
            System.out.println("Fecha: " + fecha);
            System.out.println("Nombre: " + nombre);
            System.out.println("Salario: " + salarioFormateado);
            System.out.println("Tipo: "+tipo);*/
         guardarSolicitudes();
         consultarNumeroSolicitudMasReciente();
    }
    

     public void guardarSolicitudes() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.'); // Establece el punto como separador decimal

        DecimalFormat decimalFormat = new DecimalFormat("#0.00", symbols);

        // Formatear el salario
        String salarioFormateado = decimalFormat.format(salario);

        String insert = "INSERT INTO solicitud (fecha_solicitud, tipo, nombre, salario, direccion) "
                + "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
            preparedStatement.setString(1, fecha);
            preparedStatement.setString(2, tipo);
            preparedStatement.setString(3, nombre);
            preparedStatement.setString(4, salarioFormateado);
            preparedStatement.setString(5, direccion);
            
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected> " + rowsAffected);
        } catch (SQLException e) {
            System.out.println("Error al insertar a la DB");
            e.printStackTrace();
        }
    }
    
    public void consultarNumeroSolicitudMasReciente() {
        String query = "SELECT numero_solicitud FROM solicitud "
                     + "WHERE fecha_solicitud = (SELECT MAX(fecha_solicitud) "
                     + "FROM solicitud "
                     + "WHERE fecha_solicitud = ? "
                     + "AND tipo = ? "
                     + "AND nombre = ? "
                     + "AND salario = ? "
                     + "AND direccion = ?) "
                     + "ORDER BY fecha_solicitud DESC "
                     + "LIMIT 1";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, fecha);
            preparedStatement.setString(2, tipo);
            preparedStatement.setString(3, nombre);
            preparedStatement.setString(4, new DecimalFormat("#0.00").format(salario));
            preparedStatement.setString(5, direccion);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int numeroSolicitud = resultSet.getInt("numero_solicitud");
                    System.out.println("Número de solicitud más reciente: " + numeroSolicitud);
                } else {
                    System.out.println("No se encontraron solicitudes con los atributos proporcionados.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar la DB");
            e.printStackTrace();
        }
    }
}
  
