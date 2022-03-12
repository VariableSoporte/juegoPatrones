/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.f30l.decorator.decorador;

import edu.f30l.decorator.IJugador;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author windows
 */
public abstract class JugadorDecorador implements IJugador{
    IJugador jugador;

    public JugadorDecorador(IJugador jugador) {
        this.jugador = jugador;
    }

}
