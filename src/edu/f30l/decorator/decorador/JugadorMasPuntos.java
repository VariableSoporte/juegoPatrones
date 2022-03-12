/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.f30l.decorator.decorador;

import edu.f30l.decorator.IJugador;
import edu.f30l.entidades.Jugador;
import edu.f30l.memento.Memento;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author windows
 */
public class JugadorMasPuntos extends JugadorDecorador {
    
    public JugadorMasPuntos(IJugador jugador) {
        super(jugador);
    }

    @Override
    public void ganar() {
        this.setMonedas(this.getMonedas()+2);
    }

    @Override
    public void apostar(int i) {
        this.jugador.apostar(i+1);
    }

    @Override
    public ArrayList getPreguntas() {
        return this.jugador.getPreguntas();
    }

    @Override
    public String getNombre() {
        return this.jugador.getNombre();
    }

    @Override
    public void setNombre(String nombre) {
        this.jugador.setNombre(nombre);
    }

    @Override
    public int getCheckPoint() {
        return this.jugador.getCheckPoint();
    }

    @Override
    public void setCheckPoint(int checkPoint) {
        this.jugador.setCheckPoint(checkPoint);
    }

    @Override
    public int getMonedas() {
        return this.jugador.getMonedas();
    }

    @Override
    public void setMonedas(int monedas) {
        this.jugador.setMonedas(monedas);
    }

    @Override
    public String getDificultad() { 
        return this.jugador.getDificultad();
    }

    @Override
    public void setDificultad(String dificultad) {
        this.jugador.setDificultad(dificultad);
    }

    @Override
    public Memento cloneCheckPoint() {
        Memento clon = this.jugador.cloneCheckPoint();
        clon.setEstado();
        return clon;
    }

    @Override
    public void setPreguntas(ArrayList preg) {
        this.jugador.setPreguntas(preg);
    }

    @Override
    public IJugador restaurarEstado(Memento m) {
        return this.jugador.restaurarEstado(m);
    }
    
}
