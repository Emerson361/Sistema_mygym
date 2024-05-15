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
import Modelo.Entrenador;
/**
 *
 * @author EMERSOM APAZA
 */
public class RutinaDAO {
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
        String sql = "SELECT rutina.id_rutina, rutina.Nombre, rutina.descripcion, rutina.dificultad, rutina.frecuencia, "
                + "entrenador.id_entrenador, CONCAT(entrenador.Nombre, ' ', entrenador.Apellido) AS nombre_entrenador "
                + "FROM rutina INNER JOIN entrenador ON rutina.identrenador = entrenador.id_entrenador";
        List<Rutina> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Rutina rut = new Rutina();
                rut.setId(rs.getInt("id_rutina"));
                rut.setNombre(rs.getString("Nombre"));
                rut.setDescripcion(rs.getString("descripcion"));
                rut.setDificultad(rs.getString("dificultad"));
                rut.setFrecuencia(rs.getString("frecuencia"));    
                rut.setIdentrenador(rs.getInt("id_entrenador"));
                rut.setEntrenador(rs.getString("nombre_entrenador")); 
                lista.add(rut); //em
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    public int agregar(Rutina rut) {
        String sql = "INSERT INTO rutina(Nombre, descripcion, dificultad, frecuencia, identrenador) "
                + "VALUES(?, ?, ?, ?, ?)";
        int r = 0;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, rut.getNombre());
            ps.setString(2, rut.getDescripcion());
            ps.setString(3, rut.getDificultad());
            ps.setString(4, rut.getFrecuencia());
            ps.setInt(5, rut.getIdentrenador());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return r; // rpta
    }

    public Rutina listarId(int id) {
        Rutina ru = new Rutina();
        String sql = "select * from rutina where id_rutina=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ru.setNombre(rs.getString(2));
                ru.setDescripcion(rs.getString(3));
                ru.setDificultad(rs.getString(4));
                ru.setFrecuencia(rs.getString(5));
                ru.setEntrenador(rs.getString(6));
                
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ru;
    }

    public int actualizar(Rutina rut) {
        String sql = "update rutina set Nombre=?, descripcion=?, dificultad=?, frecuencia=?, identrenador=? where id_rutina=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, rut.getNombre());
            ps.setString(2, rut.getDescripcion());
            ps.setString(3, rut.getDificultad());
            ps.setString(4, rut.getFrecuencia());
            ps.setString(5, rut.getEntrenador());
            ps.setInt(6, rut.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return r; // rpta
    }

    public void eliminar(int id) {
        String sql = "delete from rutina where id_rutina=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
