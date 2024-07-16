/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        String sql = "SELECT \n"
                + "    ventas.id_ventas,\n"
                + "    ventas.fecha_venta,\n"
                + "    ventas.observacion,\n"
                + "    ventas.Id_cliente,\n"
                + "    CONCAT(cliente.Nombre, ' ', cliente.Apellido) AS cliente,\n"
                + "    ventas.Id_membresia,\n"
                + "    membresia.tipo_membresia AS membresia,\n"
                + "    ventas.Id_dieta,\n"
                + "    dieta.nombre AS dieta,\n"
                + "    ventas.Id_rutina,\n"
                + "    rutina.Nombre AS rutina\n"
                + "FROM ventas\n"
                + "INNER JOIN cliente ON ventas.Id_cliente = cliente.id_cliente\n"
                + "INNER JOIN membresia ON ventas.Id_membresia = membresia.id_membresia\n"
                + "INNER JOIN dieta ON ventas.Id_dieta = dieta.Id_dieta\n"
                + "INNER JOIN rutina ON ventas.Id_rutina = rutina.id_rutina";
         
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
                venta.setIdCliente(rs.getInt("Id_cliente"));
                venta.setCliente(rs.getString("cliente"));
                venta.setIdMembresia(rs.getInt("Id_membresia"));
                venta.setMembresia(rs.getString("membresia"));
                venta.setIdDieta(rs.getInt("Id_dieta"));
                venta.setDieta(rs.getString("dieta"));
                venta.setIdRutina(rs.getInt("Id_rutina"));
                venta.setRutina(rs.getString("rutina"));
                
                lista.add(venta);
            }
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return lista;
    }
    
    public int agregar(Ventas venta) {
        String sql = "INSERT INTO ventas(fecha_venta, observacion, Id_cliente, Id_membresia, Id_dieta, Id_rutina) "
                + "VALUES(?, ?, ?, ?, ?, ?)";
        
        int r = 0;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, venta.getFechaVenta());
            ps.setString(2, venta.getObservacion());
            ps.setInt(3, venta.getIdCliente());
            ps.setInt(4, venta.getIdMembresia());
            ps.setInt(5, venta.getIdDieta());
            ps.setInt(6, venta.getIdRutina());
            
            r = ps.executeUpdate();
            if (r == 0) {
                    throw new SQLException("Creating user failed, no rows affected.");
                }

        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                r = generatedKeys.getInt(1);
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }
            
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

    
    public List listarCliente(int idcliente) {
        String sql = "SELECT \n"
                + "    ventas.id_ventas,\n"
                + "    ventas.fecha_venta,\n"
                + "    ventas.observacion,\n"
                + "    ventas.Id_cliente,\n"
                + "    CONCAT(cliente.Nombre, ' ', cliente.Apellido) AS cliente,\n"
                + "    ventas.Id_membresia,\n"
                + "    membresia.tipo_membresia AS membresia,\n"
                + "    ventas.Id_dieta,\n"
                + "    dieta.nombre AS dieta,\n"
                + "    ventas.Id_rutina,\n"
                + "    rutina.Nombre AS rutina\n"
                + "FROM ventas\n"
                + "INNER JOIN cliente ON ventas.Id_cliente = cliente.id_cliente\n"
                + "INNER JOIN membresia ON ventas.Id_membresia = membresia.id_membresia\n"
                + "INNER JOIN dieta ON ventas.Id_dieta = dieta.Id_dieta\n"
                + "INNER JOIN rutina ON ventas.Id_rutina = rutina.id_rutina"
                + "where ventas.Id_cliente = "+idcliente;
         
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
                venta.setIdCliente(rs.getInt("Id_cliente"));
                venta.setCliente(rs.getString("cliente"));
                venta.setIdMembresia(rs.getInt("Id_membresia"));
                venta.setMembresia(rs.getString("membresia"));
                venta.setIdDieta(rs.getInt("Id_dieta"));
                venta.setDieta(rs.getString("dieta"));
                venta.setIdRutina(rs.getInt("Id_rutina"));
                venta.setRutina(rs.getString("rutina"));
                
                lista.add(venta);
            }
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return lista;
    }
    
     
    }
    
 
