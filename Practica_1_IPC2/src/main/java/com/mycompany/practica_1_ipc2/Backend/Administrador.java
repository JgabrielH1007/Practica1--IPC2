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
    private boolean autorizado;
    private String numeroTarjeta;
    private boolean estado = false;
    
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
            if (verificarEstadoSolicitud(numeroSoli)) {
            LocalDate fechaActual = LocalDate.now();
        
        // Definir el formato deseado
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        estado = true;
        // Formatear la fecha en el formato deseado
            String fechaFormateada = fechaActual.format(formatter);
            Tarjeta tarjeta = new Tarjeta(this, numeroSoli,fechaFormateada,limite, limite,estado);
            tarjeta.guardarTarjeta();
           // Puedes omitir los parámetros si no los usas
           Solicitudes solicitud = new Solicitudes("", "", "", "", 0);
            solicitud.setNumeroSolicitud(Integer.parseInt(numeroSoli));
            solicitud.actualizarEstadoSolicitud(true);
            numeroTarjeta = tarjeta.getNumeroTarjeta();
            
        }else {
                autorizado = false;
                JOptionPane.showMessageDialog(null,
                        "La solicitud con el número " + numeroSoli + " ya ha sido aprobada o rechazada.",
                        "Solicitud No Válida",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        }else {
                JOptionPane.showMessageDialog(null,
                        "Solicitud Rechazada","SOLICITUD FUE DENEGADA",
                        JOptionPane.INFORMATION_MESSAGE);
            }

    }
        
        public boolean verificarEstadoSolicitud(String numeroSoli) {
        String query = "SELECT estado_solicitud FROM solicitud_nueva WHERE numero_solicitud = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, numeroSoli);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    
                    return !resultSet.getBoolean("estado_solicitud");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al verificar el estado de la solicitud en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return false;
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
            autorizado = true;
            return true;
        }else{
            autorizado = false;
            System.out.println("No se puede autorizar");
            return false;
        }
    }
    
    public void buscarSolicitud(String numeroSolicitud){
        String sql = "SELECT numero_solicitud, nombre, tipo, salario FROM solicitud_nueva WHERE numero_solicitud = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            // Establecer el valor del parámetro en la consulta SQL
            pstmt.setString(1, numeroSolicitud);

            // Ejecutar la consulta
            ResultSet rs = pstmt.executeQuery();

            // Procesar el resultado
            if (rs.next()) {
                // Obtener los datos de la tupla
                String numeroSoli = rs.getString("numero_solicitud");
                String nombre = rs.getString("nombre");
                tipo = rs.getString("tipo");
                double salario = rs.getDouble("salario");
                autorizarTarjetas(salario, tipo, numeroSoli);
            } else {
                JOptionPane.showMessageDialog(null,
                "No se encontró la solicitud con el número: " + numeroSolicitud,
                "Solicitud No Encontrada",
                JOptionPane.INFORMATION_MESSAGE);            
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar a la DB");
            e.printStackTrace();
        }
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }
    
    
    
    public boolean isAutorizado() {
        return autorizado;
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
