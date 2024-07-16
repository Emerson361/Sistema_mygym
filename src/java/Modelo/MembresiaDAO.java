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
import java.util.Set;

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
        String sql = "SELECT membresia.id_membresia, membresia.tipo_membresia, membresia.duracion_meses, membresia.precio,"
                    + "membresia.acceso, membresia.observaciones, estado.id_estado, estado.detalle_estado AS estado, detalle_membresia.id_detalle_mem,"
                    + "detalle_membresia.horario as horario, detalle_membresia.fecha_inicio as fecha_inicio FROM membresia "
                    + "INNER JOIN estado ON membresia.estado_detmem_id = estado.id_estado INNER JOIN detalle_membresia ON membresia.detalle_mem_id = detalle_membresia.id_detalle_mem";
        List<Membresia> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Membresia mem = new Membresia();
                mem.setId(rs.getInt("id_membresia"));
                mem.setTipoMembresia(rs.getString("tipo_membresia"));
                mem.setDuracion(rs.getString("duracion_meses"));
                mem.setPrecio(rs.getDouble("precio"));
                mem.setAcceso(rs.getString("acceso"));
                mem.setObservacion(rs.getString("observaciones"));
                mem.setIdestado(rs.getInt("id_estado"));
                mem.setEstado(rs.getString("estado"));
                mem.setIddetmem(rs.getInt("id_detalle_mem"));
                
                mem.setDetmem(rs.getString("horario"));
                mem.setFecha(rs.getString("fecha_inicio"));
                lista.add(mem); //em
            }
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return lista;
    }

    
    public int agregar(Membresia mem) {
        String sql = "INSERT INTO membresia(tipo_membresia, duracion_meses, precio, acceso, observaciones, estado_detmem_id, detalle_mem_id) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, mem.getTipoMembresia());
            ps.setString(2, mem.getDuracion());
            ps.setDouble(3, mem.getPrecio());
            ps.setString(4, mem.getAcceso());
            ps.setString(5, mem.getObservacion());
            ps.setInt(6, mem.getIdestado());
            ps.setInt(7, mem.getIddetmem());
            
         
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return r; // rpta
    }

    public Membresia listarId(int id) {
        Membresia me = new Membresia();
        String sql = "SELECT membresia.id_membresia, membresia.tipo_membresia, membresia.duracion_meses, membresia.precio,\n" +
"                    membresia.acceso, membresia.observaciones, estado.detalle_estado AS estado,\n" +
"                    detalle_membresia.id_detalle_mem, estado.id_estado FROM membresia \n" +
"                    INNER JOIN estado ON membresia.estado_detmem_id = estado.id_estado "
                + "INNER JOIN detalle_membresia ON membresia.detalle_mem_id = detalle_membresia.id_detalle_mem "
                + "where id_membresia =" + id;
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
                me.setEstado(rs.getString(7));
                me.setIddetmem(rs.getInt(8));
                me.setIdestado(rs.getInt(9));
                
                
            }
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return me;
    }

    public int actualizar(Membresia mem) {
        String sql = "update membresia set tipo_membresia=?, duracion_meses=?, precio=?, acceso=?, observaciones=?, estado_detmem_id=?, detalle_mem_id=? where id_membresia=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, mem.getTipoMembresia());
            ps.setString(2, mem.getDuracion());
            ps.setDouble(3, mem.getPrecio());
            ps.setString(4, mem.getAcceso());
            ps.setString(5, mem.getObservacion());
            ps.setInt(6, mem.getIdestado());
            ps.setInt(7, mem.getIddetmem());
            ps.setInt(8,  mem.getId());
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
