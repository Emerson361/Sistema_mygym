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
 * @author EMERSOM APAZA
 */
public class DetalleMembresiaDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r; //rpta
    
    public List listar() {
        String sql = "SELECT * from detalle_membresia";
        List<DetalleMembresia> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DetalleMembresia det = new DetalleMembresia();
                det.setId(rs.getInt("id_detalle_mem"));
                det.setFecha(rs.getString("fecha_inicio"));
                det.setFechaTermino(rs.getString("fecha_final"));
                det.setHorario(rs.getString("horario")); 
                lista.add(det); //em
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }
    
    
    
    public int agregar(DetalleMembresia DetalleMembresia) {
        String sql = "INSERT INTO detalle_membresia(fecha_inicio, fecha_final, horario) VALUES(?, ?, ?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, DetalleMembresia.getFecha());
            ps.setString(2, DetalleMembresia.getFechaTermino());
            ps.setString(3, DetalleMembresia.getHorario());
            
             
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return r; // rpta
    }
    
    
    
    
    
    
    public DetalleMembresia listarId(int id) {
        DetalleMembresia det = new DetalleMembresia();
        String sql = "select * from detalle_membresia where id_detalle_mem=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                det.setFecha(rs.getString(2));
                det.setFechaTermino(rs.getString(3));
                det.setHorario(rs.getString(4));
               
            }
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return det;
    }
    
    public int actualizar(DetalleMembresia DetalleMembresia) {
        String sql = "update detalle_membresia set fecha_inicio=?, fecha_final=?, horario=? where id_detalle_mem=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, DetalleMembresia.getFecha());
            ps.setString(2, DetalleMembresia.getFechaTermino());
            ps.setString(3, DetalleMembresia.getHorario());
            ps.setInt(4,  DetalleMembresia.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return r; // rpta
    }
    
     public void eliminar(int id) {
        String sql = "delete from detalle_membresia where id_detalle_mem=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
    }
}
