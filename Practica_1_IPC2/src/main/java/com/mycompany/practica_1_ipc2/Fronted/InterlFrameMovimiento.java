/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.mycompany.practica_1_ipc2.Fronted;

import com.mycompany.practica_1_ipc2.Backend.Administrador;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author gabrielh
 */
public class InterlFrameMovimiento extends javax.swing.JInternalFrame {

    private String movimientoSeleccionado;
    private String numeroTarjeta;
    
    public InterlFrameMovimiento() {
        super("MOVIMIENTOS", false, true, false, true);
        initComponents();
        jtfFechaMovimiento.setEditable(false); // Bloquear el JTextField

        // Obtener la fecha actual y formatearla
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String fechaFormateada = fechaActual.format(formatter);
        jtfFechaMovimiento.setText(fechaFormateada);
        movimientoSeleccionado = (String) jcbMovimiento.getSelectedItem();
        pack();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jtfNoTarjeta = new javax.swing.JTextField();
        jcbMovimiento = new javax.swing.JComboBox<>();
        jtfFechaMovimiento = new javax.swing.JTextField();
        jtfDescripcion = new javax.swing.JTextField();
        jtfEstablecimiento = new javax.swing.JTextField();
        jtfMonto = new javax.swing.JTextField();
        jbtGuardar = new javax.swing.JButton();

        jLabel1.setText("No. Tarjeta:");

        jLabel2.setText("Fecha:");

        jLabel3.setText("Tipo:");

        jLabel4.setText("Descripción:");

        jLabel5.setText("Establecimiento:");

        jLabel6.setText("Monto:");

        jtfNoTarjeta.setToolTipText("Utilizar formato: xxxx xxxx xxxx xxxx");
        jtfNoTarjeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfNoTarjetaActionPerformed(evt);
            }
        });

        jcbMovimiento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cargo", "Abono" }));
        jcbMovimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbMovimientoActionPerformed(evt);
            }
        });

        jtfDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfDescripcionActionPerformed(evt);
            }
        });
        jtfDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfDescripcionKeyTyped(evt);
            }
        });

        jbtGuardar.setText("Guardar");
        jbtGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfEstablecimiento)
                            .addComponent(jtfMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
                        .addComponent(jbtGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(44, 44, 44)
                                .addComponent(jtfDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jcbMovimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel2))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(51, 51, 51)
                                            .addComponent(jtfFechaMovimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jtfNoTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtfNoTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jcbMovimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtfFechaMovimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtfDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtfEstablecimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jtfMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtGuardar))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfDescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfDescripcionKeyTyped
        // TODO add your handling code here:
        if(jtfDescripcion.getText().length() >= 200)
    {
        evt.consume();
    }
    }//GEN-LAST:event_jtfDescripcionKeyTyped

    private void jtfNoTarjetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfNoTarjetaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfNoTarjetaActionPerformed

    private void jbtGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGuardarActionPerformed
        manejoErrorNoTarjeta();
        String descripcion = jtfDescripcion.getText().trim();
        String fecha = jtfFechaMovimiento.getText().trim();
        String codEstablecimiento = jtfEstablecimiento.getText().trim();
        String monto = jtfMonto.getText().trim();
        String movimiento = null;

    // Verificar si alguno de los JTextFields está vacío
        if (numeroTarjeta.isEmpty() || descripcion.isEmpty() || fecha.isEmpty() || codEstablecimiento.isEmpty() || monto.isEmpty()) {
        // Mostrar un mensaje de error si alguno está vacío
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
            // Convertir el salario a double
                double valorMonto = Double.parseDouble(monto);

                switch (movimientoSeleccionado) {
                    case "Cargo":
                        movimiento = "Cargo";
                    break;
                    case "Abono":
                        movimiento = "Abono";
                    break;
     
                }
            
            // Imprimir para verificar
                Administrador admin = new Administrador();
                admin.establecerMovimiento(numeroTarjeta, descripcion, fecha, codEstablecimiento, monto, movimiento);
            
                //JOptionPane.showMessageDialog(null, "Número de solicitud insertado: " /*solicitud.getNumeroSolicitud()*/, "Éxito", JOptionPane.INFORMATION_MESSAGE);
                if (this != null) {
                    this.dispose();
                }
            } catch (NumberFormatException e) {
            // Mostrar un mensaje de error si el formato del monto es incorrecto
                JOptionPane.showMessageDialog(null, "El formato del salario es incorrecto. Por favor, ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
         }
        }
    }//GEN-LAST:event_jbtGuardarActionPerformed

    private void jcbMovimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbMovimientoActionPerformed
        // TODO add your handling code here:
       movimientoSeleccionado = (String) jcbMovimiento.getSelectedItem();

    }//GEN-LAST:event_jcbMovimientoActionPerformed

    private void jtfDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfDescripcionActionPerformed
    
    private void manejoErrorNoTarjeta(){
        numeroTarjeta = jtfNoTarjeta.getText().trim(); // Obtener el texto y eliminar espacios en blanco extra

    // Verificar si el texto tiene exactamente 19 caracteres (incluidos los espacios)
        if (numeroTarjeta.length() != 19) {
        // Mostrar mensaje de error
            JOptionPane.showMessageDialog(null, 
            "El número de tarjeta debe tener exactamente 19 caracteres, incluyendo espacios.", 
            "Error en Número de Tarjeta", 
            JOptionPane.ERROR_MESSAGE);
            jtfNoTarjeta.setText("");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton jbtGuardar;
    private javax.swing.JComboBox<String> jcbMovimiento;
    private javax.swing.JTextField jtfDescripcion;
    private javax.swing.JTextField jtfEstablecimiento;
    private javax.swing.JTextField jtfFechaMovimiento;
    private javax.swing.JTextField jtfMonto;
    private javax.swing.JTextField jtfNoTarjeta;
    // End of variables declaration//GEN-END:variables
}
