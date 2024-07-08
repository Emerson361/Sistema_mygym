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
public class AdministradorDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r; //rpta

    public Admin validar(String usuario, String password) {
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
    }

    // OPERACIONES CRUD
    public List listar() {
        String sql = "SELECT * FROM administrador";
        List<Admin> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Admin admin = new Admin();
                admin.setId(rs.getInt(1));
                admin.setNombre(rs.getString(2));
                admin.setApellido(rs.getString(3));
                admin.setTipo_doc(rs.getString(4));
                admin.setNum_doc(rs.getString(5));
                admin.setTelefono(rs.getString(6));
                admin.setCorreo(rs.getString(7));
                admin.setUsuario(rs.getString(8));
                admin.setPassword(rs.getString(9));
                lista.add(admin); //em
            }
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return lista;
    }

    public int agregar(Admin admin) {
        String sql = "INSERT INTO administrador(Nombre, Apellido, doc_tipo, doc_num, telefono, correo, nom_usuario, contrasena) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, admin.getNombre());
            ps.setString(2, admin.getApellido());
            ps.setString(3, admin.getTipo_doc());
            ps.setString(4, admin.getNum_doc());
            ps.setString(5, admin.getTelefono());
            ps.setString(6, admin.getCorreo());
            ps.setString(7, admin.getUsuario());
            ps.setString(8, admin.getPassword());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return r; // rpta
    }

    public Admin listarId(int id) {
        Admin adm = new Admin();
        String sql = "select * from administrador where id_administrador=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                adm.setNombre(rs.getString(2));
                adm.setApellido(rs.getString(3));
                adm.setTipo_doc(rs.getString(4));
                adm.setNum_doc(rs.getString(5));
                adm.setTelefono(rs.getString(6));
                adm.setCorreo(rs.getString(7));
                adm.setUsuario(rs.getString(8));
                adm.setPassword(rs.getString(9)); //emp
            }
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return adm;
    }

    public int actualizar(Admin admin) {
        String sql = "update administrador set Nombre=?, Apellido=?, doc_tipo=?, doc_num=?, telefono=?, correo=?, nom_usuario=?, contrasena=? where id_administrador=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, admin.getNombre());
            ps.setString(2, admin.getApellido());
            ps.setString(3, admin.getTipo_doc());
            ps.setString(4, admin.getNum_doc());
            ps.setString(5, admin.getTelefono());
            ps.setString(6, admin.getCorreo());
            ps.setString(7, admin.getUsuario());
            ps.setString(8, admin.getPassword());
            ps.setInt(9,  admin.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return r; // rpta
    }

    public void eliminar(int id) {
        String sql = "delete from administrador where id_administrador=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
    }
}
