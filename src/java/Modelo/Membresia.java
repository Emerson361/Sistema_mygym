/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author EMERSOM APAZA
 */
public class Membresia {
    int id;
    String tipoMembresia;
    String duracion;
    Double precio;
    String acceso;
    String observacion;
    int idestado;
    String estado;
    String fecha;
    int iddetmem;
    String detmem;

    public Membresia() {
    }

    public Membresia(int id, String tipoMembresia, String duracion, Double precio, String acceso, String observacion, int idestado, String estado, int iddetmem, String detmem) {
        this.id = id;
        this.tipoMembresia = tipoMembresia;
        this.duracion = duracion;
        this.precio = precio;
        this.acceso = acceso;
        this.observacion = observacion;
        this.idestado = idestado;
        this.estado = estado;
        this.iddetmem = iddetmem;
        this.detmem = detmem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setFecha(String id) {
        this.fecha = id;
    }

    public String getTipoMembresia() {
        return tipoMembresia;
    }

    public void setTipoMembresia(String tipoMembresia) {
        this.tipoMembresia = tipoMembresia;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getAcceso() {
        return acceso;
    }

    public void setAcceso(String acceso) {
        this.acceso = acceso;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getIdestado() {
        return idestado;
    }

    public void setIdestado(int idestado) {
        this.idestado = idestado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIddetmem() {
        return iddetmem;
    }

    public void setIddetmem(int iddetmem) {
        this.iddetmem = iddetmem;
    }

    public String getDetmem() {
        return detmem;
    }

    public void setDetmem(String detmem) {
        this.detmem = detmem;
    }

    
    
}
