/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author EMERSOM APAZA
 */
public class Estado {
    int idestado;
    String detalle_estado;

    public Estado() {
    }

    public Estado(int idestado, String detalle_estado) {
        this.idestado = idestado;
        this.detalle_estado = detalle_estado;
    }

    public int getIdestado() {
        return idestado;
    }

    public void setIdestado(int idestado) {
        this.idestado = idestado;
    }

    public String getDetalle_estado() {
        return detalle_estado;
    }

    public void setDetalle_estado(String detalle_estado) {
        this.detalle_estado = detalle_estado;
    }
    
    
}
