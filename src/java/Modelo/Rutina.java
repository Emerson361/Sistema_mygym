/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import Modelo.Entrenador;
/**
 *
 * @author EMERSOM APAZA
 */
public class Rutina {
    int id;
    String nombre;
    String tiporutina;
    String descripcion;
    String dificultad;
    String frecuencia;
    int entrenador;

    public Rutina() {
    }

    public Rutina(int id, String nombre, String tiporutina, String descripcion, String dificultad, String frecuencia, int entrenador) {
        this.id = id;
        this.nombre = nombre;
        this.tiporutina = tiporutina;
        this.descripcion = descripcion;
        this.dificultad = dificultad;
        this.frecuencia = frecuencia;
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

    public String getTiporutina() {
        return tiporutina;
    }

    public void setTiporutina(String tiporutina) {
        this.tiporutina = tiporutina;
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

    public int getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(int entrenador) {
        this.entrenador = entrenador;
    }

    
    
    
}
