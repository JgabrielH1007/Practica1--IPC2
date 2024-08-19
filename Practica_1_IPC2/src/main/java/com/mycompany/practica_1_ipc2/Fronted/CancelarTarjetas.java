/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.mycompany.practica_1_ipc2.Fronted;

import com.mycompany.practica_1_ipc2.Backend.Administrador;
import javax.swing.JOptionPane;

/**
 *
 * @author gabrielh
 */
public class CancelarTarjetas extends javax.swing.JInternalFrame {
    private String numeroTarjeta;
    /**
     * Creates new form CancelarTarjetas
     */
    public CancelarTarjetas() {
        super("CANCELACION TARJETAS", false, true, false, true);
        initComponents();
        
        pack();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jtfNumeroTarjeta = new javax.swing.JTextField();
        jbtAceptar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        jLabel1.setText("Ingrese número de tarjeta que desea cancelar: ");

        jtfNumeroTarjeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfNumeroTarjetaActionPerformed(evt);
            }
        });

        jbtAceptar.setText("Aceptar");
        jbtAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAceptarActionPerformed(evt);
            }
        });

        jLabel2.setText("NOTA: tarjeta será desactivada permanentemente.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jtfNumeroTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(56, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jbtAceptar)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jtfNumeroTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jbtAceptar)
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAceptarActionPerformed
        // TODO add your handling code here:
        manejoErrorNoTarjeta();
        Administrador admin = new Administrador();
        admin.actualizarEstadoTarjeta(numeroTarjeta);
        if (this != null) {
                this.dispose();
            }
    }//GEN-LAST:event_jbtAceptarActionPerformed

    private void jtfNumeroTarjetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfNumeroTarjetaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfNumeroTarjetaActionPerformed

    private void manejoErrorNoTarjeta(){
        numeroTarjeta = jtfNumeroTarjeta.getText().trim(); // Obtener el texto y eliminar espacios en blanco extra

    // Verificar si el texto tiene exactamente 19 caracteres (incluidos los espacios)
        if (numeroTarjeta.length() != 19) {
        // Mostrar mensaje de error
            JOptionPane.showMessageDialog(null, 
            "El número de tarjeta debe tener exactamente 19 caracteres, incluyendo espacios.", 
            "Error en Número de Tarjeta", 
            JOptionPane.ERROR_MESSAGE);
            jtfNumeroTarjeta.setText("");
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton jbtAceptar;
    private javax.swing.JTextField jtfNumeroTarjeta;
    // End of variables declaration//GEN-END:variables
}
