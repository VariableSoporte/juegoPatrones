/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.f30l.decorator;

import edu.f30l.memento.Memento;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author windows
 */
public interface IJugador {
    
    public Memento cloneCheckPoint();
    public void ganar ();
    public void apostar (int i);
    public ArrayList getPreguntas();
    public String getNombre();
    public void setNombre(String nombre);
    public int getCheckPoint();
    public void setCheckPoint(int checkPoint);
    public int getMonedas();
    public void setMonedas(int monedas);
    public String getDificultad();
    public void setDificultad(String dificultad);
    public void setPreguntas (ArrayList preg);
    public IJugador restaurarEstado (Memento m);

}
