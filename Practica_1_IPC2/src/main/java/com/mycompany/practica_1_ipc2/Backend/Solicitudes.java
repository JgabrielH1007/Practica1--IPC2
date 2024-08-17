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
import java.util.Random;

import java.text.DecimalFormatSymbols;

import java.util.Random;
import javax.swing.JOptionPane;


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
    private int numeroSolicitud;

    public Solicitudes(InterlFrameSolicitud interSoli, String nombre, String direccion, String tipo, String fecha, double salario) {
        this.interSoli = interSoli;
        this.nombre = nombre;
        this.direccion = direccion;
        this.tipo = tipo;
        this.fecha = fecha;
        this.salario = salario;

        try {
            connection = DriverManager.getConnection(URL_MYSQL, USER, PASSWORD);
            System.out.println("Conexión establecida con éxito.");
            guardarSolicitud();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public int generarNumeroSolicitud() {
        Random random = new Random();
        return 100000 + random.nextInt(900000); // Genera un número de 6 dígitos
    }

    public boolean verificarNumeroSolicitud(int numeroSolicitud) {
        String query = "SELECT COUNT(*) FROM solicitud_nueva WHERE numero_solicitud = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, numeroSolicitud);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) == 0; // Retorna true si no existe el número
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al verificar el número de solicitud en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return false; // En caso de error, consideramos que no está disponible
    }

    public void guardarSolicitud() {
        boolean numeroDisponible = false;

        // Generar y verificar el número de solicitud hasta que sea único
        while (!numeroDisponible) {
            numeroSolicitud = generarNumeroSolicitud();
            numeroDisponible = verificarNumeroSolicitud(numeroSolicitud);
        }

        String insert = "INSERT INTO solicitud_nueva (numero_solicitud, fecha_solicitud, tipo, nombre, salario, direccion) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
            preparedStatement.setInt(1, numeroSolicitud);
            preparedStatement.setString(2, fecha);
            preparedStatement.setString(3, tipo);
            preparedStatement.setString(4, nombre);
            preparedStatement.setDouble(5, salario);
            preparedStatement.setString(6, direccion);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected> " + rowsAffected);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public int getNumeroSolicitud() {
        return numeroSolicitud;
    }
    
    
    
}

    
    


  
