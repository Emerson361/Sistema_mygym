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
 * @author Axel
 */
public class VentasDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r; //rpta
    
    public List listar() {
        String sql = "SELECT " +
        "ventas.id_recibo, " +
        "ventas.fecha_recibo, " +
        "ventas.observaciones, " +
        "ventas.id_membresia, " +
        "membresia.tipo_membresia AS membresia, " +
        "ventas.id_dieta, " +
        "dieta.nombre AS dieta, " +
        "ventas.id_rutina, " +
        "rutina.Nombre AS rutina, " +
        "ventas.id_cliente, " +
        "CONCAT(cliente.Nombre, ' ', cliente.Apellido) AS cliente " +
        "FROM ventas " +
        "INNER JOIN cliente ON ventas.id_cliente = cliente.id_cliente " +
        "INNER JOIN membresia ON ventas.id_membresia = membresia.id_membresia " +
        "INNER JOIN dieta ON ventas.id_dieta = dieta.Id_dieta " +
        "INNER JOIN rutina ON ventas.id_rutina = rutina.id_rutina";
        List<Ventas> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ventas venta = new Ventas();
                venta.setId_recibo(rs.getInt("id_recibo"));
                venta.setFecha_recibo(rs.getString("fecha_recibo"));
                venta.setObservaciones(rs.getString("observaciones"));
                venta.setId_membresia(rs.getInt("id_membresia"));
                venta.setMembresia(rs.getString("membresia"));
                venta.setId_dieta(rs.getInt("id_dieta"));
                venta.setDieta(rs.getString("dieta"));
                venta.setId_rutina(rs.getInt("id_rutina"));
                venta.setRutina(rs.getString("rutina"));
                venta.setId_cliente(rs.getInt("id_cliente"));
                venta.setCliente(rs.getString("cliente"));
                lista.add(venta);
            }
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return lista;
    }
    
    public int agregar(Ventas venta) {
        String sql = "INSERT INTO ventas(fecha_recibo, observaciones, id_membresia, id_dieta, id_rutina, id_cliente) VALUES(?, ?, ?, ?, ?, ?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, venta.getFecha_recibo());
            ps.setString(2, venta.getObservaciones());
            ps.setInt(3, venta.getId_membresia());
            ps.setInt(4, venta.getId_dieta());
            ps.setInt(5, venta.getId_rutina());
            ps.setInt(6, venta.getId_cliente());
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return r; // rpta
    }
    
    public Ventas listarId(int id) {
        Ventas venta = new Ventas();
        String sql = "select * from ventas where id_recibo=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                // venta.setIdVentas(rs.getInt("Id_ventas"));
                venta.setFecha_recibo(rs.getString(2));
                venta.setObservaciones(rs.getString(3));
                venta.setId_cliente(rs.getInt(4));  
                venta.setId_membresia(rs.getInt(5));
                venta.setId_dieta(rs.getInt(6));
                venta.setId_rutina(rs.getInt(7));
            }
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return venta;
    }
    
    public int actualizar(Ventas venta) {
        String sql = "UPDATE ventas SET fecha_recibo=?, observaciones=?, id_membresia=?, id_dieta=?, id_rutina=?, id_cliente=? WHERE id_recibo=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, venta.getFecha_recibo());
            ps.setString(2, venta.getObservaciones());
            ps.setInt(3, venta.getId_membresia());
            ps.setInt(4, venta.getId_dieta());
            ps.setInt(5, venta.getId_rutina());
            ps.setInt(6, venta.getId_cliente());
            ps.setInt(7, venta.getId_recibo());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return r; // rpta
    }
    
    public void eliminar(int id) {
        String sql = "delete from ventas where id_recibo=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
    }
}
