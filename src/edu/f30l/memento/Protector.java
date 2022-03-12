/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.f30l.memento;

import java.util.ArrayList;

/**
 *
 * @author windows
 */
public class Protector {
    ArrayList<Memento> historial = new ArrayList<>();
    
    public void agregarMemento ( Memento m){
        historial.add(m);
    }
    
    public Memento getMemento (int i){
        return historial.get(i);
    }
    
    public ArrayList<Memento> getHistorial (){
        return this.historial;
    }
    
    public void eliminarPosterior (int j){
        int num = this.historial.size();
        for (int i = j ; i< num;i++){
            historial.remove(j);
        }
    }
}
