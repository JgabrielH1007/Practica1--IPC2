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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private static final String INTERNACIONAL = "Internacional";
    private static final String NACIONAL = "Nacional";
    private static final String REGIONAL = "Regional";
    private String tipo;
        
    
    public Administrador() {
        try {
            connection = DriverManager.getConnection(URL_MYSQL, USER, PASSWORD);
            System.out.println("Esquema: " + connection.getSchema());
        } catch (SQLException e) {
            System.out.println("Error al connectar a la DB");
            e.printStackTrace();
        }
    }
    
    
    
    public void autorizarTarjetas(double salario, String tipo, String numeroSoli){
        double limite = salario*0.60;
        if(verificarSalario(salario, limite, tipo)== true){
            LocalDate fechaActual = LocalDate.now();
        
        // Definir el formato deseado
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        // Formatear la fecha en el formato deseado
            String fechaFormateada = fechaActual.format(formatter);
            Tarjeta tarjeta = new Tarjeta(this, numeroSoli,fechaFormateada,limite, limite);
        }
    }
    
    public boolean verificarSalario(double salario, double limite, String tipo){
        limite = salario*0.60;
        double limiteTipo=0;
        if(tipo.equals("Internacional")){
            limiteTipo = 20000;
        }else if (tipo.equals("Regional")){
            limiteTipo = 10000;
        }else{
            limiteTipo = 5000;
        }
        
        if(limite > limiteTipo){
            return true;
        }else{
            return false;
        }
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
                tipo = rs.getString("tipo");
                String numeroSoli = rs.getString("numero_solicitud");
                double salario = rs.getDouble("salario");
                autorizarTarjetas(salario, tipo, numeroSoli);
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

    public String getTipo() {
        return tipo;
    }
    
    public String getURL_MYSQL() {
        return URL_MYSQL;
    }

    public String getUSER() {
        return USER;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }
    
    
    
    
}
