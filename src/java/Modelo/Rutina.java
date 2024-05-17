/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
/**
 *
 * @author EMERSOM APAZA
 */
public class Rutina {
    int id;
    String nombre;
    String descripcion;
    String dificultad;
    String frecuencia;
    int identrenador;
    String entrenador;

    public Rutina() {
    }
    
    public Rutina(int id, String nombre, String descripcion, String dificultad, String frecuencia, int identrenador, String entrenador) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dificultad = dificultad;
        this.frecuencia = frecuencia;
        this.identrenador = identrenador;
        this.entrenador = entrenador;
    }

    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public int getIdentrenador() {
        return identrenador;
    }

    public void setIdentrenador(int identrenador) {
        this.identrenador = identrenador;
    }

    public String getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(String entrenador) {
        this.entrenador = entrenador;
    }

    

    
}
