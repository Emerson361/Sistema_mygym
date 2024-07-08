/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fidel
 */
public class BoletaDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r; //rpta
    
    public List listar() {
        String sql = "SELECT " +
        "boleta.id_boleta, " + 
        "boleta.id_recibo, " + 
        "boleta.fecha_pago, " +
        "boleta.tipo_pago, " +
        "boleta.estado_pago, " +
        "ventas.observaciones AS recibo " +
        "FROM boleta " +
        "INNER JOIN ventas ON ventas.id_recibo = boleta.id_recibo";
        List<Boleta> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Boleta boleta = new Boleta();
                boleta.setId_boleta(rs.getInt("id_boleta"));
                boleta.setFecha_pago(rs.getString("fecha_pago"));
                boleta.setTipo_pago(rs.getString("tipo_pago"));
                boleta.setEstado_pago(rs.getString("estado_pago"));
                boleta.setId_recibo(rs.getInt("id_recibo"));
                boleta.setRecibo(rs.getString("recibo"));
                lista.add(boleta);
            }
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return lista;
    }
    
    public int agregar(Boleta boleta) {
        String sql = "INSERT INTO boleta(id_recibo, fecha_pago, tipo_pago, estado_pago) VALUES(?, ?, ?, ?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, boleta.getId_recibo());
            ps.setString(2, boleta.getFecha_pago());
            ps.setString(3, boleta.getTipo_pago());
            ps.setString(4, boleta.getEstado_pago());
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return r; // rpta
    }
    
    public Boleta listarId(int id) {
        Boleta boleta = new Boleta();
        String sql = "select * from boleta where id_boleta=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                // venta.setIdVentas(rs.getInt("Id_ventas"));
                boleta.setId_recibo(rs.getInt(2));
                boleta.setFecha_pago(rs.getString(3));
                boleta.setTipo_pago(rs.getString(4));
                boleta.setEstado_pago(rs.getString(5));
            }
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return boleta;
    }
    
    public int actualizar(Boleta boleta) {
        String sql = "UPDATE boleta SET id_recibo=?, fecha_pago=?, tipo_pago=?, estado_pago=? WHERE id_boleta=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, boleta.getId_recibo());
            ps.setString(2, boleta.getFecha_pago());
            ps.setString(3, boleta.getTipo_pago());
            ps.setString(4, boleta.getEstado_pago());
            ps.setInt(5, boleta.getId_boleta());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return r; // rpta
    }
    
    public void eliminar(int id) {
        String sql = "delete from boleta where id_boleta=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
    }
}
