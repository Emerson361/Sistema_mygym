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
public class ClienteDAO {
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
        String sql = "SELECT * FROM cliente";
        List<Cliente> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setId(rs.getInt(1));
                cli.setNombre(rs.getString(2));
                cli.setApellido(rs.getString(3));
                cli.setTipo_doc(rs.getString(4));
                cli.setNum_doc(rs.getString(5));
                cli.setTelefono(rs.getString(6));
                cli.setCorreo(rs.getString(7));
                cli.setGenero(rs.getString(8));
                cli.setFecha_nac(rs.getString(9));
                cli.setUsuario(rs.getString(10));
                cli.setPassword(rs.getString(11));
                lista.add(cli); //em
            }
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return lista;
    }

    
    public int agregar(Cliente cli) {
        String sql = "INSERT INTO cliente(Nombre, Apellido, doc_tipo, doc_num, telefono, correo, genero, fecha_nac, nom_usuario, contrasena) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, cli.getNombre());
            ps.setString(2, cli.getApellido());
            ps.setString(3, cli.getTipo_doc());
            ps.setString(4, cli.getNum_doc());
            ps.setString(5, cli.getTelefono());
            ps.setString(6, cli.getCorreo());
            ps.setString(7, cli.getGenero());
            ps.setString(8, cli.getFecha_nac());
            ps.setString(9, cli.getUsuario());
            ps.setString(10, cli.getPassword());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return r; // rpta
    }

    public Cliente listarId(int id) {
        Cliente cl = new Cliente();
        String sql = "select * from cliente where id_cliente=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cl.setNombre(rs.getString(2));
                cl.setApellido(rs.getString(3));
                cl.setTipo_doc(rs.getString(4));
                cl.setNum_doc(rs.getString(5));
                cl.setTelefono(rs.getString(6));
                cl.setCorreo(rs.getString(7));
                cl.setGenero(rs.getString(8));
                cl.setFecha_nac(rs.getString(9));
                cl.setUsuario(rs.getString(10));
                cl.setPassword(rs.getString(11));
            }
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return cl;
    }

    public int actualizar(Cliente cli) {
        String sql = "update cliente set Nombre=?, Apellido=?, doc_tipo=?, doc_num=?, telefono=?, correo=?, genero=?, fecha_nac=?, nom_usuario=?, contrasena=? where id_cliente=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cli.getNombre());
            ps.setString(2, cli.getApellido());
            ps.setString(3, cli.getTipo_doc());
            ps.setString(4, cli.getNum_doc());
            ps.setString(5, cli.getTelefono());
            ps.setString(6, cli.getCorreo());
            ps.setString(7, cli.getGenero());
            ps.setString(8, cli.getFecha_nac());
            ps.setString(9, cli.getUsuario());
            ps.setString(10, cli.getPassword());
            ps.setInt(11,  cli.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return r; // rpta
    }

    public void eliminar(int id) {
        String sql = "delete from cliente where id_cliente=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
    }
}
