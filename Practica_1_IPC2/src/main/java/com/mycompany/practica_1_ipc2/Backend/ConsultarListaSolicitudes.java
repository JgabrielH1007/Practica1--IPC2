/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica_1_ipc2.Backend;

import java.awt.Component;
import java.awt.Dimension;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author gabrielh
 */
public class ConsultarListaSolicitudes {
    private static final String URL_MYSQL = "jdbc:mysql://localhost:3306/control_entidad_bancaria";
    private static final String USER = "root";
    private static final String PASSWORD = "1007";
    private Connection connection;

    public ConsultarListaSolicitudes() {
        try {
            connection = DriverManager.getConnection(URL_MYSQL, USER, PASSWORD);
            System.out.println("Conexión establecida con éxito.");
           
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
 
    public void obtenerDatos(JTable table, JCheckBox jcbRangoFecha, JCheckBox jcbSalario, JComboBox<String> jcbTipo, JComboBox<String> jcbEstado,
                      JTextField jtfFechaInicio, JTextField jtfFechaFin, JTextField jtfSalario) throws ParseException {
        StringBuilder query = new StringBuilder();
        query.append("SELECT numero_solicitud, fecha_solicitud, tipo, nombre, salario, direccion, estado_solicitud ");
        query.append("FROM solicitud_nueva ");
        query.append("WHERE 1=1 ");
    
    // Filtros de fecha
        if (jcbRangoFecha.isSelected() && !jtfFechaInicio.getText().trim().isEmpty() && !jtfFechaFin.getText().trim().isEmpty()) {
            try {
                SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            
                Date fechaInicio = inputFormat.parse(jtfFechaInicio.getText().trim());
                Date fechaFin = inputFormat.parse(jtfFechaFin.getText().trim());
                String fechaInicioSQL = outputFormat.format(fechaInicio);
                String fechaFinSQL = outputFormat.format(fechaFin);
            
                query.append("AND fecha_solicitud BETWEEN ? AND ? ");
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(null, "Error al procesar las fechas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return; // Salir del método si hay un error de formato
            }
        }

    // Filtros de salario
        if (jcbSalario.isSelected() && !jtfSalario.getText().trim().isEmpty()) {
            try {
                BigDecimal salario = new BigDecimal(jtfSalario.getText().trim());
                query.append("AND salario >= ? ");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "El salario debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Salir del método si hay un error de formato
            }
        }

    // Filtros de tipo
        if (jcbTipo.getSelectedIndex() > 0) { // Supongamos que "Ninguno" está en el índice 0
            query.append("AND tipo = ? ");
        }

    // Filtros de estado
        if (jcbEstado.getSelectedIndex() > 0) { // Supongamos que "Ninguno" está en el índice 0
            query.append("AND estado_solicitud = ? ");
        }

        query.append("ORDER BY fecha_solicitud");

        System.out.println("Consulta SQL: " + query.toString());

        try (PreparedStatement ps = connection.prepareStatement(query.toString())) {
            int index = 1;

        // Establecer parámetros de fecha si se usan
            if (jcbRangoFecha.isSelected() && !jtfFechaInicio.getText().trim().isEmpty() && !jtfFechaFin.getText().trim().isEmpty()) {
                SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            
                Date fechaInicio = inputFormat.parse(jtfFechaInicio.getText().trim());
                Date fechaFin = inputFormat.parse(jtfFechaFin.getText().trim());
                String fechaInicioSQL = outputFormat.format(fechaInicio);
                String fechaFinSQL = outputFormat.format(fechaFin);
            
                ps.setDate(index++, java.sql.Date.valueOf(fechaInicioSQL));
                ps.setDate(index++, java.sql.Date.valueOf(fechaFinSQL));
            }

        // Establecer parámetro de salario si se usa
            if (jcbSalario.isSelected() && !jtfSalario.getText().trim().isEmpty()) {
                ps.setBigDecimal(index++, new BigDecimal(jtfSalario.getText().trim()));
            }

        // Establecer parámetro de tipo si se usa
            if (jcbTipo.getSelectedIndex() > 0) { // "Ninguno" está en el índice 0
                ps.setString(index++, jcbTipo.getSelectedItem().toString());
            }

        // Establecer parámetro de estado si se usa
            if (jcbEstado.getSelectedIndex() > 0) { // "Ninguno" está en el índice 0
                boolean estado = jcbEstado.getSelectedIndex() == 1; // Suponiendo que el índice 1 es para autorizada
                ps.setBoolean(index++, estado);
            }

            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Limpiar tabla antes de agregar los nuevos datos

            int rowCount = 0;
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("numero_solicitud"),
                    rs.getDate("fecha_solicitud"),
                    rs.getString("tipo"),
                    rs.getString("nombre"),
                    rs.getBigDecimal("salario"),
                    rs.getString("direccion"),
                    rs.getObject("estado_solicitud") == null ? "" : (rs.getBoolean("estado_solicitud") ? "Autorizada" : "Rechazada")
                });
                rowCount++;
            }

            System.out.println("Total de filas recuperadas: " + rowCount); // Depuración

            if (rowCount == 0) {
                JOptionPane.showMessageDialog(null, "No se encontraron datos que coincidan con los filtros seleccionados.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }

            table.revalidate();
            table.repaint();
            ajustarAnchoYAltura(table);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al consultar la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error en el formato del salario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    private void ajustarAnchoYAltura(JTable table) {
        TableColumnModel columnModel = table.getColumnModel();

    // Ajustar el ancho de las columnas
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            TableColumn column = columnModel.getColumn(i);
            int maxWidth = 0;

        // Calcular el ancho máximo basado en el contenido de las celdas
            for (int j = 0; j < table.getRowCount(); j++) {
                TableCellRenderer renderer = table.getCellRenderer(j, i);
                Component comp = renderer.getTableCellRendererComponent(table, table.getValueAt(j, i), false, false, j, i);
                int cellWidth = comp.getPreferredSize().width;
                maxWidth = Math.max(cellWidth, maxWidth);
            }

        // Ajustar el ancho basado en el encabezado de la columna
            TableCellRenderer headerRenderer = table.getTableHeader().getDefaultRenderer();
            Component headerComp = headerRenderer.getTableCellRendererComponent(table, column.getHeaderValue(), false, false, -1, i);
            int headerWidth = headerComp.getPreferredSize().width;

        // Añadir un margen extra
            int newWidth = Math.max(maxWidth, headerWidth) + table.getIntercellSpacing().width + 10;
            column.setPreferredWidth(newWidth);
        }

    // Ajustar la altura de las filas
        for (int j = 0; j < table.getRowCount(); j++) {
            int maxHeight = 0;
            for (int i = 0; i < table.getColumnCount(); i++) {
                TableCellRenderer renderer = table.getCellRenderer(j, i);
                Component comp = renderer.getTableCellRendererComponent(table, table.getValueAt(j, i), false, false, j, i);
                int cellHeight = comp.getPreferredSize().height;
                maxHeight = Math.max(cellHeight, maxHeight);
            }
            table.setRowHeight(j, maxHeight + 10); // Añadir un margen extra para la altura
        }

    // Ajustar la altura del encabezado de la tabla
        JTableHeader tableHeader = table.getTableHeader();
        TableCellRenderer headerRenderer = tableHeader.getDefaultRenderer();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            TableColumn column = columnModel.getColumn(i);
            Component headerComp = headerRenderer.getTableCellRendererComponent(table, column.getHeaderValue(), false, false, -1, i);
            int headerHeight = headerComp.getPreferredSize().height;
            tableHeader.setPreferredSize(new Dimension(tableHeader.getPreferredSize().width, headerHeight + 10)); // Añadir un margen extra
        }

        table.revalidate();
        table.repaint();
    }

    
    
    
}
