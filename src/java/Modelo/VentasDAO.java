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
        "ventas.id_ventas, " +
        "ventas.fecha_venta, " +
        "ventas.observacion, " +
        "ventas.fecha_inicio, " +
        "ventas.fecha_final, " +
        "ventas.horario, " +
        "ventas.Id_membresia, " +
        "membresia.tipo_membresia AS membresia, " +
        "ventas.Id_dieta, " +
        "dieta.nombre AS dieta, " +
        "ventas.Id_rutina, " +
        "rutina.Nombre AS rutina, " +
        "ventas.Id_cliente, " +
        "CONCAT(cliente.Nombre, ' ', cliente.Apellido) AS cliente " +
        "FROM ventas " +
        "INNER JOIN cliente ON ventas.Id_cliente = cliente.id_cliente " +
        "INNER JOIN membresia ON ventas.Id_membresia = membresia.id_membresia " +
        "INNER JOIN dieta ON ventas.Id_dieta = dieta.Id_dieta " +
        "INNER JOIN rutina ON ventas.Id_rutina = rutina.id_rutina";
        List<Ventas> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ventas venta = new Ventas();
                venta.setIdVentas(rs.getInt("Id_ventas"));
                venta.setFechaVenta(rs.getString("fecha_venta"));
                venta.setObservacion(rs.getString("observacion"));
                venta.setFechaInicio(rs.getString("fecha_inicio"));
                venta.setFechaFinal(rs.getString("fecha_final"));
                venta.setHorario(rs.getString("horario"));
                venta.setIdMembresia(rs.getInt("Id_membresia"));
                venta.setMembresia(rs.getString("membresia"));
                venta.setIdDieta(rs.getInt("Id_dieta"));
                venta.setDieta(rs.getString("dieta"));
                venta.setIdRutina(rs.getInt("Id_rutina"));
                venta.setRutina(rs.getString("rutina"));
                venta.setIdCliente(rs.getInt("Id_cliente"));
                venta.setCliente(rs.getString("cliente"));
                lista.add(venta);
            }
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return lista;
    }
    
    public int agregar(Ventas venta) {
        String sql = "INSERT INTO ventas(fecha_venta, observacion, fecha_inicio, fecha_final, horario, Id_membresia, Id_dieta, Id_rutina, Id_cliente) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, venta.getFechaVenta());
            ps.setString(2, venta.getObservacion());
            ps.setString(3, venta.getFechaInicio());
            ps.setString(4, venta.getFechaFinal());
            ps.setString(5, venta.getHorario());
            ps.setInt(6, venta.getIdMembresia());
            ps.setInt(7, venta.getIdDieta());
            ps.setInt(8, venta.getIdRutina());
            ps.setInt(9, venta.getIdCliente());
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return r; // rpta
    }
    
    public Ventas listarId(int id) {
        Ventas venta = new Ventas();
        String sql = "select * from ventas where Id_ventas=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                // venta.setIdVentas(rs.getInt("Id_ventas"));
                venta.setFechaVenta(rs.getString(2));
                venta.setObservacion(rs.getString(3));
                venta.setFechaInicio(rs.getString(4));
                venta.setFechaFinal(rs.getString(5));
                venta.setHorario(rs.getString(6));
                venta.setIdMembresia(rs.getInt(7));
                // venta.setMembresia(rs.getString("membresia"));
                venta.setIdDieta(rs.getInt(8));
                // venta.setDieta(rs.getString("dieta"));
                venta.setIdRutina(rs.getInt(9));
                // venta.setRutina(rs.getString("rutina"));
                venta.setIdCliente(rs.getInt(10));
                // venta.setCliente(rs.getString(10));  
            }
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return venta;
    }
    
    public int actualizar(Ventas venta) {
        String sql = "UPDATE ventas SET fecha_venta=?, observacion=?, fecha_inicio=?, fecha_final=?, horario=?, Id_membresia=?, Id_dieta=?, Id_rutina=?, Id_cliente=? WHERE Id_ventas=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, venta.getFechaVenta());
            ps.setString(2, venta.getObservacion());
            ps.setString(3, venta.getFechaInicio());
            ps.setString(4, venta.getFechaFinal());
            ps.setString(5, venta.getHorario());
            ps.setInt(6, venta.getIdMembresia());
            ps.setInt(7, venta.getIdDieta());
            ps.setInt(8, venta.getIdRutina());
            ps.setInt(9, venta.getIdCliente());
            ps.setInt(10, venta.getIdVentas());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return r; // rpta
    }
    
    public void eliminar(int id) {
        String sql = "delete from ventas where Id_ventas=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
    }
}
