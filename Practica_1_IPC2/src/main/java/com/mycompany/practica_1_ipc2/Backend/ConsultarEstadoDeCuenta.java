/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica_1_ipc2.Backend;

/**
 *
 * @author gabrielh
 */
import java.awt.Component;
import java.awt.Dimension;
import java.math.BigDecimal;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.math.BigDecimal;
import java.sql.*;
import java.text.DecimalFormat;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class ConsultarEstadoDeCuenta {
    private static final String URL_MYSQL = "jdbc:mysql://localhost:3306/control_entidad_bancaria";
    private static final String USER = "root";
    private static final String PASSWORD = "1007";
    private Connection connection;

    public ConsultarEstadoDeCuenta() {
        try {
            connection = DriverManager.getConnection(URL_MYSQL, USER, PASSWORD);
            System.out.println("Conexión establecida con éxito.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

public void obtenerDatos(JTable table, JCheckBox jcbNoTarjeta, JCheckBox jcbTipo, JCheckBox jcbSaldo, JCheckBox jcbInteres,
                          JTextField jtfNumeroTarjeta, JComboBox<String> jcbTipos, JTextField jtfMontoSaldo, JTextField jtfMontoInteres) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT t.numero_tarjeta, s.tipo, s.nombre, s.direccion, ");
        query.append("m.fecha_movimiento, m.tipo_movimiento, m.descripcion, m.establecimiento, m.monto, ");
        query.append("(SELECT SUM(m2.monto) FROM movimientos m2 WHERE m2.numero_tarjeta = t.numero_tarjeta) AS monto_total, ");
        query.append("t.saldo, ");
        query.append("t.saldo * CASE s.tipo ");
        query.append("WHEN 'Nacional' THEN 0.012 ");
        query.append("WHEN 'Regional' THEN 0.023 ");
        query.append("WHEN 'Internacional' THEN 0.0375 ");
        query.append("ELSE 0 ");
        query.append("END AS intereses ");
        query.append("FROM tarjetas t ");
        query.append("INNER JOIN solicitud_nueva s ON t.numero_solicitud = s.numero_solicitud ");
        query.append("LEFT JOIN movimientos m ON t.numero_tarjeta = m.numero_tarjeta "); // Cambio a LEFT JOIN
        query.append("WHERE 1=1 ");
        
        // Agregar filtros según selección
        if (jcbNoTarjeta.isSelected() && !jtfNumeroTarjeta.getText().trim().isEmpty()) {
            query.append("AND t.numero_tarjeta = ? ");
        }
        if (jcbTipo.isSelected() && jcbTipos.getSelectedIndex() > 0) { // "Ninguno" está en el índice 0
            query.append("AND s.tipo = ? ");
        }
        if (jcbSaldo.isSelected() && !jtfMontoSaldo.getText().trim().isEmpty()) {
            query.append("AND t.saldo > ? ");
        }
        if (jcbInteres.isSelected() && !jtfMontoInteres.getText().trim().isEmpty()) {
            query.append("AND t.saldo * CASE s.tipo ");
            query.append("WHEN 'Nacional' THEN 0.012 ");
            query.append("WHEN 'Regional' THEN 0.023 ");
            query.append("WHEN 'Internacional' THEN 0.0375 ");
            query.append("ELSE 0 ");
            query.append("END > ? ");
        }

        query.append("ORDER BY m.fecha_movimiento");

        System.out.println("Consulta SQL: " + query.toString());

        try (PreparedStatement ps = connection.prepareStatement(query.toString())) {
            int index = 1;
            
            if (jcbNoTarjeta.isSelected() && !jtfNumeroTarjeta.getText().trim().isEmpty()) {
                ps.setString(index++, jtfNumeroTarjeta.getText().trim());
            }
            if (jcbTipo.isSelected() && jcbTipos.getSelectedIndex() > 0) { // "Ninguno" está en el índice 0
                ps.setString(index++, jcbTipos.getSelectedItem().toString());
            }
            if (jcbSaldo.isSelected() && !jtfMontoSaldo.getText().trim().isEmpty()) {
                ps.setBigDecimal(index++, new BigDecimal(jtfMontoSaldo.getText().trim()));
            }
            if (jcbInteres.isSelected() && !jtfMontoInteres.getText().trim().isEmpty()) {
                ps.setBigDecimal(index++, new BigDecimal(jtfMontoInteres.getText().trim()));
            }

            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Limpiar tabla antes de agregar los nuevos datos

            // Crear una instancia de DecimalFormat para formatear los intereses
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            
            int rowCount = 0; // Para contar el número de filas recuperadas
            while (rs.next()) {
                // Formatear el valor de interés a dos decimales
                String formattedInterest = decimalFormat.format(rs.getBigDecimal("intereses"));

                model.addRow(new Object[]{
                    rs.getString("numero_tarjeta"),
                    rs.getString("tipo"),
                    rs.getString("nombre"),
                    rs.getString("direccion"),
                    rs.getDate("fecha_movimiento") != null ? rs.getDate("fecha_movimiento") : "", // Manejo de NULL
                    rs.getString("tipo_movimiento") != null ? rs.getString("tipo_movimiento") : "", // Manejo de NULL
                    rs.getString("descripcion") != null ? rs.getString("descripcion") : "", // Manejo de NULL
                    rs.getString("establecimiento") != null ? rs.getString("establecimiento") : "", // Manejo de NULL
                    rs.getBigDecimal("monto") != null ? rs.getBigDecimal("monto") : BigDecimal.ZERO, // Manejo de NULL
                    rs.getBigDecimal("monto_total") != null ? rs.getBigDecimal("monto_total") : BigDecimal.ZERO, // Manejo de NULL
                    formattedInterest, // Interés formateado a dos decimales
                    rs.getBigDecimal("saldo") != null ? rs.getBigDecimal("saldo") : BigDecimal.ZERO // Manejo de NULL
                });
                rowCount++;
            }

            System.out.println("Total de filas recuperadas: " + rowCount); // Depuración

            if (rowCount == 0) {
                JOptionPane.showMessageDialog(null, "No se encontraron datos que coincidan con los filtros seleccionados.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }

            ajustarAnchoYAltura(table);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al consultar la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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






/*private void configurarTabla(JTable table) {
    JScrollPane scrollPane = new JScrollPane(table);
    table.setFillsViewportHeight(true); // Asegura que la tabla llene el área visible del JScrollPane
    table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
}*/

}



