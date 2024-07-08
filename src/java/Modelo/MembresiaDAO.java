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
public class MembresiaDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r; //rpta

    /*public Admin validar(String usuario, String password) {
        Admin admin = new Admin();
        String sql = "select * from administrador where nom_usuario = ? and contrasena = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                admin.setNombre(rs.getString("Nombre"));
                admin.setApellido(rs.getString("Apellido"));
                admin.setCorreo(rs.getString("correo"));
                admin.setUsuario(rs.getString("nom_usuario"));
                admin.setPassword(rs.getString("contraseña"));
            }
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return admin;
    }*/

    // OPERACIONES CRUD
    public List listar() {
        String sql = "SELECT * FROM membresia";
        List<Membresia> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Membresia mem = new Membresia();
                mem.setId(rs.getInt(1));
                mem.setTipoMembresia(rs.getString(2));
                mem.setDuracion(rs.getString(3));
                mem.setPrecio(rs.getDouble(4));
                mem.setAcceso(rs.getString(5));
                mem.setObservacion(rs.getString(6));
                lista.add(mem); //em
            }
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return lista;
    }

    
    public int agregar(Membresia mem) {
        String sql = "INSERT INTO membresia(tipo_membresia, duracion_meses, precio, acceso, observaciones) VALUES(?, ?, ?, ?, ?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, mem.getTipoMembresia());
            ps.setString(2, mem.getDuracion());
            ps.setDouble(3, mem.getPrecio());
            ps.setString(4, mem.getAcceso());
            ps.setString(5, mem.getObservacion());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return r; // rpta
    }

    public Membresia listarId(int id) {
        Membresia me = new Membresia();
        String sql = "select * from membresia where id_membresia=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                me.setTipoMembresia(rs.getString(2));
                me.setDuracion(rs.getString(3));
                me.setPrecio(rs.getDouble(4));
                me.setAcceso(rs.getString(5));
                me.setObservacion(rs.getString(6));
            }
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return me;
    }

    public int actualizar(Membresia mem) {
        String sql = "update membresia set tipo_membresia=?, duracion_meses=?, precio=?, acceso=?, observaciones=? where id_membresia=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, mem.getTipoMembresia());
            ps.setString(2, mem.getDuracion());
            ps.setDouble(3, mem.getPrecio());
            ps.setString(4, mem.getAcceso());
            ps.setString(5, mem.getObservacion());
            ps.setInt(6,  mem.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return r; // rpta
    }

    public void eliminar(int id) {
        String sql = "delete from membresia where id_membresia=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
    }
}
