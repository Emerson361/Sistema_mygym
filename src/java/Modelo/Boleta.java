/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Axel
 */
public class Boleta {
    int id_boleta;
    int id_recibo;
    String recibo;
    String fecha_pago;
    String tipo_pago;
    String estado_pago;
    
    public Boleta() {
    }

    public Boleta(int id_boleta, int id_recibo, String fecha_pago, String tipo_pago, String estado_pago) {
        this.id_boleta = id_boleta;
        this.id_recibo = id_recibo;
        this.fecha_pago = fecha_pago;
        this.tipo_pago = tipo_pago;
        this.estado_pago = estado_pago;
    }

    public int getId_boleta() {
        return id_boleta;
    }

    public void setId_boleta(int id_boleta) {
        this.id_boleta = id_boleta;
    }

    public int getId_recibo() {
        return id_recibo;
    }

    public void setId_recibo(int id_recibo) {
        this.id_recibo = id_recibo;
    }
    
    public String getRecibo() {
        return recibo;
    }

    public void setRecibo(String recibo) {
        this.recibo = recibo;
    }

    public String getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(String fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public String getTipo_pago() {
        return tipo_pago;
    }

    public void setTipo_pago(String tipo_pago) {
        this.tipo_pago = tipo_pago;
    }

    public String getEstado_pago() {
        return estado_pago;
    }

    public void setEstado_pago(String estado_pago) {
        this.estado_pago = estado_pago;
    }
}
