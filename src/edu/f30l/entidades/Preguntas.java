/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.f30l.entidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author windows
 */
public class Preguntas {
    ArrayList<String> preguntas;

    
    public Preguntas(int dificultad) {
        if (dificultad == 1)
            preguntas = preguntasFaciles();
        else if (dificultad == 2)
            preguntas = preguntasMedias();
        else
            preguntas = preguntasNormales();
    }

    public ArrayList getPreguntas() {
        return preguntas;
    }

    
    private ArrayList preguntasFaciles (){
        
        ArrayList p1 = new ArrayList();
        p1.add("Como se llama el tipo de dato que contiene un valor Verdadero o Falso");
        p1.add("Boolean");
        p1.add("int");
        p1.add("return");
        p1.add("Boolean");
        p1.add("Integer");
        
        p1.add("Como se llama el tipo de dato que retorna un numero entero");
        p1.add("int");
        p1.add("return");
        p1.add("Boolean");
        p1.add("int");
        p1.add("Integer");
        
        p1.add("¿Qué es el Java?");
        p1.add("Lenguaje de programación.");
        p1.add("Algoritmo.");
        p1.add("Programa.");
        p1.add("Lenguaje de programación.");
        p1.add("Pseudocódigo.");
        
        p1.add("¿Qué es el Java?");
        p1.add("Lenguaje de programación.");
        p1.add("Algoritmo.");
        p1.add("Programa.");
        p1.add("Lenguaje de programación.");
        p1.add("Pseudocódigo.");
        
        p1.add("Tiene su propio estado, comportamiento y definición");
        p1.add("Un objeto");
        p1.add("Una clase");
        p1.add("Un lista");
        p1.add("Un nodo");
        p1.add("Un objeto");
        
        p1.add("Es una característica del objeto y guarda todo los datos ocultos");
        p1.add("Encapsulación");
        p1.add("Polimorfismo");
        p1.add("Encapsulación");
        p1.add("Objeto");
        p1.add("Herencia");
        
        p1.add("El esquema de datos UNION, se logra mediante");
        p1.add("Registrons con variantes");
        p1.add("Polimorfismo");
        p1.add("Registrons con variantes");
        p1.add("Objeto");
        p1.add("Herencia");
        
        p1.add("Que es la asignación de un valor que se le da a una subclase");
        p1.add("Polimorfismo");
        p1.add("Polimorfismo");
        p1.add("Encapsulación");
        p1.add("Objeto");
        p1.add("Herencia");
        
        p1.add("Como se llama aquella clase que no puede ser instanciada");
        p1.add("Abstracta");
        p1.add("Abstracta");
        p1.add("final");
        p1.add("static");
        p1.add("volatile");
        
        p1.add("Que tipo de operador es el que toma tres argumentos");
        p1.add("Ternario");
        p1.add("Binarios");
        p1.add("Lógicos");
        p1.add("Ternario");
        p1.add("Aritméticos");
        
        return p1;
    }
    
    private ArrayList preguntasMedias(){
        ArrayList p1 = new ArrayList();
        p1.add("Cuales son los tipos de datos numéricos enteros");
        p1.add("byte, short, int, long");
        p1.add("float, double");
        p1.add("bool, char");
        p1.add("byte, short, int, long");
        p1.add("string");
        
        p1.add("Identifique cuál de todos es un Patrón Estructural");
        p1.add("Adapter");
        p1.add("Abstract Factory");
        p1.add("Factory Method");
        p1.add("Builder");
        p1.add("Adapter");
        
        p1.add("Identifique cuál de todos es un Patrón Comportamiento");
        p1.add("Memento");
        p1.add("Adapter");
        p1.add("Memento");
        p1.add("Prototype");
        p1.add("Decorator");
        
        p1.add("Como se llama a una librería");
        p1.add("import ...");
        p1.add("#import ...");
        p1.add("import ...");
        p1.add("include ...");
        p1.add("#include...");
        
        p1.add("Cuál no es un modificador de acceso");
        p1.add("int");
        p1.add("public");
        p1.add("int");
        p1.add("protected");
        p1.add("private");
        
        p1.add("¿Qué elementos crees que definen a un objeto?");
        p1.add("Atributos y sus métodos");
        p1.add("Tipo");
        p1.add("Cardinalidad");
        p1.add("Atributos y sus métodos");
        p1.add("Interfaz");
        
        p1.add("Cual de estas son palabras reservadas de java");
        p1.add("cast future generic");
        p1.add("continue for Boolean");
        p1.add("default goto null");
        p1.add("Implements Public Extends");
        p1.add("cast future generic");
        
        p1.add("Cual de estos son operadores de java");
        p1.add("*=/");
        p1.add("() . ;");
        p1.add("*=/");
        p1.add("%/");
        p1.add("/** * /");
        
        p1.add("Es la unidad básica de almacenamiento en un programa");
        p1.add("variable");
        p1.add("variable");
        p1.add("Constantes");
        p1.add("Lenaguaje de programación");
        p1.add("Storyboard");
        
        p1.add("Son descripciones gráficas de algoritmos");
        p1.add("Diagramas de flujo");
        p1.add("Storyboard");
        p1.add("Diagramas de flujo");
        p1.add("Pseudocódigo");
        p1.add("Programación");
       
        return p1;
    }
    
    private ArrayList preguntasNormales(){
        ArrayList p1 = new ArrayList();
        p1.add("Dato cuyo valor no cambia durante la ejecución del programa");
        p1.add("Constante");
        p1.add("Variable");
        p1.add("Constante");
        p1.add("Byte");
        p1.add("Dato");
        
        p1.add("Escoja el patrón que también es conocido como peso mosca o peso ligero");
        p1.add("Flyweight");
        p1.add("Composite");
        p1.add("Proxy");
        p1.add("Flyweight");
        p1.add("Decorator");
        
        p1.add("¿Cómo deben estar las clases public para acceder a ellas desde otras clases?");
        p1.add("Importadas");
        p1.add("Declaradas");
        p1.add("Importadas");
        p1.add("Directas");
        p1.add("Por herencia");
        
        p1.add("¿Cómo se llama la clase que termina una cadena de herencia?");
        p1.add("final");
        p1.add("Public");
        p1.add("Abstract");
        p1.add("Public");
        p1.add("final");
        
        p1.add("¿Cómo se especifica las clases que tiene una súper clase?");
        p1.add("Extends");
        p1.add("implements");
        p1.add("Interface");
        p1.add("Extends");
        p1.add("Object");
        
        p1.add("Proceso de diseñar, codificar, depurar y mantener el código fuente");
        p1.add("Programación");
        p1.add("Programación");
        p1.add("Compilación");
        p1.add("Depuración");
        p1.add("Software");
        
        p1.add("La programación a la defensiva recomienda usar");
        p1.add("Subprogramas");
        p1.add("Código fuente");
        p1.add("Programador");
        p1.add("Subprogramas");
        p1.add("Informática");
        
        p1.add("Conjunto prescrito de instrucciones o reglas bien definidas");
        p1.add("Algoritmo");
        p1.add("Ciclos");
        p1.add("Estructuras");
        p1.add("Algoritmo");
        p1.add("Clases");
        
        p1.add("Mezcla de lenguaje natural con lenguajes de programación");
        p1.add("Pseudocódigo");
        p1.add("Diagramas de flujo");
        p1.add("Variables");
        p1.add("Constantes");
        p1.add("Pseudocódigo");
        
        p1.add("Un byte a cuántos bits equivale");
        p1.add("8");
        p1.add("16");
        p1.add("36");
        p1.add("10");
        p1.add("8");
        
        return p1;
    }
}
