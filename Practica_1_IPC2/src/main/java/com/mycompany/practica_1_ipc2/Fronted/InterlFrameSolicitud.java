/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.mycompany.practica_1_ipc2.Fronted;

import com.mycompany.practica_1_ipc2.Backend.Solicitudes;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author gabrielh
 */
public class InterlFrameSolicitud extends javax.swing.JInternalFrame {
    
 
    private String tipoSeleccionado;

    
    /**
     * Creates new form InterlFrameSolicitud
     */
    public InterlFrameSolicitud() {
        super("SOLICITUDES", false, true, false, true);
        initComponents();
         jtfFecha.setEditable(false); // Bloquear el JTextField

        // Obtener la fecha actual y formatearla
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFormateada = fechaActual.format(formatter);

        // Establecer la fecha formateada en el JTextField
        jtfFecha.setText(fechaFormateada);
        tipoSeleccionado = (String) jcbTipoTarjeta.getSelectedItem();
        pack();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jtfNombre = new javax.swing.JTextField();
        jtfFecha = new javax.swing.JTextField();
        jtfDireccion = new javax.swing.JTextField();
        jtfSalario = new javax.swing.JTextField();
        jcbTipoTarjeta = new javax.swing.JComboBox<>();
        jbtGuardarSolicitud = new javax.swing.JButton();

        jLabel3.setText("Ingresar nombre: ");

        jLabel4.setText("Fecha:");

        jLabel5.setText("Tipo de tarjeta:");

        jLabel6.setText("Salario:");

        jLabel7.setText("Dirección:");

        jtfNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfNombreActionPerformed(evt);
            }
        });
        jtfNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfNombreKeyTyped(evt);
            }
        });

        jtfFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfFechaActionPerformed(evt);
            }
        });

        jtfDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfDireccionActionPerformed(evt);
            }
        });
        jtfDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfDireccionKeyTyped(evt);
            }
        });

        jtfSalario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfSalarioActionPerformed(evt);
            }
        });

        jcbTipoTarjeta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nacional", "Internacional", "Regional" }));
        jcbTipoTarjeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbTipoTarjetaActionPerformed(evt);
            }
        });

        jbtGuardarSolicitud.setText("Guardar");
        jbtGuardarSolicitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGuardarSolicitudActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbTipoTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfSalario, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(104, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtGuardarSolicitud)
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtfFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jcbTipoTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jtfSalario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jtfDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jbtGuardarSolicitud)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfNombreActionPerformed

    private void jtfFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfFechaActionPerformed

    private void jtfDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfDireccionActionPerformed

    private void jcbTipoTarjetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbTipoTarjetaActionPerformed
        // TODO add your handling code here:
        tipoSeleccionado = (String) jcbTipoTarjeta.getSelectedItem();
       

    }//GEN-LAST:event_jcbTipoTarjetaActionPerformed

    private void jtfNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfNombreKeyTyped
        // TODO add your handling code here:
        if(jtfNombre.getText().length() >= 100) {
            evt.consume();
        }
    }//GEN-LAST:event_jtfNombreKeyTyped

    private void jtfDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfDireccionKeyTyped
        // TODO add your handling code here:
        if(jtfDireccion.getText().length() >= 150) {
            evt.consume();
        }
    }//GEN-LAST:event_jtfDireccionKeyTyped

    private void jtfSalarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfSalarioActionPerformed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_jtfSalarioActionPerformed

    private void jbtGuardarSolicitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGuardarSolicitudActionPerformed
      // Obtener el texto de los JTextFields
    String direccion = jtfDireccion.getText().trim();
    String fecha = jtfFecha.getText().trim();
    String nombre = jtfNombre.getText().trim();
    String salario = jtfSalario.getText().trim();
    String tipo = null;

    // Verificar si alguno de los JTextFields está vacío
    if (direccion.isEmpty() || fecha.isEmpty() || nombre.isEmpty() || salario.isEmpty()) {
        // Mostrar un mensaje de error si alguno está vacío
        JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
    } else {
        try {
            // Convertir el salario a double
           double valorSalario = Double.parseDouble(salario);

         
            
             switch (tipoSeleccionado) {
                case "Nacional":
                   tipo = "Nacional";
                break;
                case "Internacional":
                   tipo = "Internacional";
                break;
                case "Regional":
                    tipo = "Regional";
                break;
     
                }
            
            // Imprimir para verificar
            Solicitudes solicitud = new Solicitudes(this, nombre, direccion, tipo,fecha, valorSalario);

            // Aquí puedes continuar con la lógica de guardado o procesamiento
        } catch (NumberFormatException e) {
            // Mostrar un mensaje de error si el formato del salario es incorrecto
            JOptionPane.showMessageDialog(null, "El formato del salario es incorrecto. Por favor, ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_jbtGuardarSolicitudActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JButton jbtGuardarSolicitud;
    private javax.swing.JComboBox<String> jcbTipoTarjeta;
    private javax.swing.JTextField jtfDireccion;
    private javax.swing.JTextField jtfFecha;
    private javax.swing.JTextField jtfNombre;
    private javax.swing.JTextField jtfSalario;
    // End of variables declaration//GEN-END:variables
}
