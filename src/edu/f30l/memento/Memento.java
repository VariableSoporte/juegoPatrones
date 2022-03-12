/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.f30l.memento;

import edu.f30l.decorator.IJugador;
import edu.f30l.decorator.decorador.JugadorMasPuntos;
import java.util.ArrayList;

/**
 *
 * @author windows
 */
public class Memento {
    String nombre;
    int checkPoint;
    int monedas = 0;
    String dificultad;
    ArrayList<String> preguntas;
    IJugador estado;
    public Memento (IJugador m){
        this.nombre = m.getNombre();
        this.checkPoint = m.getCheckPoint();
        this.monedas = m.getMonedas();
        this.dificultad = m.getDificultad();
        this.preguntas = m.getPreguntas();
        this.estado = m;
    }
    
    public IJugador getEstado (){
        return this.estado;
    }
    
    public void setEstado(){
        this.estado = new JugadorMasPuntos(this.estado);
    }
}
