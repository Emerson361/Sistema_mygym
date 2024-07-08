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
public class EntrenadorDAO {
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
        String sql = "SELECT * FROM entrenador";
        List<Entrenador> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Entrenador ent = new Entrenador();
                ent.setId(rs.getInt(1));
                ent.setNombre(rs.getString(2));
                ent.setApellido(rs.getString(3));
                ent.setTipo_doc(rs.getString(4));
                ent.setNum_doc(rs.getString(5));
                ent.setTelefono(rs.getString(6));
                ent.setCorreo(rs.getString(7));
                ent.setFecha(rs.getString(8));
                ent.setFechaTermino(rs.getString(9));
                ent.setHorario(rs.getString(10));
                lista.add(ent); //em
            }
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return lista;
    }

    
    public int agregar(Entrenador ent) {
        String sql = "INSERT INTO entrenador(Nombre, Apellido, doc_tipo, doc_num, telefono, correo, fecha_contratacion, fecha_termino, horario) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, ent.getNombre());
            ps.setString(2, ent.getApellido());
            ps.setString(3, ent.getTipo_doc());
            ps.setString(4, ent.getNum_doc());
            ps.setString(5, ent.getTelefono());
            ps.setString(6, ent.getCorreo());
            ps.setString(7, ent.getFecha());
            ps.setString(8, ent.getFechaTermino());
            ps.setString(9, ent.getHorario());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return r; // rpta
    }

    public Entrenador listarId(int id) {
        Entrenador en = new Entrenador();
        String sql = "select * from entrenador where id_entrenador=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                en.setNombre(rs.getString(2));
                en.setApellido(rs.getString(3));
                en.setTipo_doc(rs.getString(4));
                en.setNum_doc(rs.getString(5));
                en.setTelefono(rs.getString(6));
                en.setCorreo(rs.getString(7));
                en.setFecha(rs.getString(8));
                en.setFechaTermino(rs.getString(9));
                en.setHorario(rs.getString(10)); //emp
            }
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return en;
    }

    public int actualizar(Entrenador ent) {
        String sql = "update entrenador set Nombre=?, Apellido=?, doc_tipo=?, doc_num=?, telefono=?, correo=?, fecha_contratacion=?, fecha_termino=?, horario=? where id_entrenador=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, ent.getNombre());
            ps.setString(2, ent.getApellido());
            ps.setString(3, ent.getTipo_doc());
            ps.setString(4, ent.getNum_doc());
            ps.setString(5, ent.getTelefono());
            ps.setString(6, ent.getCorreo());
            ps.setString(7, ent.getFecha());
            ps.setString(8, ent.getFechaTermino());
            ps.setString(9, ent.getHorario());
            ps.setInt(10,  ent.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return r; // rpta
    }

    public void eliminar(int id) {
        String sql = "delete from entrenador where id_entrenador=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
    }
}
