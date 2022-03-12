/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.f30l.entidades;

import edu.f30l.decorator.IJugador;
import edu.f30l.memento.Memento;
import java.util.ArrayList;


/**
 *
 * @author windows
 */
public class Jugador implements IJugador{
    String nombre;
    int checkPoint;
    int monedas = 0;
    String dificultad;
    ArrayList<String> preguntas;

    public Jugador(String nombre, int checkPoint, int monedas, String dificultad, ArrayList preguntas) {
        this.nombre = nombre;
        this.checkPoint = checkPoint;
        this.monedas = monedas;
        this.dificultad = dificultad;
        this.preguntas = preguntas;
    }

    public Jugador() {
    }
    
    @Override
    public Memento cloneCheckPoint(){
        Jugador clon = new Jugador ();
        clon.setNombre(this.getNombre());
        clon.setCheckPoint(this.getCheckPoint()+1);
        clon.setMonedas(this.getMonedas());
        clon.setDificultad(this.getDificultad());
        clon.setPreguntas(this.getPreguntas());
        Memento m = new Memento(clon);
        return m;
    }
    
    @Override
    public void ganar (){
        this.setMonedas(this.getMonedas()+1);
    }
    
    @Override
    public void setPreguntas(ArrayList preg ){
        this.preguntas = preg;
    }
    
    @Override
    public void apostar (int i){
        int random = (int)(Math.random()*10+1);
        if ( i == random )
            this.setMonedas(this.getMonedas()+(i*3));
        else
            this.setMonedas(this.getMonedas()-i);
    }

    @Override
    public ArrayList getPreguntas() {
        return preguntas;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int getCheckPoint() {
        return checkPoint;
    }

    @Override
    public void setCheckPoint(int checkPoint) {
        this.checkPoint = checkPoint;
    }

    @Override
    public int getMonedas() {
        return monedas;
    }

    @Override
    public void setMonedas(int monedas) {
        this.monedas = monedas;
    }

    @Override
    public String getDificultad() {
        return dificultad;
    }

    @Override
    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    @Override
    public IJugador restaurarEstado(Memento m) {
        return m.getEstado();
    }



}
