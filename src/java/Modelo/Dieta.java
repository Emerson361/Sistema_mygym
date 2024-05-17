/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Armando
 */
public class Dieta {
     int idDieta;
     String nombre;
     String tipoDieta;
     String duracion;
     String suplemento;
     int idNutricionista;
     String nutricionista;

    // Constructor vacío
    public Dieta() {
    }

    // Constructor con todos los campos
    public Dieta(int idDieta, String nombre, String tipoDieta, String duracion, String suplemento, int idNutricionista, String nutricionista) {
        this.idDieta = idDieta;
        this.nombre = nombre;
        this.tipoDieta = tipoDieta;
        this.duracion = duracion;
        this.suplemento = suplemento;
        this.idNutricionista = idNutricionista;
        this.nutricionista = nutricionista;
    }

    // Getters y setters
    public int getIdDieta() {
        return idDieta;
    }

    public void setIdDieta(int idDieta) {
        this.idDieta = idDieta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoDieta() {
        return tipoDieta;
    }

    public void setTipoDieta(String tipoDieta) {
        this.tipoDieta = tipoDieta;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getSuplemento() {
        return suplemento;
    }

    public void setSuplemento(String suplemento) {
        this.suplemento = suplemento;
    }

    public int getIdNutricionista() {
        return idNutricionista;
    }

    public void setIdNutricionista(int idNutricionista) {
        this.idNutricionista = idNutricionista;
    }
  
     public String getNutricionista() {
        return nutricionista;
    }

    public void setNutricionista(String nutricionista) {
        this.nutricionista = nutricionista;
    }


}
