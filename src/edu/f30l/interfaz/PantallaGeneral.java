/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.f30l.interfaz;

import edu.f30l.decorator.IJugador;
import edu.f30l.decorator.decorador.JugadorMasPuntos;
import edu.f30l.entidades.Jugador;
import edu.f30l.memento.Memento;
import edu.f30l.memento.Protector;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author windows
 */
public class PantallaGeneral extends javax.swing.JFrame {
    IJugador jugador;
    IJugador copJug;
    String res;
    Boolean mejora = true;
    Protector caretaker = new Protector();
    JOptionPane leer = new JOptionPane();
    /**
     * Creates new form PantallaGeneral
     */
    public PantallaGeneral() {
        initComponents();
        jTxtMensajeGP.setVisible(false);
        //System.out.println(this.jugador.getPreguntas().size());
        
    }

    public void traerDatos (Jugador jugador){
        this.jugador = jugador;
        jTxtNombreUsuario.setText(this.jugador.getNombre());
        jTxtCanMonedas.setText(String.valueOf(this.jugador.getMonedas()));
        mostrarPregunta();
        
    }
    public void mostrarPregunta (){
        ArrayList preg = this.jugador.getPreguntas();
        int emp = numeroAleatorio(preg.size()/6);
        emp = emp*6 -6;

        jTxtPregunta.setText((String)preg.get(emp));
        this.res = (String)preg.get(emp+1);
        jRbtnUno.setText((String)preg.get(emp+2));
        jRbtnDos.setText((String)preg.get(emp+3));
        jRbtntres.setText((String)preg.get(emp+4));
        jRbtnCuatro.setText((String)preg.get(emp+5));
        
        actualizarBtnes();
    }
    
    public int numeroAleatorio (int numPre){
        return (int)(Math.random()*numPre+1);
    }
    
    public IJugador subirNivel (IJugador jug){
        this.jugador.setMonedas(this.jugador.getMonedas()-5);
        jBtnMejora.setEnabled(false);
        jTxtCanMonedas.setText(String.valueOf(this.jugador.getMonedas()));
        this.mejora = false;
        JugadorMasPuntos mejora = new JugadorMasPuntos(jug);
        return mejora;
    }
    
    public void enviarRes (){
        String seleccion;
        if (jRbtnUno.isSelected())
            seleccion = jRbtnUno.getText();
        else if (jRbtnDos.isSelected())
            seleccion = jRbtnDos.getText();
        else if (jRbtntres.isSelected())
            seleccion = jRbtntres.getText();
        else
            seleccion = jRbtnCuatro.getText();
        if (seleccion.equals(this.res)){

                this.jugador.ganar();
                jTxtCanMonedas.setText(String.valueOf(this.jugador.getMonedas()));
                jTxtMensajeGP.setText("Felicidades respuesta Correcta");
                jTxtMensajeGP.setBackground(Color.GREEN);
                jTxtMensajeGP.setVisible(true);
                //Thread.sleep(1000);
                //jTxtMensajeGP.setVisible(false);


            
        }else {

                jTxtMensajeGP.setText("Respuesta incorrecta");
                jTxtMensajeGP.setBackground(Color.RED);
                jTxtMensajeGP.setVisible(true);
                //Thread.sleep(1000);
                //jTxtMensajeGP.setVisible(false);
            

        }
        mostrarPregunta();
        //jTxtMensajeGP.setVisible(false);
    }
    
    public void actualizarBtnes (){
        if (mejora)
        jBtnMejora.setEnabled(this.jugador.getMonedas()>=5 ? true : false);
        jBtnApostar.setEnabled(this.jugador.getMonedas()>=2 ? true : false);
        jBtnAyuda.setEnabled(this.jugador.getMonedas()>=2 ? true : false);
    }
    
    public void apuesta (){
        this.jugador.apostar(2);
        jBtnApostar.setEnabled(this.jugador.getMonedas()>=2 ? true : false);
        jTxtCanMonedas.setText(String.valueOf(this.jugador.getMonedas()));
    }
    
    public void agregarMemento (){
        this.caretaker.agregarMemento(this.jugador.cloneCheckPoint());
        this.jugador.setCheckPoint(this.jugador.getCheckPoint()+1);
    }
    
    public void mostrarMementosGuardados (){
        ArrayList<Memento> ejemplo = this.caretaker.getHistorial();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Check Point");
        model.addColumn("Username");
        model.addColumn("Monedas");
        model.addColumn("Dificultad");
        for (int i = 0 ; i< ejemplo.size();i++){
            Object [] dat = new Object[4];
            dat[0] = i+1;
            dat[1] = ejemplo.get(i).getEstado().getNombre();
            dat[2] = ejemplo.get(i).getEstado().getMonedas();
            dat[3] = ejemplo.get(i).getEstado().getDificultad();
            
            model.addRow(dat);
        }
        VentanaHistorial ven = new VentanaHistorial();
        ven.setTable(model);
        ven.setVisible(true);
        //dispose();
    }
    
    public void cargarHistorialAnterior (){
        try {
            int numHistorial = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el número de check point que deseas"));
            this.jugador = this.jugador.restaurarEstado(this.caretaker.getMemento(numHistorial-1));
            actualizarBtnes();
            jTxtCanMonedas.setText(String.valueOf(this.jugador.getMonedas()));
            this.caretaker.eliminarPosterior(numHistorial-1);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Punto de guardado no valido");
        }
    }
    
    public void ayuda (){
        this.jugador.setMonedas(this.jugador.getMonedas()-2);
        jTxtCanMonedas.setText(String.valueOf(this.jugador.getMonedas()));
        actualizarBtnes();
        int ayu;
        int i=1;
        boolean sop1 = true;
        boolean sop2 = true;
        boolean sop3 = true;
        boolean sop4 = true;
        while (i<=2){
            ayu = numeroAleatorio(4);
            switch (ayu){
                case 1:
                    if ( !(this.res.equals(jRbtnUno.getText())) && sop1){
                        jRbtnUno.setEnabled(false);
                        sop1 = false;
                        i+=1;}
                    break;
                case 2:
                    if ( !(this.res.equals(jRbtnDos.getText()))&& sop2){
                        jRbtnDos.setEnabled(false);
                        sop2 = false;
                        i+=1;}
                    break;
                case 3:
                    if ( !(this.res.equals(jRbtntres.getText())) && sop3){
                        sop3 = false;
                        jRbtntres.setEnabled(false);
                        i+=1;}
                    break;
                case 4:
                    if ( !(this.res.equals(jRbtnCuatro.getText())) && sop4){
                        jRbtnCuatro.setEnabled(false);
                        sop4 = false;
                        i+=1;}
                    break;
                default:
                    break;
            }
            
        }
        jBtnAyuda.setEnabled(false);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BtnGroupRespuestas = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jTxtMonedas = new javax.swing.JLabel();
        jTxtCanMonedas = new javax.swing.JLabel();
        jTxtNombreUsuario = new javax.swing.JLabel();
        jBtnMejora = new javax.swing.JButton();
        jBtnApostar = new javax.swing.JButton();
        jBtnGuardar = new javax.swing.JButton();
        jBtnAyuda = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTxtPregunta = new javax.swing.JLabel();
        jRbtnUno = new javax.swing.JRadioButton();
        jRbtnDos = new javax.swing.JRadioButton();
        jRbtntres = new javax.swing.JRadioButton();
        jRbtnCuatro = new javax.swing.JRadioButton();
        jBtnEnviar = new javax.swing.JButton();
        jTxtMensajeGP = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(19, 56, 190));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTxtMonedas.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jTxtMonedas.setText("Monedas : ");

        jTxtCanMonedas.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jTxtCanMonedas.setText("0");

        jTxtNombreUsuario.setFont(new java.awt.Font("Roboto", 1, 26)); // NOI18N
        jTxtNombreUsuario.setForeground(new java.awt.Color(19, 51, 139));
        jTxtNombreUsuario.setText("Nombre Usuario");

        jBtnMejora.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jBtnMejora.setText("Mejora 5pts");
        jBtnMejora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnMejoraActionPerformed(evt);
            }
        });

        jBtnApostar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jBtnApostar.setText("Apostar 2pts");
        jBtnApostar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnApostarActionPerformed(evt);
            }
        });

        jBtnGuardar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jBtnGuardar.setText("Guardar");
        jBtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnGuardarActionPerformed(evt);
            }
        });

        jBtnAyuda.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jBtnAyuda.setText("Ayuda 2pts");
        jBtnAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAyudaActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jButton1.setText("Historial");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jButton2.setText("Cargar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTxtMonedas, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTxtCanMonedas, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTxtNombreUsuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jBtnMejora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnApostar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jBtnAyuda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBtnApostar)
                            .addComponent(jBtnAyuda)
                            .addComponent(jButton1)))
                    .addComponent(jTxtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBtnMejora)
                            .addComponent(jBtnGuardar)
                            .addComponent(jButton2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTxtCanMonedas, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jTxtMonedas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jTxtPregunta.setFont(new java.awt.Font("Roboto", 3, 18)); // NOI18N
        jTxtPregunta.setText("PREGUNTA");

        BtnGroupRespuestas.add(jRbtnUno);
        jRbtnUno.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jRbtnUno.setSelected(true);
        jRbtnUno.setText("jRadioButton1");

        BtnGroupRespuestas.add(jRbtnDos);
        jRbtnDos.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jRbtnDos.setText("jRadioButton2");

        BtnGroupRespuestas.add(jRbtntres);
        jRbtntres.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jRbtntres.setText("jRadioButton3");

        BtnGroupRespuestas.add(jRbtnCuatro);
        jRbtnCuatro.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jRbtnCuatro.setText("jRadioButton4");

        jBtnEnviar.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jBtnEnviar.setForeground(new java.awt.Color(19, 56, 190));
        jBtnEnviar.setText("Enviar");
        jBtnEnviar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEnviarActionPerformed(evt);
            }
        });

        jTxtMensajeGP.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jTxtMensajeGP.setForeground(new java.awt.Color(19, 56, 190));
        jTxtMensajeGP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jTxtMensajeGP.setText("jLabel5");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTxtPregunta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRbtnUno)
                            .addComponent(jRbtntres))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRbtnCuatro)
                            .addComponent(jRbtnDos))
                        .addGap(61, 61, 61)
                        .addComponent(jBtnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTxtMensajeGP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTxtMensajeGP, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTxtPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jRbtnUno)
                            .addGap(18, 18, 18)
                            .addComponent(jRbtntres))
                        .addComponent(jBtnEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jRbtnDos)
                        .addGap(18, 18, 18)
                        .addComponent(jRbtnCuatro)))
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAyudaActionPerformed
        ayuda();
    }//GEN-LAST:event_jBtnAyudaActionPerformed

    private void jBtnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEnviarActionPerformed
        enviarRes();
        actualizarBtnes();
        jRbtnUno.setEnabled(true);
        jRbtnDos.setEnabled(true);
        jRbtntres.setEnabled(true);
        jRbtnCuatro.setEnabled(true);
    }//GEN-LAST:event_jBtnEnviarActionPerformed

    private void jBtnApostarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnApostarActionPerformed
        apuesta();
    }//GEN-LAST:event_jBtnApostarActionPerformed

    private void jBtnMejoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnMejoraActionPerformed
        this.jugador = subirNivel(this.jugador);
    }//GEN-LAST:event_jBtnMejoraActionPerformed

    private void jBtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGuardarActionPerformed
        agregarMemento();
    }//GEN-LAST:event_jBtnGuardarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        mostrarMementosGuardados();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        cargarHistorialAnterior();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PantallaGeneral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaGeneral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaGeneral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaGeneral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaGeneral().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup BtnGroupRespuestas;
    private javax.swing.JButton jBtnApostar;
    private javax.swing.JButton jBtnAyuda;
    private javax.swing.JButton jBtnEnviar;
    private javax.swing.JButton jBtnGuardar;
    private javax.swing.JButton jBtnMejora;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRbtnCuatro;
    private javax.swing.JRadioButton jRbtnDos;
    private javax.swing.JRadioButton jRbtnUno;
    private javax.swing.JRadioButton jRbtntres;
    private javax.swing.JLabel jTxtCanMonedas;
    private javax.swing.JLabel jTxtMensajeGP;
    private javax.swing.JLabel jTxtMonedas;
    private javax.swing.JLabel jTxtNombreUsuario;
    private javax.swing.JLabel jTxtPregunta;
    // End of variables declaration//GEN-END:variables
}
