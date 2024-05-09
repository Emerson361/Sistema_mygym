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
        String sql = "SELECT * FROM rutina";
        List<Rutina> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Rutina rut = new Rutina();
                rut.setId(rs.getInt("id_rutina"));
                rut.setNombre(rs.getString("Nombre"));
                rut.setTiporutina(rs.getString("tipo_rutina"));
                rut.setDescripcion(rs.getString("descripcion"));
                rut.setDificultad(rs.getString("dificultad"));
                rut.setFrecuencia(rs.getString("frecuencia"));              
                rut.setEntrenador(rs.getInt("identrenador")); 
                lista.add(rut); //em
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    public int agregar(Nutricionista nut) {
        String sql = "INSERT INTO nutricionista(Nombre, Apellido, doc_tipo, doc_num, telefono, correo, fecha_contratacion, fecha_termino, horario) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, nut.getNombre());
            ps.setString(2, nut.getApellido());
            ps.setString(3, nut.getTipo_doc());
            ps.setString(4, nut.getNum_doc());
            ps.setString(5, nut.getTelefono());
            ps.setString(6, nut.getCorreo());
            ps.setString(7, nut.getFecha());
            ps.setString(8, nut.getFechaTermino());
            ps.setString(9, nut.getHorario());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return r; // rpta
    }

    public Nutricionista listarId(int id) {
        Nutricionista nu = new Nutricionista();
        String sql = "select * from nutricionista where id_nutricionista=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                nu.setNombre(rs.getString(2));
                nu.setApellido(rs.getString(3));
                nu.setTipo_doc(rs.getString(4));
                nu.setNum_doc(rs.getString(5));
                nu.setTelefono(rs.getString(6));
                nu.setCorreo(rs.getString(7));
                nu.setFecha(rs.getString(8));
                nu.setFechaTermino(rs.getString(9));
                nu.setHorario(rs.getString(10)); //emp
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return nu;
    }

    public int actualizar(Nutricionista nut) {
        String sql = "update nutricionista set Nombre=?, Apellido=?, doc_tipo=?, doc_num=?, telefono=?, correo=?, fecha_contratacion=?, fecha_termino=?, horario=? where id_nutricionista=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, nut.getNombre());
            ps.setString(2, nut.getApellido());
            ps.setString(3, nut.getTipo_doc());
            ps.setString(4, nut.getNum_doc());
            ps.setString(5, nut.getTelefono());
            ps.setString(6, nut.getCorreo());
            ps.setString(7, nut.getFecha());
            ps.setString(8, nut.getFechaTermino());
            ps.setString(9, nut.getHorario());
            ps.setInt(10, nut.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return r; // rpta
    }

    public void eliminar(int id) {
        String sql = "delete from nutricionista where id_nutricionista=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
